# [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode curr = res;
        
        while(l1 != null && l2 != null){
            if(l1.val >= l2.val){
                curr.next = l2;
                l2 = l2.next;
            }else{
                curr.next = l1;
                l1 = l1.next;
            }
            curr = curr.next;
        }
        
        if(l1 != null){
            curr.next = l1;
        }
        
        if(l2 != null){
            curr.next = l2;
        }
        
        return res.next;
    }
```



# [Two Sum - Unique Pairs](https://algo.monster/problems/two_sum_unique_pairs)

https://leetcode.com/discuss/interview-question/372434

```java
public static int twoSumSorted(int[] nums, int target) {
        // WRITE YOUR BRILLIANT CODE HERE
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        int res = 0;
        for(int num : nums){
            if(s1.contains(target - num) && !s2.contains(num)){
                res++;
                s2.add(num);
                s2.add(target - num);
            }else{
                s1.add(num);
            }
        }

        return res;
    }
```



# [Slowest Key](https://leetcode.com/problems/slowest-key/)

```java
public char slowestKey(int[] releaseTimes, String keysPressed) {
        char res = '\0';
        int maxTime = 0;
        for(int i = 0; i < keysPressed.length(); i++){
            int temp = (i == 0) ? releaseTimes[0] : releaseTimes[i] - releaseTimes[i-1];
            if(temp > maxTime){
                maxTime = temp;
                res = keysPressed.charAt(i);
            }else if(temp == maxTime){
                res = (res < keysPressed.charAt(i)) ? keysPressed.charAt(i) : res;
            }
        }
        
        return res;
    }
```



# [Winning Sequence](https://algo.monster/problems/winning_sequence)

```java
public static int[] winningSequence(int num, int lowerEnd, int upperEnd) {
        // WRITE YOUR BRILLIANT CODE HERE
        if(num < 2 || lowerEnd > upperEnd || num > upperEnd - lowerEnd + 2) 
          return new int[]{-1};
        
        int[] res = new int[num];
        if(lowerEnd == upperEnd){
            for(int i = 0; i < num; i++){
                res[i] = upperEnd;
            }
        }
        
        res[0] = upperEnd - 1;
        res[1] = upperEnd;
        for(int i = 2; i < num; i++){
            /*
            if(upperEnd - i + 1 < lowerEnd){
                return new int[]{-1};
            }
            */
            res[i] = upperEnd - i + 1;
        }
        
        return res;
    }
```



# [Divisibility of Strings](https://leetcode.com/discuss/general-discussion/680341/Divisibility-of-Strings)

```java
public static int findSmallestDivisor(String s, String t) {
        if(s.length() % t.length() != 0) return -1;

        for(int i = 0; i < s.length(); i += t.length()){
            String temp = s.substring(i, i + t.length());
            if(!t.equals(temp)) return -1;
        }

        if(t.length() % 2 != 0){
            if(t.length() < 2) return t.length();
            for(int i = 1; i < t.length(); i++){
                if(t.charAt(i) != t.charAt(i-1)) return t.length();
            }
            return 1;
        }


        return count(t);
    }

    public static int count(String t){
        if(t.substring(0,t.length()/2).
                contentEquals(t.substring(t.length()/2, t.length()))) {
            return count(t.substring(0,t.length()/2));
        }else {
            return t.length();
        }
    }
```

```java
private static int solve(String s1, String s2) {
	if(s1.length() % s2.length() != 0)
		return -1;
	int l2 = s2.length();
	for(int i=0;i<s1.length();i++) {
		if(s1.charAt(i) != s2.charAt(i%l2))
			return -1;
	}
	for(int i=0;i<s2.length();i++) {
		int j=0;
		for(;j<s2.length();j++) {
			if(s2.charAt(j) != s2.charAt(j%(i+1)))
				break;
		}
		if(j == s2.length()) {
			return i+1;
		}
	}
	return -1;
}
```



# [Maximal Square](https://leetcode.com/problems/maximal-square/)

```java
public int maximalSquare(char[][] matrix) {
        if(matrix.length < 1 || matrix[0].length < 1)  return 0;
        // dp[r][c]
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        int res = 0;
        
        for(int r = 1; r <= matrix.length; r++){
            for(int c = 1; c <= matrix[0].length; c++){
                if(matrix[r-1][c-1] == '1'){
                    dp[r][c] = Math.min(
                        Math.min(dp[r][c-1], dp[r-1][c]), dp[r-1][c-1]) + 1;
                    if(dp[r][c] > res) res = dp[r][c];
                }
            }
        }
        
        return res * res;
    }
```



# [Count LRU Cache Misses](https://algo.monster/problems/count_lru_cache_misses)

https://leetcode.com/discuss/interview-question/992179/Amazon-or-OA-2021-or-LRU-Cache-Misses

```java
public int lruCacheMisses(int num, List<Integer> pages, int maxCacheSize) {
        List<Integer> list = new LinkedList<>();
        int count = 0;
        for(int i: pages)
        {
            if(list.contains(i)) list.remove(new Integer(i));
            else
            {
                if(list.size() == maxCacheSize) list.remove(0);
                count++;
            }
            list.add(i);
        }
        return count;
    }
```



# [Cut off Rank](https://aonecode.com/amazon-online-assessment-cutoff-ranks)

https://github.com/BhaasitaDesiraju/Coding_Practice/blob/bda27bb0aaa597ff48732d076c02f69c84e9ae6b/src/CutOffRanks.java

```java
public int cutOffRank(int cutOffRank, int num, int[] scores) {
        // O(n^2)
        if(cutOffRank == 0 || num > 100000) return 0;
        Arrays.sort(scores);

        int currRank = 1;
        for(int i = scores.length - 1; i >= 0; i--){
            int count = 1;
            while(i - 1 >= 0 && scores[i] == scores[i-1]){
                count++;
                i--;
            }
            currRank += count;
            if(currRank > cutOffRank) return num - i;
        }

        return num;
    }
```



# [Multiprocessor System](https://algo.monster/problems/multiprocessor_system)

```java
public static int multiprocessorSystem(int[] ability, int num,  int processes){
        // O(nlog(n)) 
        if(ability.length < 1 || num < 1 || processes < 1) return 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int a : ability){
            maxHeap.add(a);
        }

        int res = 0;
        while(processes > 0){
            int temp = maxHeap.remove();
            processes -=  temp;
            res++;
            int newAbility = temp / 2;
            maxHeap.add(newAbility);
        }

        return res;
    }
```



# [Earliest Time To Complete Deliveries](https://algo.monster/problems/earliest_time_to_complete_deliveries)

https://leetcode.com/discuss/interview-question/992156/Amazon-or-OA-or-Earliest-Time-To-Complete-Deliveries

```java
public static int earliestTime(int numOfBuildings, int[] buildingOpenTime, int[] offloadTime) {
        // O(nlog(n)) 
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        
        for(int time : offloadTime){
            maxHeap.add(time);
        }
        
        for(int time : buildingOpenTime){
            minHeap.add(time);
        }
        
        int res = 0;
        while(!maxHeap.isEmpty() && !minHeap.isEmpty()){
            res = Math.max(res, maxHeap.remove() + minHeap.remove());
            maxHeap.remove();
            maxHeap.remove();
            maxHeap.remove();
        }
        
        return res;
    }
```

```java
public static int earliestTime(int numOfBuildings, int[] buildingOpenTime, int[] offloadTime) {
        // O(nlog(n))
        Arrays.sort(buildingOpenTime);
        Integer[] newOff = new Integer[offloadTime.length];
        for(int i=0;i<offloadTime.length;i++){
            newOff[i]= offloadTime[i];
        }
        Arrays.sort(newOff, Collections.reverseOrder());
        int ans = 0;
        int j = 0;
        for(int i = 0; i < newOff.length; i++){
            if(i > 0 && i % 4 == 0){
                j++;
                j %= buildingOpenTime.length;
            }
            //System.out.println(i + "," + j);
            ans = Math.max(ans, newOff[i] + buildingOpenTime[j]);
        }
        return ans;
    }
```



```java
public static int earliestTime(int numOfBuildings, int[] buildingOpenTime, int[] offloadTime) {
        Arrays.sort(buildingOpenTime);
        Arrays.sort(offloadTime);

        int latestTime = Integer.MIN_VALUE;
        for (int x = 0; x < numOfBuildings; x++)
            latestTime = Math.max(latestTime, buildingOpenTime[x] + offloadTime[numOfBuildings * 4 - x * 4 - 1]);

        return latestTime;
    }
```



# [Throttling Gateway](https://algo.monster/problems/throttling_gateway) 

https://leetcode.com/discuss/interview-question/819577/Throttling-Gateway-Hackerrank

```java
public static int throttlingGateway(int[] requestTime, int num) {
        int res = 0 ;
        for(int i = 0 ;  i < num; i++){
            if(i  >2 && requestTime[i] == requestTime[i-3]){
                res++;
            } else if(i > 19 && (requestTime[i] - requestTime[i-20]) <10){
                res++;
            } else if( i > 59 && (requestTime[i] - requestTime[i-60]) <60 ){
                res++;
            }
        }
        return res;
    }
```



# [Labeling System](https://algo.monster/problems/labeling_system)

https://github.com/BrandonHo/Technical-Coding-Questions/blob/a6f11cdcba1468997b54503bc94dace31cb98293/src/ama-oa2-i/LabelingSystem.java

```java
/*
    Amazon is looking to develop a new labeling system in the fulfillment centers.
    New labels will be devised from the original string labels.
    Given the original string label, construct a new string by rearranging the original string and deleting characters as needed.
    Return the alphabetically-largest string that can be constructed respecting the limit
    as to how many consecutive characters can be the same (represented by k).
    "Alphabetically-largest" is defined in reverse alphabetical order (e.g., b is "larger" than a, c is "larger" than b, etc.)
    from left to right (e.g., "ba" is larger than "ab").

    Write an algorithm to return the alphabetically-largest string that can be constructed respecting the above limits.
    Input
    The input to the function/method consists of two arguments:
    originalLabel, a string representing the original string label;
    charlimit, an integer representing the maximum number of identical consecutive characters the new string can have (k).
    Output
    Return a string representing the alphabetically largest string that can be constructed that has no more than k identical consecutive characters.
    Constraints
    1 <= len <= 10^5; where len represents the length of originalLabel
    1 <= charlimit <= 10^3
    Note
    The string originalLabel contains only lowercase English letters.
    Example
    Input:
    originalLabel = baccc
    charLimit = 2
    Output:
    ccbca
    Explanation:
    The largest string, alphabetically, is 'cccba' but it is not allowed because it uses the character's more than 2 times consecutively.
    Therefore, the answer is 'ccbca'.
    NB: I have assumed that if an invalid string is sent (no possible combination), then I return a blank string.
*/

//method to return alphabatically largest string that is constructed respecting the limit
//method accept two argument one is string(original string) and other is integer(limit of consecutive character)
    static String fxn(String originalLabel,int charlimit) {
// a array x is define which will store the frequency of character appear in string 'originalLabel'
// index 0 is for char 'a' and in a same sequence index 25 is for character 'z'
        int x[]=new int[26];
//a for loop is used to access character of string one by one
        for(int i = 0; i < originalLabel.length(); i++){
// according to ASCII system 'a' is repesented by 97 so minus char 'a' from each char of string
// to get index for array x and increment it by 1
            x[originalLabel.charAt(i)-'a']++;
        }
// 'ans' variable will be used to store answer
        String ans="";
        int p=26,q=25,flag=1,count=0;
// variable q will start iterating from last index of x so that we can get max char
// initially flag is 1 and count is 0
        while(q >= 0){ //this is main loop which will be true until q become equal to -1
            if(flag==1){
                while(true){//this loop will be terminated with the help of break statement
                    if(x[q]==0){ //if at q index of x there is no character left
                        q--;
                        count=0;
                        break;
                    }
                    ans = ans + (char)((int)'a'+q); //character is added to ans
                    count++; //count take care about the number of consecutive character
                    x[q]--; //character is taken so 1 is minus from qth index of array x
                    if(count==charlimit){
//if we reach limit then we have two condition
//first condition is that there is no character left at that index
                        if(x[q]==0){
                            q--;
                            count=0;
                            break;
                        }
//second condition is that there is atleast one character left at qth index of array x
                        else{
//now flag is set to 0 and here comes the role of p
//now character at qth index cann"t be used(because of consecutive limit)
                            flag=0;
                            count=0;
                            p=q-1;
                            break;
                        }
                    }
                }
            }
            else{
//p start from one less then q and find a character till index 0 so that qth index can again be started
                while(p>=0){
//if no character is there at p index then minus p by 1
                    if(x[p]==0)
                        p--;
//if character is present at pth index then add it to ans and reduce the frequency of that character from x array
                    else{
                        ans=ans+(char)(p+(int)'a');
                        x[p]--;
//set flag to 1 agian so that qth index operation can be continue
                        flag=1;
                        break;
                    }
                }
            }
        }
//finally return ans
        return ans;
    }
```



# [Unique Device Names](https://aonecode.com/amazon-online-assessment-unique-device-names)

```java
/*
    You are working on the Amazon Devices team and need to create unique device names to be used in a residential loT (Internet of Things) system.
    If a device name already exists in the system, an integer number is added at the end of the name to make it unique.
    The integer added starts with land is incremented by 1 for each new request of an existing device name.
    Given a list of device name requests, write an algorithm to process all the requests and output a list of the corresponding unique device names.
    Input
    The input to the function/method consists of two arguments:
    num, an integer representing the number of device names;
    devicenames, a list of strings representing the device names;
    Output
    Return a list of strings representing the usernames in the order assigned
    Constraints
    1 <= num <= 10^5
    1 <= length of devicenames[i] >= 20
    0 <= i < num
    Note
    devicenames contains only lowercase English letters in the range ASCII[a-z].
    Example
    Input:
    num = 6
    devicenames = ["switch", "tv", "switch", "tv","switch", "tv"]
    Output:
    ["switch","tv","switch1", "tv1", "switch2", "tv2"]
    Explanation:
    devicenames[0] = "switch" is unique. uniqueDevicename[0]="switch" devicenames[1] = "tv" is unique. uniqueDevicename[1]="tv"
    devicenames[2] = devicenames[0]. Add 1 at the end the previous unique username "switch", uniqueDevicename[2]="switch1"
    devicenames[3] = devicenames[1]. Add 1 at the end the previous unique username "tv", uniqueDevicename[3]="tv1"
    devicenames[4] = devicenames[2]. Increment by 1 the number at the end of the previous unique username "switch1", uniqueDevicenames[4]="switch2"
    devicenames[5] = devicenames[3]. Increment by 1 the number at the end of the previous unique username "tv1". uniqueDevicenames[5]="tv2"
    return unique Devicenames = ["switch", "tv" "switch1" "tv1", "switch2", "tv2"]
*/

public static String[] deviceNamesSystem(String[] deviceNames, int num){
        String[] ans = new String[num];
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < num; i++){
            if(!map.containsKey(deviceNames[i])){
                map.put(deviceNames[i], 0);
                ans[i] = deviceNames[i];
            }else{
                map.put(deviceNames[i], map.get(deviceNames[i]) + 1);
                ans[i] = deviceNames[i] + map.get(deviceNames[i]);
            }
        }

        return ans;
    }
```



# [Chemical Delivery System/Choose A Flask](https://aonecode.com/amazon-online-assessment-choose-a-flask) ?

```java
public static int chooseAFlask(int numOrders, int[] requirements, int flaskTypes, int totalMarks, PairInt[] markings){
        // WRITE YOUR BRILLIANT CODE HERE
        int flaskRange = totalMarks / flaskTypes;
        int minIndex = -1;
        int minLoss = Integer.MAX_VALUE;

        for (int flaskIndex = 0; flaskIndex < flaskTypes; flaskIndex++) {

            int currLoss = 0;
            boolean validFlask = true;

            // Could optimise this further and do binary search between boundaries on array
            List<Integer> markingsPerFlask = new ArrayList<Integer>();
            for (int markIndex = flaskIndex * flaskTypes; markIndex < flaskIndex * flaskTypes + flaskRange; markIndex++)
                markingsPerFlask.add(markings[markIndex].second);

            for (int requirement : requirements) {
                int index = Arrays.binarySearch(markingsPerFlask.toArray(), requirement);

                // If valid index do nothing... Otherwise check for nearest value
                if (index < 0) {
                    // If no valid flask - stop, this one doesn't count
                    if (index * -1 - 1 >= markingsPerFlask.size()) {
                        validFlask = false;
                        break;
                    } else
                        currLoss += markingsPerFlask.get(index * -1 - 1) - requirement;
                }
            }

            if (validFlask) {
                if (minLoss > currLoss) {
                    minLoss = currLoss;
                    minIndex = flaskIndex;
                }
            }
        }

        return minIndex;


    }
```



# [Minimum Total Container Size](https://algo.monster/problems/minimum_total_container_size)

```java
public static int minContainerSize(int d, int[] P) {
        int length = P.length;
        if(d > length) return -1;
        int dp[][] = new int[length+1][d+1];

        for(int i = 0; i <= length; i++){
            for(int j = 0; j <= d; j++){
                dp[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        dp[0][0] = 0;

        for(int i = 1; i <= length; i++){
            for(int k = 1; k <= d; k++){
                int max_d = 0;
                for(int j = i - 1; j >= k - 1; j--){
                    max_d = Math.max(max_d, P[j]);
                    dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + max_d);
                }
            }
        }

        return dp[length][d];
    }
```



# [Keyword Suggestions/ Search Suggestions System](https://leetcode.com/problems/search-suggestions-system/)

```java
// Custom class Trie with function to get 3 words starting with given prefix
class Trie {

    // Node definition of a trie
    class Node {
        boolean isWord = false;
        List<Node> children = Arrays.asList(new Node[26]);
    };
    Node Root, curr;
    List<String> resultBuffer;

    // Runs a DFS on trie starting with given prefix and adds all the words in the resultBuffer, limiting result size to 3
    void dfsWithPrefix(Node curr, String word) {
        if (resultBuffer.size() == 3)
            return;
        if (curr.isWord)
            resultBuffer.add(word);

        // Run DFS on all possible paths.
        for (char c = 'a'; c <= 'z'; c++)
            if (curr.children.get(c - 'a') != null)
                dfsWithPrefix(curr.children.get(c - 'a'), word + c);
    }
    Trie() {
        Root = new Node();
    }

    // Inserts the string in trie.
    void insert(String s) {

        // Points curr to the root of trie.
        curr = Root;
        for (char c : s.toCharArray()) {
            if (curr.children.get(c - 'a') == null)
                curr.children.set(c - 'a', new Node());
            curr = curr.children.get(c - 'a');
        }

        // Mark this node as a completed word.
        curr.isWord = true;
    }
    List<String> getWordsStartingWith(String prefix) {
        curr = Root;
        resultBuffer = new ArrayList<String>();
        // Move curr to the end of prefix in its trie representation.
        for (char c : prefix.toCharArray()) {
            if (curr.children.get(c - 'a') == null)
                return resultBuffer;
            curr = curr.children.get(c - 'a');
        }
        dfsWithPrefix(curr, prefix);
        return resultBuffer;
    }
};
class Solution {
    List<List<String>> suggestedProducts(String[] products,
                                         String searchWord) {
        Trie trie = new Trie();
        List<List<String>> result = new ArrayList<>();
        // Add all words to trie.
        for (String w : products)
            trie.insert(w);
        String prefix = new String();
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            result.add(trie.getWordsStartingWith(prefix));
        }
        return result;
    }
};
```

