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