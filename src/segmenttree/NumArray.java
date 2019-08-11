package segmenttree;

/**
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

 示例：

 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 说明:

 你可以假设数组不可变。
 会多次调用 sumRange 方法。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/range-sum-query-immutable
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class NumArray {

    private int[] sum;  // sum[i]存储前 i 个元素的和，sum[0] = 0
    private int[] data;

    public NumArray(int[] nums) {
        data = nums;
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    /**
     * 暴力破解法
     * 1. 在进行求和是遍历整个数组，找出我们所需区间 [i, j]的所有元素
     * 2. 将其累加即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n), 每次调用 sumRange()的复杂度都是 O(n)
     * 空间复杂度：O(1), data只是nums的一个引用
     *
     * @param i
     * @param j
     * @return
     */
    public int sumRange(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += data[k];
        }
        return sum;
    }

    /**
     * 预处理
     * 1. 事先现将 nums 中前 i 个元素存储进数组 sum 中, 其中 sum[0] = 0，所以我们需要多开辟一个空间
     * 2. 在计算区间 [i, j]的和，使用 j（包括j）之前所有的元素之和 减去 i（不包括i）之前的元素和即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n), n 为数组 nums 的长度, 每次查询的时间复杂度为 O(1)
     * 空间复杂度：O(n)，新开辟的数组 sum
     *
     * @param i
     * @param j
     * @return
     */
    public int sumRange2(int i, int j) {
        return sum[j + 1] - sum[i];
    }
    
    public static void main(String[] args) {
         int[] nums = {-2, 0, 3, -5, 2, -1};
         NumArray numArray = new NumArray(nums);
         System.out.println("区域和检索 - 数组不可变:" + numArray.sumRange(2, 5));
    }

}
