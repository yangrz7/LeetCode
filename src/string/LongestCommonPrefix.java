package string;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 说明:

 所有输入只包含小写字母 a-z 。
 *
 */

public class LongestCommonPrefix {

    /**
     * 思路：
     * 1.取出数组中第一个元素遍历每个元素
     * 2.同时遍历数组，如果当前 current != str.char(j) 或者 str.length() < i 返回当前 substring(0, j)即可
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        int index = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            char current = strs[0].charAt(i);
            for (String str : strs) {
                if (str.length() == i || str.charAt(index) != current) {
                    return str.substring(0, index);
                }
            }
            index++;
        }
        //这种情况说明每个元素都是相同的
        return strs[0].substring(0, index);
    }
    
    public static void main(String[] args) {
         String[] strs = new String[] {"dog","dacecar","dar"};
         System.out.println("最长公共前缀为：" + longestCommonPrefix(strs));
    }

}
