public class Ticket {
    private User user;
    private Movie movie;
    private String seatNumber;
    private double price;

//construktor Overloading
    public Ticket(User user, Movie movie, String seatNumber) {
        this.user = user;
        this.movie = movie;
        this.seatNumber = seatNumber;
        this.price = 50.0;
    }
    public Ticket(User user, Movie movie, String seatNumber, double price) {
        this.user= user;
        this.movie=movie;
        this.seatNumber=seatNumber;
        this.price = price;
    }
    public void displayTicket(){
        System.out.println("=== Ticket Information ===");
        user.displayUser();
        movie.displayMovie();
        System.out.println("Seat Number: "+seatNumber+ " | Price: Rp. "+price);
    }

}
