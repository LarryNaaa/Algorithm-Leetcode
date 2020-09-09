# Sliding Window
##  Introduction
The Sliding Problem contains a sliding window 
which is a sub – list that runs over 
a Large Array which is an underlying 
collection of elements.


**滑动窗口算法可以用O(n)的时间复杂度解决数组/字符串的子元素问题，**
它可以将嵌套的循环问题，转换为单循环问题，降低时间复杂度。

## 如何判断使用滑动窗口算法
如果题目中求的结果有以下情况时可使用滑动窗口算法：
1. 最小值 Minimum value
2. 最大值 Maximum value
3. 最长值 Longest value
4. 最短值 Shortest value
5. K值 K-sized value

## 滑动窗口算法的思路
1. 在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0 ，把索引左闭右开区间 [left, right) 称为一个「窗口」。
2. 不断地增加 right 指针扩大窗口 [left, right) ，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
3. 此时停止增加 right ，转而不断增加 left 指针缩小窗口 [left, right) ，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left ，都要更新一轮结果。
4. 重复第2和第3步，直到 right 到达字符串 S 的尽头。

## 使用滑动窗口算法之前，要思考的四个问题：
1. 当移动 right 扩大窗口，即加入字符时，应该更新哪些数据？
2. 什么条件下，窗口应该暂停扩大，开始移动_left_ 缩小窗口？
3. 当移动 left 缩小窗口，即移出字符时，应该更新哪些数据？
4. 我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？

## Example
Given a string and a pattern, find all anagrams of the pattern in the given string.


Input: String="ppqp", Pattern="pq"


Output: [1, 2]


Explanation: The two anagram s of the pattern in the given string are "pq" and "qp".
 
**our goal is to match characters from the map the current window**
1. use **a map** to store the characters and their frequency.
2. a variable **matchedCount**, which records characters' matched count.
3. use a **loop** to extend the window.
   1) get the right character
   2) if the map contains this character,
   we need to decrease it's frequency and if the 
   value of this character in the map is equal to
   0, then we need to increase the matchedCount.
   3) if the matchedCount is equal to the size of map,
   which means we get a satisfied anagram.
   4) if we have the required window size, then we
   shrink the window:
   get the left character;if the map contains this 
   character, and if the value of this character in the map is equal to
   0, then we need to decrease the matchedCount, put 
   the character back.
   
```java
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
```
    