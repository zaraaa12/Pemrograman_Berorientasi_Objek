import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CharacterCount {
     private final Map<Character, Integer> charCountMap;
   private final String inputString;

   public CharacterCount(String inputString) {
      if (inputString == null) {
         throw new IllegalArgumentException("Input string cannot be null");
      } else {
         this.inputString = inputString;
         this.charCountMap = this.countCharacters();
      }
   }

   private Map<Character, Integer> countCharacters() {
      return (Map)this.inputString.chars().mapToObj((c) -> {
         return (char)c;
      }).collect(Collectors.toMap((c) -> {
         return c;
      }, (c) -> {
         return 1;
      }, Integer::sum, HashMap::new));
   }

   public String getUniqueCharactersString() {
      Set<Character> seen = new LinkedHashSet();
      char[] var5;
      int var4 = (var5 = this.inputString.toCharArray()).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         char c = var5[var3];
         seen.add(c);
      }

      StringBuilder sb = new StringBuilder();
      Iterator<Character> var8 = seen.iterator();

      while(var8.hasNext()) {
         char c = (Character)var8.next();
         sb.append(c);
      }

      return sb.toString();
   }

   public Map<Character, Integer> getCharacterCounts() {
      return new HashMap(this.charCountMap);
   }

   public void printCharacterCounts() {
      System.out.printf("%nCharacter counts in \"%s\":%n", this.inputString);
      this.charCountMap.forEach((k, v) -> {
         System.out.printf("'%c': %d%n", k, v);
      });
   }

   public void printUniqueCharacterString() {
      System.out.printf("%nUnique characters string (duplicates removed): %s%n", this.getUniqueCharactersString());
   }

   public void printMostFrequentCharacters() {
      if (this.charCountMap.isEmpty()) {
         System.out.println("No characters to analyze.");
      } else {
         int maxCount = (Integer)Collections.max(this.charCountMap.values());
         List<Character> mostFrequent = (List)this.charCountMap.entrySet().stream().filter((entry) -> {
            return (Integer)entry.getValue() == maxCount;
         }).map(Map.Entry::getKey).collect(Collectors.toList());
         System.out.printf("%nMost frequent character(s) [occurred %d time(s)]: %s%n", maxCount, mostFrequent);
      }
   }

   public static void main(String[] args) throws Throwable {
      Throwable var1 = null;
      Object var2 = null;

      try {
         Scanner scanner = new Scanner(System.in);

         try {
            System.out.print("Enter a string: ");
            String userInput = scanner.nextLine();
            CharacterCount counter = new CharacterCount(userInput);
            counter.printCharacterCounts();
            counter.printUniqueCharacterString();
            counter.printMostFrequentCharacters();
         } finally {
            if (scanner != null) {
               scanner.close();
            }

         }

      } catch (Throwable var11) {
         if (var1 == null) {
            var1 = var11;
         } else if (var1 != var11) {
            var1.addSuppressed(var11);
         }

         throw var1;
      }
   }
}
