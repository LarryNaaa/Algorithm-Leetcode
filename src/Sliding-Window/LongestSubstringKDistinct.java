import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {
    public static int findLength(String str, int k) {
        // basic check
        if(str.length() == 0 || k == 0) return 0;
        // use a map to store characters and counts
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0, length = 0;
        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++){
            // get the character from the string 's' at index 'windowEnd'
            char end = str.charAt(windowEnd);
            // use map to store end and count the numbers of end
            map.put(end,map.getOrDefault(end,0) + 1);
            // if the size of the map greater than k, shrink the window.
            while(map.size() > k){
                // get the charater from string 's' at index 'windowStart'
                char start = str.charAt(windowStart);
                // update map
                map.put(start, map.get(start) - 1);
                // if the numbers of this character is 0, so remove it from the map
                if(map.get(start) == 0){
                    map.remove(start);
                }
                // slide the window ahead
                windowStart++;
            }
            // get the current length
            length = windowEnd - windowStart + 1;
            // get the max length
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("cbbebi", 3));
    }
}
