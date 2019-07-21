package set;

import java.util.*;

/**
 * 350. 两个数组的交集 II
 *
 * 示例 1:

 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 输出: [2,2]
 示例 2:

 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 输出: [4,9]
 说明：

 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 我们可以不考虑输出结果的顺序。
 进阶:

 如果给定的数组已经排好序呢？你将如何优化你的算法？
 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Intersection2 {

    /**
     * map映射
     * 由于取出的元素包含重复元素，我们考虑使用map映射存储该元素数量
     *
     * 1. 将数组 nums1 放入映射 map 中，key 为相应元素， 如果 map 中不包含该元素，则初始化 value 为 1， 如果包含，value 则为原来基础上在 +1
     * 2. 遍历数组 nums2，如果 map 中包含该元素，则添加进 list 中，并将 value - 1， 当 value == 0时，将其从 map 中删除
     * 3. 遍历list， 加入到数组中即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n + m + l), 时间复杂度为遍历各个数组和 list 的时间总和
     * 空间复杂度：O(n + l), n 为 nums1 的大小，最坏情况下是每个元素都不相同，总的空间复杂度为 map 所占用的空间 + list所占用的空间
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums1) {
            if (!treeMap.containsKey(num)) {
                treeMap.put(num, 1);
            } else {
                treeMap.put(num, treeMap.get(num) + 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (treeMap.containsKey(num)) {
                list.add(num);
                treeMap.put(num, treeMap.get(num) - 1);
                if (treeMap.get(num) == 0) {
                    treeMap.remove(num);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 排序法
     * 1. 分别将两个数组进行排序
     * 2. 创建两个指针 i, j, 分别对应 nums1、nums2
     * 3. 遍历两个数组，如果 nums1[i] == nums2[j]，该元素一定为交集，同时将指针往后移动
     * 4. nums1[i] > nums2[j]， 则移动 j , 否则移动 i
     * 5. 最后将 list 转为数组即可
     *
     * 复杂度分析：
     * 时间复杂度：(不考虑 Arrays.sort()的情况下) O(n + m + l), 分别为数组大小和 list 大小
     * 空间复杂度：O(l) , l 为相交元素的list所占用的空间
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums1);
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] num1 = {1, 2, 2, 1};
        int[] num2 = {2, 2};
        System.out.println("两个数组的交集2:" + Arrays.toString(intersect2(num1, num2)));
    }

}
