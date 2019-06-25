package string;

/**
 * LeetCode 14
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
     * 该方法多循环了一次 strs[0]的情况，进行改进
     * 1.取出数组中第一个元素遍历每个元素
     * 2.同时遍历数组，如果当前 current != str.char(j) 或者 str.length() < i 返回当前 substring(0, j)即可
     *
     * 复杂度分析：
     * 时间复杂度：O(S)，S为字符串中字符数量的总和
     * 空间复杂度：O（1）
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
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

    /**
     * 改进上面第一种算法
     *
     * 复杂度分析：
     * 时间复杂度：O(S), S为字符串中字符数量的总和
     *           最坏情况下，输入 n 个长度为 m 的相同字符串，复杂度为 S = n * m,
     *           最好情况下，进行 n * lenMin 次比较， lenMin为数组中最短字符串的长度
     *
     * 空间复杂度：O(1)
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
       if (strs == null || strs.length == 0) return "";
       for (int i = 0; i < strs[0].length(); i++) {
           char current = strs[0].charAt(i);
           for (int j = 1; j < strs.length; j++) {
               if (strs[j].length() == i || strs[j].charAt(i) != current) {
                   return strs[0].substring(0, i);
               }
           }
       }
       //这种情况说明每个元素都是相同的
       return strs[0];
    }

    /**
     * 水平扫描法：LCP(S1...Sn) = LCP(LCP(LCP(S1, S2), S3), ...Sn)
     *
     * 1.取出数组中第一个字符串 prefix
     * 2.依次进行遍历字符串，当遍历到第 i 字符串的时候，如果 strs[i].indes(prefix) != 0，则每次缩减 prefix 字符串长度，直到匹配为止
     *
     * 复杂度分析：
     * 时间复杂度：时间复杂度：O(S)，S 是所有字符串中字符数量的总和。
     * 最坏的情况下，n 个字符串都是相同的。算法会将 S1 与其他字符串都做一次比较。这样就会进行 S 次字符比较，其中 S 是输入数据中所有字符数量。

     * 空间复杂度：O(1)，我们只需要使用常数级别的额外空间。
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                //这种情况表示没有相同的公共前缀，返回""即可
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    /**
     * 使用分治递归思想
     *
     *       leetcode   leet   lee  le
     *                    |
     *      mid = (left + right) / 2 = 1
     *            |              |
     *     LCP(left, mid)  LCP(mid + 1, right)
     *            |              |
     *          leet            le
     *                    |
     *                   le
     *
     * n 个长度为 m 的相同字符串
     * 复杂度分析：O(S) 其中 S = m * n
     * 空间复杂度：O(m * logn)
     *           （n/(2^x)）=1  x = logn;
     *            内存开支主要是递归过程中使用的栈空间所消耗的。 一共会进行 log(n)次递归，每次需要 m 的空间存储返回结果，所以空间复杂度为 O(m⋅log(n))。
     *
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix4(strs, 0, strs.length - 1);
    }

    public static String longestCommonPrefix4(String[] strs, int left, int right) {
        if (left == right) return strs[right];
        int mid = (left + right) / 2;
        String lcpLeft = longestCommonPrefix4(strs, left, mid);
        String lcpRight = longestCommonPrefix4(strs, mid + 1, right);
        return commonPrefix(lcpLeft, lcpRight);
    }

    private static String commonPrefix(String lcpLeft, String lcpRight) {
        int min = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < min; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, min);
    }

    /**
     * 二分法
     *
     *        {leets, leetcode, leetc , leeds}
     *                        |
     *                       leets
     *                 |              |
     *                lee             ts
     *                 |
     *            in leetcode
     *            in leetc
     *            in leeds            |
     *                             |     |
     *                             t     s
     *                 leet in leetcode
     *                 leet in leetc
     *                 leet not leeds
     *                        |
     *                       lee
     *
     * 复杂度分析：最坏情况下，我们有 n 个长度为 m 的相同字符串。
     * 时间复杂度：O(S*logn)
     *           算法一共会进行 logn 次迭代， 每次又进行了 S = m * n 次比较，所以总的时间复杂度为：S*logn
     * 空间复杂度：O(1)
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix5(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        if (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix5(strs, middle)) {
                low = middle + 1;
            } else {
                low = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private static boolean isCommonPrefix5(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
         String[] strs = new String[] {"leetcode", "leet", "lee", "le"};
         System.out.println("最长公共前缀为：" + longestCommonPrefix5(strs));
    }

}
