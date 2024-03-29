package array;

/**
 * 在一个给定的数组nums中，总是存在一个最大元素 。

 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。

 如果是，则返回最大元素的索引，否则返回-1。

 示例 1:

 输入: nums = [3, 6, 1, 0]
 输出: 1
 解释: 6是最大的整数, 对于数组中的其他整数,
 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.


 示例 2:

 输入: nums = [1, 2, 3, 4]
 输出: -1
 解释: 4没有超过3的两倍大, 所以我们返回 -1.


 提示:

 nums 的长度范围在[1, 50].
 每个 nums[i] 的整数范围在 [0, 99].
 *
 */
public class DominantIndex {

    /**
     * 1.先找去整个数组中最大的数 max1
     * 2.同时找出数组中第二大的数 max2，如果 max1 >= max2 * 2 ,返回 max1 所对应的 index 即可
     * 
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param nums
     * @return
     */
    public static int dominantIndex(int[] nums) {
        if (nums.length == 0) return -1;
        int max1 = 0;
        int max2 = 0;
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
                index = i;
            } else if (nums[i] > max2) {
                max2 = nums[i];
            } 
        }
        return max1 >= max2 *2 ? index : -1;
    }
    
    public static void main(String[] args) {
         int[] nums = new int[] {1};
         System.out.println("最大的数index为" + dominantIndex(nums));
    }

}
