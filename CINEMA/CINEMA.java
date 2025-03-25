import java.util.Scanner;

public class CINEMA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //user
        User user1 = new User("Jennie", "jennie@gmail.com");
        Movie movie1 = new Movie("Exhuma", "Horror", "Jang Jae-Hyung", 134, "13:00");

        //buying ticket 
        System.out.println("Select your seat : ");
        String seatNumber = scanner.nextLine();
        Ticket ticket1 = new Ticket(user1, movie1, seatNumber, 25.0);
        ticket1.displayTicket();

        System.out.println("\nPayment Method:");
        System.out.println("1. Credit card");
        System.out.println("2. PayPal");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        Payment payment;
        double ticketPrice;

        if (paymentChoice == 1){
            System.out.println("Input Credit Card Number: ");
            String cardNumber = scanner.nextLine();
            payment = new CreditCardPay(cardNumber);
            ticketPrice = 50.0;
        }
        else if (paymentChoice == 2){
            System.out.println("Input E-mail: ");
            String email = scanner.nextLine();
            payment = new PayPalPayment(email);
            ticketPrice = 25.0;
        }
        else {
            System.out.println("Invalid, then use the default payment.");
            payment = new Payment();
            ticketPrice = 50.0;
        }
        //based on payment method
        Ticket ticket = new Ticket(user1, movie1, seatNumber, ticketPrice);
        ticket.displayTicket();

        payment.pay(ticketPrice);
        scanner.close();
    }
}
