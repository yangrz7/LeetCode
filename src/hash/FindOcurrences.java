package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1078. Bigram 分词
 *
 */
public class FindOcurrences {

    /**
     * 迭代法
     * 1. 将 text 根据 "" 切分成数组
     * 2. 从第二个元素开始到倒是第二个元素结束，如果满足 str[i].equals(second) && str[i - 1].equals(first),我们直接取 second 后面的一个元素加入 list 即可
     * 3. 遍历 list，将其转化为数组
     *
     * 复杂度分析：
     * 时间复杂度：O(n), 复杂度为遍历数组所用的时间
     * 空间复杂度：O(l), l 为 list 所占用的空间
     *
     * @param text
     * @param first
     * @param second
     * @return
     */
    public static String[] findOcurrences(String text, String first, String second) {
        if (text == null) return null;
        List<String> list = new ArrayList<>();
        String[] str = text.split(" ");
        for (int i = 1; i < str.length - 1; i++) {
            if (str[i].equals(second) && str[i - 1].equals(first)) {
                list.add(str[i + 1]);
            }
        }
        return list.toArray(new String[list.size()]);
    }


    public static void main(String[] args) {
         String text = "alice is a good girl she is a good student";
         String first = "a";
         String second = "good";
         System.out.println("Bigram 分词:" + Arrays.toString(findOcurrences(text, first, second)));
    }

}
