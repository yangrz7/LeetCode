package string;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。

 输入为非空字符串且只包含数字 1 和 0。

 示例 1:

 输入: a = "11", b = "1"
 输出: "100"
 示例 2:

 输入: a = "1010", b = "1011"
 输出: "10101"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/add-binary
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class AddBinary {

    /**
     * 1.将2个字符串，较短的用0补齐，使其长度一致
     * 2.计算时拼接字符串，得到一个反向字符，之后在进行反转即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            builder.append(sum % 2);
            ca = sum / 2;
        }
        builder.append(ca == 1 ? 1 : "");
        return builder.reverse().toString();
    }
    
    public static void main(String[] args) {
         System.out.println("二进制相加：" + addBinary("11", "1"));
    }

}
