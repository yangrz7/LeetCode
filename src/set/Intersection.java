package set;

import java.util.*;

/**
 * 349. 两个数组的交集
 *
 * 示例 1:

 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 输出: [2]
 示例 2:

 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 输出: [9,4]
 说明:

 输出结果中的每个元素一定是唯一的。
 我们可以不考虑输出结果的顺序。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Intersection {

    /**
     * set集合法
     * 1. 将数组 nums1 放入集合set中，如果有重复元素的话，即可去重
     * 2. 遍历数组 nums2 判断集合 set 中是否包含相同元素，如果包含记录下来，再将集合 set 中相同元素删除，这样是为了防止数组 nums2 中有相同元素时再次添加
     *
     * 复杂度分析：
     * 时间复杂度：O(n + m), n 和 m 分别为两个数组的大小
     * 空间复杂度：O(n + l), 分别为 nums1 的大小 + list大小
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new TreeSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 内置方法
     *
     * 1. 分别将两个数组放入 set 集合中
     * 2. 使用 set 集合的内置方法 retainAll() 求出交集
     * 3. 将结果写进数组即可
     *
     * 复杂度分析：
     * 时间复杂度： 一般情况下是 O(m+n)，最坏情况下是 O(m × n)。
     * 空间复杂度： 最坏的情况是 O(m+n)，当数组中的元素全部不一样时。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new TreeSet<>();
        Set<Integer> set2 = new TreeSet<>();
        for (int num : nums1) set1.add(num);
        for (int num : nums2) set2.add(num);
        set1.retainAll(set2);
        int[] res = new int[set1.size()];
        int index = 0;
        for (int num : set1) res[index++] = num;
        return res;
    }
    
    public static void main(String[] args) {
         int[] num1 = {1, 2, 2, 1};
         int[] num2 = {2, 2};
         System.out.println("两个数组的交集:" + Arrays.toString(intersection2(num1, num2)));
    }

}
