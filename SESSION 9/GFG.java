import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GFG {
    GFG() {
   }

   static boolean areAnagrams(String s1, String s2) {
      HashMap<Character, Integer> charCount = new HashMap();
      char[] var6;
      int var5 = (var6 = s1.toCharArray()).length;

      char ch;
      int var4;
      for(var4 = 0; var4 < var5; ++var4) {
         ch = var6[var4];
         charCount.put(ch, (Integer)charCount.getOrDefault(ch, 0) + 1);
      }

      var5 = (var6 = s2.toCharArray()).length;

      for(var4 = 0; var4 < var5; ++var4) {
         ch = var6[var4];
         charCount.put(ch, (Integer)charCount.getOrDefault(ch, 0) - 1);
      }

      Iterator var8 = charCount.entrySet().iterator();

      while(var8.hasNext()) {
         Map.Entry<Character, Integer> pair = (Map.Entry)var8.next();
         if ((Integer)pair.getValue() != 0) {
            return false;
         }
      }

      return true;
   }

   public static void main(String[] args) {
      String s1 = "siti";
      String s2 = "itis";
      System.out.println(areAnagrams(s1, s2) ? "true" : "false");
   }
}