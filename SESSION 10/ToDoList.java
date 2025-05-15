import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ToDoList extends JFrame {
    private JPanel todoPanel;
    private JTextField inputField;
    private final String FILE_NAME = "todos.txt";
    private ArrayList<ToDoItem> todos = new ArrayList<>();

    public ToDoList() {
        setTitle("To Do List");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        todoPanel = new JPanel();
        todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(todoPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputField = new JTextField(20);
        JButton addButton = new JButton("Add");
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                todos.add(new ToDoItem(text, false));
                inputField.setText("");
                saveTodos();
                updateDisplay();
            }
        });

        loadTodos();
        updateDisplay();
        setVisible(true);
    }

    static class ToDoItem {
        String text;
        boolean done;

        ToDoItem(String text, boolean done) {
            this.text = text;
            this.done = done;
        }

        @Override
        public String toString() {
            return done + "|" + text;
        }

        static ToDoItem fromString(String line) {
            if (!line.contains("|")) {
                return new ToDoItem(line, false);
            }
            String[] parts = line.split("\\|", 2);
            boolean done = Boolean.parseBoolean(parts[0]);
            String text = parts[1];
            return new ToDoItem(text, done);
        }
    }

    private void loadTodos() {
        todos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                todos.add(ToDoItem.fromString(line));
            }
        } catch (IOException e) {
            // File mungkin belum ada, bisa diabaikan
        }
    }

    private void saveTodos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (ToDoItem item : todos) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to save file", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDisplay() {
        todoPanel.removeAll();

        for (int i = 0; i < todos.size(); i++) {
            final int index = i;
            ToDoItem item = todos.get(i);

            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setPreferredSize(new Dimension(400, 50));
            itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            itemPanel.setBackground(new Color(245, 245, 245));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JCheckBox checkBox = new JCheckBox((index + 1) + ". " + item.text, item.done);
            checkBox.setOpaque(false);
            checkBox.addActionListener(e -> {
                todos.get(index).done = checkBox.isSelected();
                saveTodos();
            });

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setOpaque(false);
            JButton editButton = new JButton("Edit");
            JButton deleteButton = new JButton("Delete");

            editButton.addActionListener(e -> {
                String newText = JOptionPane.showInputDialog(this, "Edit:", todos.get(index).text);
                if (newText != null && !newText.trim().isEmpty()) {
                    todos.get(index).text = newText.trim();
                    saveTodos();
                    updateDisplay();
                }
            });

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Do you want to delete this?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    todos.remove(index);
                    saveTodos();
                    updateDisplay();
                }
            });

            editButton.setMargin(new Insets(2, 5, 2, 5));
            deleteButton.setMargin(new Insets(2, 5, 2, 5));
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);

            itemPanel.add(checkBox, BorderLayout.CENTER);
            itemPanel.add(buttonPanel, BorderLayout.EAST);
            todoPanel.add(itemPanel);
        }

        todoPanel.revalidate();
        todoPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoList());
    }
}