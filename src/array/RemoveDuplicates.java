package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

 示例 1:

 给定数组 nums = [1,1,2],

 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

 你不需要考虑数组中超出新长度后面的元素。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class RemoveDuplicates {

    private Map<Integer, Integer> map;

    public RemoveDuplicates() {
        this.map = new HashMap<>();
    }

    /**
     * 该方法不满足O(1)的空间复杂度
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        return map.size();
    }

    /**
     * 双指针法(前提是数组必须是已经排好序的)
     *
     * 使用一个快指针 j 和一个慢指针 count
     * 如果 num[j] == num[count] 则跳过即可
     * 当 num[j] != num[count] 时，则count递增， 并且需要把 nums[count] = nums[j]
     * 最后再将 count + 1 即可，加的是第一个元素
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int count = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[count]) {
                count++;
                nums[count] = nums[j];
            }
        }
        return ++count;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        RemoveDuplicates duplicates = new RemoveDuplicates();
        System.out.println("数组长度为：" + duplicates.removeDuplicates2(nums));
//        for (Map.Entry<Integer, Integer> arg : duplicates.map.entrySet()) {
//            System.out.print(arg.getKey());
//        }
    }

}
