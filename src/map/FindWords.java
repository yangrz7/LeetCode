package map;

import java.util.*;

/**
 * 500. 键盘行
 *
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。

 示例：

 输入: ["Hello", "Alaska", "Dad", "Peace"]
 输出: ["Alaska", "Dad"]
  

 注意：

 你可以重复使用键盘上同一字符。
 你可以假设输入的字符串将只包含字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/keyboard-row
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FindWords {

    /**
     * Map映射
     * 1. 将没一行元素和对应的行号存入 map 中，例如 <Q, 1> <A, 2> 等等
     * 2. 遍历数组 words，在遍历每个字符串，判断这些元素是否在一行，如果是则加入 list 中，不是则 break
     * 3. 将 list 转为数组输出即可
     *
     * 复杂度分析：
     * 时间复杂度：O(s * c + w * c), 分别为两次遍历a
     * 空间复杂度：O(m + l), 复杂度为 map 和 list 所占用的空间
     *
     * @param words
     * @return
     */
    public static String[] findWords(String[] words) {
        String[] strs = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
        //将每一行字符和对应的行数放入map中
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            for (char c : chars) {
                map.put(c, i);
            }
        }

        List<String> list = new ArrayList<>();
        for (String word : words) {
            char[] wordChars = word.toUpperCase().toCharArray();
            int line = map.get(wordChars[0]);
            boolean flag = true;
            for (char c : wordChars) {
                if (map.get(c) != line) {
                    flag = false;
                    break;
                }
            }
            if (flag) list.add(word);
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 迭代法
     * 1. 遍历数组 words，在遍历其中每个单词， 判断 a，b, c 中是否包含
     * 2. 设置标识位，如果每次遍历完一个单词，如果 flagA + flagB + flagC > 1, 则一定不在同一行
     * 3. 最后，将 flagA + flagB + flagC == 1 的单词加入 list 中，最后在转为数组即可
     *
     * 复杂度分析：
     * 时间复杂度：O(w * c) 我们只遍历 words 和 word 中的每个单词
     * 空间复杂度：O(l), l 为 list 的大小
     *
     * @param words
     * @return
     */
    public static String[] findWords2(String[] words) {
        String a = "QWERTYUIOP";
        String b = "ASDFGHJKL";
        String c = "ZXCVBNM";

        List<String> list = new ArrayList<>();
        for (String word : words) {
            int flagA = 0;  //用来记录每个元素出现的行号是否一致
            int flagB = 0;
            int flagC = 0;
            for (char c1 : word.toCharArray()) {
                if (a.contains(String.valueOf(c1).toUpperCase())) {
                    flagA = 1;
                } else if (b.contains(String.valueOf(c1).toUpperCase())) {
                    flagB = 1;
                } else if (c.contains(String.valueOf(c1).toUpperCase())) {
                    flagC = 1;
                }
                //每个单词遍历完，如果 flagA + flagB + flagC > 1, 则一定不在同一行
                if (flagA + flagB + flagC > 1) break;
            }
            if (flagA + flagB + flagC == 1) list.add(word);
        }
        return list.toArray(new String[list.size()]);
    }


    public static void main(String[] args) {
         String[] words  = {"Hello", "Alaska", "Dad", "Peace"};
         System.out.println(Arrays.toString(words));
         System.out.println("键盘行:" + Arrays.toString(findWords(words)));
    }

}
