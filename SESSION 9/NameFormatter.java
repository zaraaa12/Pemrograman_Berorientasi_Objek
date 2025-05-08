import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Stream;

public class NameFormatter {
    private static final String TARGET_NAME = "Mohamed";
   private static final String REPLACEMENT_NAME = "Muhammad";

   public NameFormatter() {
   }

   public static void main(String[] args) {
      String[] students = new String[]{"Mohamed Ahmed", "Mohamed Ali", "Mohamed Salah", "Mohamed Hassan", "Mohamed Ibrahim", "Mohamed Noor", "Mohamed Farah", "Mohamed Yusuf", "Mohamed Ismail", "Mohamed Kamal"};
      Stream var10000 = Arrays.stream(students).map(NameFormatter::formatName);
      PrintStream var10001 = System.out;
      var10000.forEach(var10001::println);
   }

   private static String formatName(String fullName) {
      String[] parts = fullName.split(" ", 2);
      String firstName = parts[0].equalsIgnoreCase("Mohamed") ? "Muhammad" : parts[0];
      return parts.length > 1 ? parts[1] + ", " + firstName : firstName;
   }
}