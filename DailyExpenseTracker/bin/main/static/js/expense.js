const apiUrl = "http://localhost:8080/api/transactions";
const transactionList = document.getElementById("transactionList");
const totalBalance = document.getElementById("totalBalance");

function loadTransactions() {
  fetch(apiUrl)
    .then((res) => res.json())
    .then((data) => {
      const expenses = data.filter((t) => t.type === "expense"); // Filter untuk expense
      renderTransactions(expenses);
      updateBalance(data);
    });
}

function renderTransactions(transactions) {
  transactionList.innerHTML = "";
  transactions.forEach((t) => {
    const div = document.createElement("div");
    div.className = `transaction-card ${t.type}`;

    // Struktur HTML baru untuk styling tombol
    div.innerHTML = `
      <div class="transaction-details">
        <i class="fas fa-wallet"></i>
        <span class="description">${t.description}</span>
        <span class="amount">-$${t.amount.toFixed(2)}</span>
      </div>
      <div class="transaction-actions">
        <button class="btn action edit"><i class="fas fa-edit"></i></button>
        <button class="btn action delete"><i class="fas fa-trash-alt"></i></button>
      </div>
    `;

    // Tambahkan event listener untuk tombol
    div.querySelector(".delete").onclick = () => deleteTransaction(t.id);
    div.querySelector(".edit").onclick = () => openEditModal(t);
    transactionList.appendChild(div);
  });
}

function updateBalance(transactions) {
  let income = 0,
    expense = 0;
  transactions.forEach((t) => {
    if (t.type === "income") income += t.amount;
    else expense += t.amount;
  });

  const total = income - expense;
  totalBalance.textContent = `$${total.toFixed(2)}`;
  document.querySelector(
    ".summary .income"
  ).innerHTML = `<i class="fas fa-arrow-up"></i> $${income.toFixed(2)}`;
  document.querySelector(
    ".summary .expense"
  ).innerHTML = `<i class="fas fa-arrow-down"></i> $${expense.toFixed(2)}`;
}

// FUNGSI BARU: Hapus Transaksi
function deleteTransaction(id) {
  if (!confirm("Are you sure you want to delete this?")) return;
  fetch(`${apiUrl}/${id}`, { method: "DELETE" })
    .then((res) => {
      if (!res.ok) throw new Error("Delete gagal: " + res.status);
      loadTransactions();
    })
    .catch((err) => console.error("Error deleting:", err));
}

// FUNGSI BARU: Buka Modal Edit
function openEditModal(tx) {
  const modalEdit = document.getElementById("modalEdit");
  document.getElementById("editIdInput").value = tx.id;
  document.getElementById("editDescInput").value = tx.description;
  document.getElementById("editAmountInput").value = tx.amount;
  document.getElementById("editTransactionType").value = tx.type;
  modalEdit.classList.remove("hidden");
}

// FUNGSI INI UNTUK MODAL TAMBAH (ADD)
function initExpenseModal() {
  const modal = document.getElementById("modal");
  const addBtn = document.querySelector(".add-btn");
  const submitBtn = document.getElementById("submitBtn");

  addBtn.addEventListener("click", () => modal.classList.remove("hidden"));
  modal.addEventListener("click", (e) => {
    if (e.target === modal) {
      modal.classList.add("hidden");
    }
  });

  submitBtn.addEventListener("click", () => {
    const descInput = document.getElementById("descInput");
    const amountInput = document.getElementById("amountInput");
    const transactionType = document.getElementById("transactionType");

    transactionType.value = "expense"; // Paksa tipe expense

    const transaction = {
      description: descInput.value.trim(),
      amount: parseFloat(amountInput.value),
      type: transactionType.value,
    };

    if (!transaction.description || isNaN(transaction.amount))
      return alert("Isi dengan benar");

    fetch(apiUrl, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(transaction),
    }).then(() => {
      modal.classList.add("hidden");
      descInput.value = "";
      amountInput.value = "";
      loadTransactions();
    });
  });
}

// FUNGSI BARU INI UNTUK MODAL EDIT
function initEditModal() {
  const modalEdit = document.getElementById("modalEdit");
  const updateBtn = document.getElementById("updateBtn");

  modalEdit.addEventListener("click", function (e) {
    if (e.target === modalEdit) {
      modalEdit.classList.add("hidden");
    }
  });

  if (updateBtn) {
    updateBtn.addEventListener("click", () => {
      const id = document.getElementById("editIdInput").value;
      const transaction = {
        description: document.getElementById("editDescInput").value.trim(),
        amount: parseFloat(document.getElementById("editAmountInput").value),
        type: document.getElementById("editTransactionType").value,
      };

      if (!transaction.description || isNaN(transaction.amount))
        return alert("Isi dengan benar");

      fetch(`${apiUrl}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(transaction),
      })
        .then((res) => {
          if (!res.ok) throw new Error("Update gagal");
          modalEdit.classList.add("hidden");
          loadTransactions();
        })
        .catch((err) => console.error("Error updating:", err));
    });
  }
}
