import java.io.File;
import java.io.IOException;

public class NewFile {
    public static void main(String[] args) {
        try {
            File newFile = new  File("filename.txt");
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
