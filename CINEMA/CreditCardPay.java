public class CreditCardPay extends Payment{
    private String cardNumber;
    public CreditCardPay(String cardNumber){
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount){
        System.out.println("Paid Rp. "+amount+ " Credit Card Number: "+cardNumber);
    }
}