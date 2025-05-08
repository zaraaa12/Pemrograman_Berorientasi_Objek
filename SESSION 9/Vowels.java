import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Vowels {
    private static final Set<Character> VOWELS = Set.of('a','i','u','e','o','A','I','U','E','O');

    public Vowels(){
    }

    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in);
        System.out.println("Enter a words :");
        String Word = userinput.nextLine();
        long vowelCount = countVowels(Word);
        System.out.println("Sentence : \"" + Word + "\"");
        System.out.println("Number of vowels :" +vowelCount);
        userinput.close();
    }

    public static long countVowels(String str){
        if(str != null && !str.isEmpty()){
            IntStream var10000 = IntStream.range(0, str.length());
            str.getClass();
            Stream var1 = var10000.mapToObj(str::charAt);
            Set var10001 = VOWELS;
            var10001.getClass();
            return var1.filter(var10001::contains).count();
        } else{
            return 0L ;
        }
    }
}
