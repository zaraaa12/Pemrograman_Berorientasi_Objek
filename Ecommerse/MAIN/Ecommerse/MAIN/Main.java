package MAIN.Ecommerse.MAIN;

public class Main {
    public static <PaymentMethode> void main(String[] args) {
        PaymentMethode payment1 = new Ewallet("Zara", "EW123");
        PaymentMethode payment2 = new BankTransfer("Kev", "BT456");
        PaymentMethode payment3 = new CreditCard("", "CC789"); 

        payment1.pay(100.0);
        payment2.pay(250.0);
        payment3.pay(75.0);

        PaymentMethode p = new Ewallet("Charlie", "EW890");
        p.validateAccount(true); 
    }
}