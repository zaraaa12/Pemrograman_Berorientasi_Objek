public class Movie {
    private String title;
    private String genre;
    private String author;
    private int duration;
    private String showTime;

    public Movie(String title, String genre, String author, int duration, String showTime) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.duration = duration;
        this.showTime = showTime;
    }

    public void displayMovie() {
        System.out.println("Movie: " + title + "| Genre: " + genre +"| Author: "+author+"| Duration: "+duration+"| Show Time: "+ showTime);
    }
    
}
