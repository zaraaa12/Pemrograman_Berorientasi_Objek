import java.util.HashMap;
import java.util.Map;

public class AnagramChecker {
    public AnagramChecker(){

    }

    public static boolean areAnagrams(String s1, String s2){
        if(s1 != null && s2 != null && s1.length() == s2.length()) {
            Map<Character, Integer> freqMap = new HashMap();

            int i;
            char c;
            for(i=0; i<s1.length(); ++i){
                c = s1.charAt(i);
                freqMap.put(c, (Integer)freqMap.getOrDefault(c, 0)+1);
            }

            for(i=0; i<s2.length(); ++i){
                c = s2.charAt(i);
                if (!freqMap.containsKey(c)){
                    return false;
                }

                freqMap.put(c, (Integer) freqMap.get(c)-1);
                if((Integer)freqMap.get(c)<0){
                    return false;
                }
            }

            return freqMap.values().stream().allMatch((count) -> {
                return count == 0;
            });
            } else {
                return false;
            }
    }
            public static void main(String[] args){
                String a = "lamp";
                String b = "palm";
                if(areAnagrams(a, b)){
                    System.out.printf("\"%s\" and \"%s\" are anagrams.%n", a, b);
                } else{
                    System.out.printf("\"%s\" and \"%s\" are  not anagrams.%n", a, b);
                }
            }
        }
