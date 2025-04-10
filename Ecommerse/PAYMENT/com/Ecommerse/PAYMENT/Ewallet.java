package com.Ecommerse.PAYMENT;

class Ewallet extends PaymentMethode{
    public Ewallet (String accountName, String accountId) {
        super (accountName, accountId);
    }

    @Override
    public void pay(double amount) {
        System.out.println("============================================================");
        System.out.println("Processing E-Wallet Payment..");
        validateAccount();
        if (accountName != null && !accountName.isEmpty() && accountId != null && !accountId.isEmpty()) {
            System.out.println("Paid $" + amount + " via E-Wallet for " + accountName );
            System.out.println("Successfully!");
        } else {
            System.out.println("E-Wallet Payment failed: invalid account.");
        }
    }
}