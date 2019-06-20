package string;

/**
 * 实现 strStr() 函数。

 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

 示例 1:

 输入: haystack = "hello", needle = "ll"
 输出: 2
 示例 2:

 输入: haystack = "aaaaa", needle = "bba"
 输出: -1
 说明:

 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 */

public class StrStr {

    /**
     * 使用String的indexOf返回相应位置即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        int needLen = needle.length();
        if (needLen == 0) return 0;
        if (haystack.contains(needle)) {
            return haystack.indexOf(needle);
        }
        return -1;
    }

    /**
     * 遍历查询在haystack中是否存在needle字符串
     *
     * 复杂度分析：
     * 时间复杂度：O(n * n)
     * 空间复杂度：O(1)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr2(String haystack, String needle) {
        if ("".equals(needle)) return 0;
        if (needle.length() > haystack.length()) return -1;

        for (int i = 0; i < haystack.length(); i++) {
            //当haystack中剩余的子串小于needle长度时，返回-1即可
            if ((haystack.length() - i) < needle.length()) return -1;
            if (haystack.charAt(i) == needle.charAt(0)) {
                boolean flag = true;
                for (int j = 1; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return i;
            }
        }
        return -1;
    }

    /**
     * 利用substring
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr3(String haystack, String needle) {
        int len = needle.length();
        if (haystack.length() < len) return -1;
        if (len == 0) return 0;

        for (int i = 0; i < haystack.length(); i++) {
            if (len + i <= haystack.length() && haystack.substring(i, len + i).equals(needle)) return i;
        }
        return -1;
    }
    
    public static void main(String[] args) {
         System.out.println("索引为：" + strStr3("hello", "ll"));
    }

}
