package stack;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

 示例 1:

 输入: 121
 输出: true
 *
 */
public class Palindrome {

    /**
     * 暴力解法
     *
     * 将int转为字符串
     * 但是，这需要额外的非常量空间来创建问题描述中所不允许的字符串。
     * @param x
     * @return
     *
     * 复杂度分析：
     * 时间复杂度 ： O(n)
     * 空间复杂度 ： O(1)
     *
     */
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) == str.charAt(str.length() - i - 1)) return true;
        }
        return false;
    }

    /**
     * 最优解
     *
     *  1.每次都进行取余操作， y = x % 10;
     *  2.每次取出最低位的数字并组合起来 ： revertNum = revertNum * 10 + x % 10;
     *  3.之后每次将 x / 10，以便下次进行取余
     *  4.判断 x 是否 <或=  revertNum , 如果是，则说明已经进行了一半或者以上，事实证明，取出一半即可判断是否位回文数了
     *  5.如果 x 长度是偶数 revertNum == x即可判断， 但是若为奇数，则须 revertNum / 10 == x， revertNum / 10 是为了去掉最中间的那个数
     *
     *  复杂度分析：
     *
     *  时间复杂度： O(log10(n))，对于每次迭代，我们会将输入除以10，因此时间复杂度为 O(log10(n))
     *  空间复杂度： O(1)
     *
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int revertNum = 0;
        while (x > revertNum) {
            revertNum = revertNum * 10 + x % 10;
            x /= 10;
        }
        return revertNum == x || revertNum / 10 == x;
    }
    
    public static void main(String[] args) {
         Palindrome palindrome = new Palindrome();
         System.out.println("121是否为回文数：" + palindrome.isPalindrome2(121));
    }

}
