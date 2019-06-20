package array;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

 你可以假设除了整数 0 之外，这个整数不会以零开头。

 示例 1:

 输入: [1,2,3]
 输出: [1,2,4]
 解释: 输入数组表示数字 123。
 示例 2:

 输入: [4,3,2,1]
 输出: [4,3,2,2]
 解释: 输入数组表示数字 4321。
 *
 */
public class PlusOne {

    /**
     * 1.如果数组末尾 !=9 直接在最后一位 +1 即可
     * 2.如果只有之后一位 ==9 , 最后一位置 0，前一位 +1 即可
     * 3.如果每位都 ==9，将数组长度 +1，将数组第一位置1即可
     *
     * 从数组末尾 len -1 到 0 判断，如果小于9，nums[i]++ 并 return 即可
     * 如果等于9，nunms[i] == 0，继续执行即可
     * 到最后如果所有数都为9，将数组 +1 为 array[]，并将 array[0] = 1 即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int[] plusOne(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] < 9) {
                nums[i]++;
                return nums;
            }
            //逢9进0
            nums[i] = 0;
        }
        //如果这时候还没有返回，表示所有数都为9
        int[] array = new int[len + 1];
        array[0] = 1;
        return array;
    }
    
    public static void main(String[] args) {
         int[] nums = new int[] {9, 9, 9, 9};
         System.out.println("数组输出为:" + Arrays.toString(plusOne(nums)));
    }

}
