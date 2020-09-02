import java.util.HashMap;
import java.util.Map;

public class StringPermutation {
    public static boolean findPermutation(String str, String pattern){
        // use a map store characters in string 's1' and the frequency of these characters
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < pattern.length(); i++){
            // get the characters from streing 's1' at index 'i'
            char c = pattern.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int windowStart = 0, count = 0;
        // use a loop to extend the window [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++){
            // get the characters from string 's2' at index 'windowEnd'
            char end = str.charAt(windowEnd);
            // if the map contains this character 'end', then we do next operation
            // else slide the window ahead
            if(map.containsKey(end)){
                // update map
                map.put(end, map.get(end) - 1);
                // if the value of the character is equal to 0, count++
                if(map.get(end) == 0){
                    count++;
                }
            }
            // basic check
            if(count == map.size()) return true;
            //
            if(windowEnd >= pattern.length() - 1){
                // get character from s2 at index 'windowStart'
                char start = str.charAt(windowStart);
                // if map contains this charater 'start', we need to recover the frequency of it
                if(map.containsKey(start)){
                    // recover count
                    if(map.get(start) == 0){
                        count--;
                    }
                    // update map
                    map.put(start, map.get(start) + 1);
                }
                // slide window ahead
                windowStart++;
            }
        }
        return count == map.size();
    }

    public static void main(String[] args) {
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("oidbcaf", "abc"));
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("odicf", "dc"));
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("aaacb", "abc"));
    }
}
