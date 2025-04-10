package com.Ecommerse;

public class BankTransfer extends PaymentMethode {
    public BankTransfer(String accountName, String accountId) {
        super(accountName, accountId);
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("============================================================");
        System.out.println("Processing Bank Payment..");
        validateAccount();
        if (accountName != null && !accountName.isEmpty() && accountId != null && !accountId.isEmpty()) {
            System.out.println("Paid $" + amount + " via Bank Transfer for " + accountName);
            System.out.println("Successfully!");
        } else {
            System.out.println("Bank Transfer Payment failed: invalid account.");
        }
    }
    }