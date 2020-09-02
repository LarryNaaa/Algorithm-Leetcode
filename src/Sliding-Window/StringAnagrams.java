import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        // result
        List<Integer> result = new ArrayList<>();
        // basic check
        if(s.length() == 0) return result;
        // use a map to store the characters and their frequency in string 'p'
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < p.length(); i++){
            char c = p.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // use the sliding window
        int windowStart = 0;
        // our goal is to match the characters from the map with the current window
        // a variable records the matched count
        int matchedCount = 0;
        // use a loop to extend the window
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++){
            // get the character from string 's' at the index 'windowEnd'
            char end = s.charAt(windowEnd);
            // if the map contains this character, decrease the frequency
            if(map.containsKey(end)){
                // decrease the frequency
                map.put(end, map.get(end) - 1);
                // if the value of the character in map is equal to 0,
                // increase the matched count
                if(map.get(end) == 0){
                    matchedCount++;
                }
            }
            // if the index windowEnd is not less than the length of string 'p' - 1
            if(windowEnd >= p.length() - 1){
                // if the number of matched count is equal to the size of the map,
                // we get a satisfied anagrams
                if(matchedCount == map.size()){
                    result.add(windowStart);
                }
                // shrink window
                char start = s.charAt(windowStart);
                // if the map contains the key start, update match and map
                if(map.containsKey(start)){
                    // decrease the matched count
                    if(map.get(start) == 0){
                        matchedCount--;
                    }
                    // put the character back
                    map.put(start, map.get(start) + 1);
                }
                // slide the window ahead
                windowStart++;
            }
        }
        return result;
    }
}
