import java.util.Scanner;

public class Palindrome{
    public Palindrome(){

    }

    public static void main(String[] args) throws Exception{
        Scanner userinput = new Scanner(System.in);
        String palindromeWord = "";
        System.out.println("\t Define the word Palindrome \n");
        System.out.println("Enter a word : ");
        String word = userinput.nextLine();
        int wordLength = word.length();

        for(int i = wordLength - 1; i >= 0; --i){
            palindromeWord = palindromeWord + word.charAt(i);
        }

        if(word.equals(palindromeWord)){
            System.out.println("\n Belong to a Palindrome word");
        } else {
            System.out.println("\n Excluding Palindrome words");
            userinput.close();
        }
    }
}