package array;

/**
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。

 示例 1:

 输入:
 nums = [1, 7, 3, 6, 5, 6]
 输出: 3
 解释:
 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 同时, 3 也是第一个符合要求的中心索引。
 示例 2:

 *
 */
public class PivotIndex {

    /**
     * 暴力破解法
     * 循环数组，分别计算 index 之前和之后的和，如果相同，则返回index
     *
     * 复杂度分析：
     * 时间复杂度：O(n * n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums) {
        if (nums.length == 0) return - 1;
        int index = 0;
        for (index = 0; index < nums.length; index++) {
            int sumLeft = 0;
            int sumRight = 0;
            for (int i = 0; i < index; i++) {
                sumLeft += nums[i];
            }
            for (int j = index + 1; j < nums.length; j++) {
                sumRight += nums[j];
            }
            if (sumLeft == sumRight) return index;
        }
        return -1;
    }

    /**
     * 1.先算出数组中元素之和
     * 2.遍历数组，计算 leftSum，则 rightSum = sum - leftSum - nums[i]
     * 3.如果 leftSum == rightSum, 则返回i即为中心索引
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int pivotIndex2(int[] nums) {
        if (nums.length == 0) return -1;
        int sum = 0;
        int leftSum = 0;
        int rightSum = 0;

        for (int n : nums) sum += n;

        for (int i = 0; i < nums.length; i++) {
            if (i != 0) leftSum = leftSum + nums[i - 1];
            rightSum = sum - leftSum - nums[i];
            if (leftSum == rightSum) return i;
        }
        return -1;
    }
    
    public static void main(String[] args) {
         int[] nums = new int[] {1, 7, 3, 6, 5, 6};
         System.out.println("中心索引为：" + pivotIndex2(nums));
    }

}
