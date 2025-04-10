package com.Ecommerse.PAYMENT;

abstract class PaymentMethode implements Payable{
    protected String accountName; 
    protected String accountId;    

    public PaymentMethode (String accountName, String accountId) {
        this.accountName = accountName;
        this.accountId = accountId;
    }

    @Override
    public void validateAccount() {
        if (accountName != null && !accountName.isEmpty() && accountId != null && !accountId.isEmpty()) {
            System.out.println("Validation successful for account: " + accountName);
        } else {
            System.out.println("Validation failed: account information is incomplete.");
        }
    }

    // Overloaded method for additional validation info
    public void validateAccount(boolean verbose) {
        if (accountName != null && !accountName.isEmpty() && accountId != null && !accountId.isEmpty()) {
            if (verbose) System.out.println("Validation (verbose): successful for account: " + accountName);
        } else {
            if (verbose) System.out.println("Validation (verbose): failed - account info incomplete.");
        }
    }
}
