package map;

import java.util.*;

/**
 * 961. 重复 N 次的元素
 *
 * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。

 返回重复了 N 次的那个元素。

  

 示例 1：

 输入：[1,2,3,3]
 输出：3
 示例 2：

 输入：[2,1,2,5,3,2]
 输出：2
 示例 3：

 输入：[5,1,5,2,5,3,5,4]
 输出：5
  

 提示：

 4 <= A.length <= 10000
 0 <= A[i] < 10000
 A.length 为偶数


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class RepeatedNTimes {

    /**
     * 暴力解法（Map映射）
     * 1. 将每个元素一级每个元素出现的次数放入 map 中
     * 2. 遍历该 map， 找到 value 最大的元素所对应的 key 值即可
     *
     * 复杂度分析：
     * 时间复杂度：O(a + a + log(n))，复杂度为数组 A 的长度和遍历 map 的时间复杂度之和 + containsKey()方法
     * 空间复杂度：O(m), 空间复杂度为 map 的大小
     *
     * @param A
     * @return
     */
    public static int repeatedNTimes(int[] A) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : A) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        int num = 0;
        int res = 0;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int key = (int) entry.getKey();
            int value = (int) entry.getValue();
            if (value > num) {
                num = value;
                res = key;
            }
        }
        return res;
    }

    /**
     * Set集合法
     * 因为根据题目，总共有 N + 1 个不同的元素，又有一种元素会有多个，那么剩下的元素一定都是不重复的，那么， 我们直接放入 set 集合判断是否已经存在该元素即可
     *
     * 复杂度分析：
     * 时间复杂度：O(a + log(n)), 复杂度为遍历数组 + contains()方法
     * 空间复杂度：O(s), 所用空间为将 A 中每个元素放入集合 set 中
     *
     * @param A
     * @return
     */
    public static int repeatedNTimes2(int[] A) {
        Set<Integer> set = new TreeSet<>();
        for (int num : A) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return 0;
    }

    /**
     * 排序法：
     * 1. 先将数组进行排序
     * 2. 如果重复元素在 A.length / 2 左右，去中位数就是我们所找的元素，eg：[1, 2, 2, 2, 3, 4]
     * 3. 如果重复元素在 [0, A.length / 2 - 1] ，我们取 A[mid - 1]即可， eg：[2, 2, 2, 3, 4, 5]
     * 4. 如果重复元素在 [A.length / 2, A.length], 我们取 mid 即可，eg：[1, 2, 3, 4, 4, 4]
     *
     * 复杂度分析：
     * 时间复杂度：n * log(n)，由于 Arrays.sort()使用归并排序，会使用 n * log(n) 的时间复杂度
     * 空间复杂度：O(n), 归并排序所用的空间
     *
     * @param A
     * @return
     */
    public static int repeatedNTimes3(int[] A) {
        //先对数组进行排序
        Arrays.sort(A);
        int mid = A.length / 2;
        if (A[mid] == A[mid - 1] || A[mid] == A[mid + 1]) {
            return A[mid];
        } else {
            return A[mid - 1];
        }
    }
    
    public static void main(String[] args) {
         int[] nums = {5, 1, 5, 2, 5, 3, 5,4 };
         System.out.println(Arrays.toString(nums));
         System.out.println("重复 N 次的元素:" + repeatedNTimes3(nums));
    }

}
