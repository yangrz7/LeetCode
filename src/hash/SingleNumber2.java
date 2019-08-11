package hash;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * 137. 只出现一次的数字 II
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。

 说明：

 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

 示例 1:

 输入: [2,2,3,2]
 输出: 3
 示例 2:

 输入: [0,1,0,1,0,1,99]
 输出: 99

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/single-number-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SingleNumber2 {

    /**
     * 排序法：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 3) {
            if (i == nums.length - 1) return nums[i];
            if (nums[i] != nums[i + 1]) return nums[i];
        }
        return -1;
    }

    /**
     * 使用set去重后的元素之和 * 3 - 原数组只和 / 2 则为不重复的元素
     *
     * 时间复杂度：O(n + s), 遍历数组和set所需的时间
     * 空间复杂度：O(n), set所需的空间
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        int sum = 0;
        int setSum = 0;
        for (int num : nums) {
            sum += num;
            set.add(num);
        }
        for (Integer i : set) {
            setSum += i;
        }
        return (setSum * 3 - sum) / 2;
    }
    
    public static void main(String[] args) {
         int[] nums = {2, 2, 3, 2};
         System.out.println(Arrays.toString(nums));
         System.out.println("只出现一次的数字 II:" + singleNumber2(nums));
    }

}
