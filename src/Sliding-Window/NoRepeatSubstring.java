import java.util.HashMap;
import java.util.Map;

public class NoRepeatSubstring {
    public static int findLength(String str) {
        // basic check
        if(str.length() == 0) return 0;
        // use a map to store the characters and the frequency of characters
        Map<Character, Integer> map = new HashMap<>();
        int windowStart = 0;
        int maxLength = 0;
        // use a loop to go through the string 's'
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++){
            // get the character from the string at the index 'windowEnd'
            char end = str.charAt(windowEnd);
            // use map to store end
            map.put(end, map.getOrDefault(end, 0) + 1);
            // if the frequency of the characters in the map is equal to 2,
            // which means that we have repeating characters,
            // so we need to shrink the window
            while(map.get(end) == 2){
                // get the characters from the string at the index 'windowStart'
                char start = str.charAt(windowStart);
                // update map
                map.put(start, map.get(start) - 1);
                // slide window ahead
                windowStart++;
            }
            // populate the maximum length
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("aabccbb"));
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abbbb"));
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abccde"));
    }
}
