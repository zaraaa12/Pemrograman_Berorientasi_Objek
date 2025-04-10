package com.Ecommerse.PAYMENT;

public class CreditCard extends PaymentMethode {
    public CreditCard(String accountName, String accountId) {
        super(accountName, accountId);
    }

    @Override
    public void pay(double amount) {
        System.out.println("============================================================");
        System.out.println("Processing Credit Card Payment..");
        validateAccount();
        if (accountName != null && !accountName.isEmpty() && accountId != null && !accountId.isEmpty()) {
            System.out.println("Paid $" + amount + " via Credit Card for " + accountName);
            System.out.println("Successfully!");
        } else {
            System.out.println("Credit Card Payment failed: invalid account.");
        }
    }
    
}
