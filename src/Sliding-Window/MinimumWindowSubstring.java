import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        // use a map to store the characters and the frequency of them in string 't'
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // use a sliding window
        int windowStart = 0, minLength = Integer.MAX_VALUE;
        // record the number of characters we need to match in string 't'
        int match = 0;
        // result
        int[] result = {-1, 0, 0};
        // use a loop to extend window [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++){
            // get the character from string 's' at index 'windowEnd'
            char end = s.charAt(windowEnd);
            // if the map contains this character
            if(map.containsKey(end)){
                // update map
                map.put(end, map.get(end) - 1);
                // if the value of this character is equal to 0, increase the match
                if(map.get(end) == 0){
                    match++;
                }
            }
            // if the match is equal to the size of the map, means that
            // we find a substring satisfied the condition, then we need to shrink the window
            while(match == map.size()){
                // if the length of the substring is less than the minimum length, update minLength and result
                if(windowEnd - windowStart + 1 < minLength){
                    minLength = windowEnd - windowStart + 1;
                    result[0] = 0;
                    result[1] = windowStart;
                    result[2] = windowEnd;
                }
                // shrink window
                char start = s.charAt(windowStart);
                if(map.containsKey(start)){
                    // update match
                    if(map.get(start) == 0){
                        match--;
                    }
                    // update map
                    map.put(start, map.get(start) + 1);
                }
                // slide the window ahead
                windowStart++;
            }
        }
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
}
