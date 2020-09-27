# Algorithm-Leetcode

## Two Pointers
### 解决双指针问题三种常用思想
1. 左右指针：需要两个指针，一个指向开头，一个指向末尾，然后向中间遍历，直到满足条件或者两个指针相遇
2. 快慢指针：需要两个指针，开始都指向开头，根据条件不同，快指针走得快，慢指针走的慢，直到满足条件或者快指针走到结尾
3. 后序指针：常规指针操作是从前向后便利，对于合并和替换类型题，防止之前的数据被覆盖，双指针需从后向前便利

### 碰撞指针
一般都是排好序的数组或链表，否则无序的话这两个指针的位置也没有什么意义。
特别注意两个指针的循环条件在循环体中的变化，小心右指针跑到左指针左边去了。
常用来解决的问题有

1. 二分查找问题

2. n数之和问题：比如两数之和问题，
先对数组排序然后左右指针找到满足条件的两个数。
如果是三数问题就转化为一个数和另外两个数的两数问题。以此类推。

### 快慢指针
类似于龟兔赛跑，两个链表上的指针从同一节点出发，其中一个指针前进速度是另一个指针的两倍。利用快慢指针可以用来解决某些算法问题，比如

1. 计算链表的中点：快慢指针从头节点出发，每轮迭代中，快指针向前移动两个节点，慢指针向前移动一个节点，最终当快指针到达终点的时候，慢指针刚好在中间的节点。
2. 判断链表是否有环：如果链表中存在环，则在链表上不断前进的指针会一直在环里绕圈子，且不能知道链表是否有环。使用快慢指针，当链表中存在环时，两个指针最终会在环中相遇。
3. 判断链表中环的起点：当我们判断出链表中存在环，并且知道了两个指针相遇的节点，我们可以让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置。
4. 求链表中环的长度：只要相遇后一个不动，另一个前进直到相遇算一下走了多少步就好了
5. 求链表倒数第k个元素：先让其中一个指针向前走k步，接着两个指针以同样的速度一起向前进，直到前面的指针走到尽头了，则后面的指针即为倒数第k个元素。（严格来说应该叫先后指针而非快慢指针）

### 滑动窗口法
两个指针，一前一后组成滑动窗口，并计算滑动窗口中的元素的问题。

这类问题一般包括

1、字符串匹配问题

2、子数组问题

## Merge Interval
**区间合并，如果两个区间有重叠那么合并成一个区间**

1. 对区间按照一个元素排序，如果前区间的end小于后面区间的start，那么这个区间是不能被合并的。
2. 建立自己的比较函数，区间的大小。
3. 如果end > start，那么说明有重叠，但是还要比较两个区间的end哪个更大。

## Cyclic Sort
处理涉及包含给定范围内的数字的数组的问题.

使用输入数组包含1到'n'范围内的数字这一事实。
例如，为了有效地对数组进行排序，我们可以尝试将每个数字放在正确的位置，
即在索引“ 0”处放置“ 1”，在索引“ 1”处放置“ 2”，依此类推。完成排序后，
我们可以遍历数组以查找所有缺少正确数字的索引。这些将是我们要求的数字。

## In-place Reversal of a LinkedList
1. 逐个断开原链表的每个节点（保存下个节点）
2. 将断开的节点连接到反转链表的表头上
3. 更新反转链表的表头
4. 回到原链表的下个节点

```java
public static ListNode reverse(ListNode head) {
    // TODO: Write your code here
    ListNode prev = null;
    while(head != null){
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }
```

## BFS

## DFS
**binary tree path sum**
We will be using recursion (or we can also use a stack for 
the iterative approach) to keep track of all the previous 
(parent) nodes while traversing. This also means that the space 
complexity of the algorithm will be O(H)O(H), where ‘H’ is the 
maximum height of the tree.

## Two Heaps
**Find median of numbers**
```java
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
    minHeap = new PriorityQueue<>((a, b) -> a - b);

class MedianOfAStream {
  PriorityQueue<Integer> maxHeap;
  PriorityQueue<Integer> minHeap;

  static Comparator<Integer> maxCmp = new Comparator<Integer>(){
    public int compare(Integer o1, Integer o2){
      return o2 - o1;
    }
  };

  static Comparator<Integer> minCmp = new Comparator<Integer>(){
    public int compare(Integer o1, Integer o2){
      return o1 - o2;
    }
  };

  public MedianOfAStream(){
    maxHeap = new PriorityQueue<Integer>(maxCmp);
    minHeap = new PriorityQueue<Integer>(minCmp);
  }

  public void insertNum(int num) {
    // TODO: Write your code here
    if(maxHeap.isEmpty() || num < maxHeap.peek()){
      maxHeap.add(num);
    }else{
      minHeap.add(num);
    }
    if(maxHeap.size() > minHeap.size() + 1){
      minHeap.add(maxHeap.poll());
    }else if(maxHeap.size() < minHeap.size()){
      maxHeap.add(minHeap.poll());
    }
  }

  public double findMedian() {
    // TODO: Write your code here
    if(maxHeap.size() == minHeap.size()){
      return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
    return maxHeap.peek();
  }
```

## Subsets
To generate all subsets of the given set, 
we can use the Breadth First Search (BFS) approach. 
We can start with an empty set, 
iterate through all numbers one-by-one, 
and add them to existing sets to create new subsets.

## Binary Search
**Given a sorted array of numbers, find if a given number ‘key’ is present in the array.**
```java
public static int search(int[] arr, int key) {
    int start = 0, end = arr.length - 1;
    boolean isAscending = arr[start] < arr[end];
    while (start <= end) {
      // calculate the middle of the current range
      int mid = start + (end - start) / 2;

      if (key == arr[mid])
        return mid;

      if (isAscending) { // ascending order
        if (key < arr[mid]) {
          end = mid - 1; // the 'key' can be in the first half
        } else { // key > arr[mid]
          start = mid + 1; // the 'key' can be in the second half
        }
      } else { // descending order        
        if (key > arr[mid]) {
          end = mid - 1; // the 'key' can be in the first half
        } else { // key < arr[mid]
          start = mid + 1; // the 'key' can be in the second half
        }
      }
    }
    return -1; // element not found
  }
```

## Top 'K' Elements
**find the top/smallest/frequent ‘K’ elements among a given set**
The best data structure that comes to mind to keep track of ‘K’
 elements is Heap. This pattern will make use of the Heap to 
 solve multiple problems dealing with ‘K’ elements at a time 
 from a set of given elements.

## K-way merge
**K sorted LinkedList, merge them in one sorted list**

只要给我们“ K”个排序数组，
我们就可以使用堆有效地对所有数组的所有元素进行排序遍历。
我们可以将每个排序数组中的最小（第一个）元素放入Min Heap中，
以获取整体最小值。在将元素插入“ 最小堆”时，我们会跟踪元素来自哪个数组。
然后，我们可以从堆中删除顶部元素，以获取最小的元素，
并将该最小元素所属的同一数组中的下一个元素推到堆中。
我们可以重复此过程以对所有元素进行排序遍历。

## Dynamic Programming
**需要用递归求解，求解过程中有重叠的子问题**