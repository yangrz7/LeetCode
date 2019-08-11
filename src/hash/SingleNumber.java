package hash;

import java.util.*;

/**
 * 136. 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

 说明：

 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

 示例 1:

 输入: [2,2,1]
 输出: 1
 示例 2:

 输入: [4,1,2,1,2]
 输出: 4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/single-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SingleNumber {

    /**
     * map映射
     * 1. 遍历该数组，根据相同元素出现的次数存储到map中
     * 2. 遍历map，找到 value == 1 的 key，就是我们需要的值
     *
     * 复杂度分析：
     * 时间复杂度：O(n + m), 时间复杂度为两次遍历所用的时间
     * 空间复杂度：O(n), map所需要的空间
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) return i;
        }
        return -1;
    }

    /**
     * 排序法
     * 1. 先将数组进行排序
     * 2. 遍历该数组，判断当前元素和下一个元素是否相等，如果不等，返回该数即可，否则继续向下遍历
     * 3. 如果遍历到最后一个元素还没有找到出现一次的数字，则最后一个元素一定是我们需要的
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            if (i == nums.length - 1) return nums[i];
            if (nums[i] != nums[i + 1]) return nums[i];
        }
        return -1;
    }

    /**
     * 数学法
     *
     * @param nums
     * @return
     */
    public static int singleNumber3(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        int sum = 0;
        int setSum = 0;
        for (int num : nums) {
            set.add(num);
            sum += num;
        }
        for (Integer i : set) {
            setSum += i;
        }
        return 2 * setSum - sum;
    }

    /**
     * 异或法
     * 异或：相同为0，不同为1. 异或同一个数两次，原数不变。
     * 使用异或判断是否相同
     * a ^ a = 0
     * a ^ 0 = a
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int singleNumber4(int[] nums) {
        int res = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                res = res ^ nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1};
        System.out.println(Arrays.toString(nums));
        System.out.println("只出现一次的数字:" + singleNumber3(nums));
    }

}
