package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1002. 查找常用字符
 *
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

 你可以按任意顺序返回答案。

  

 示例 1：

 输入：["bella","label","roller"]
 输出：["e","l","l"]
 示例 2：

 输入：["cool","lock","cook"]
 输出：["c","o"]
  

 提示：

 1 <= A.length <= 100
 1 <= A[i].length <= 100
 A[i][j] 是小写字母


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-common-characters
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class CommonChars {

    /**
     * 迭代法
     * 1. 拿出数组 A 中的第一个元素，遍历其中每个元素
     * 2. 将其转为 String 类型，依次和 A 中其他元素比较，判断是否包含
     * 3. 如果包含，将其添加到结果 list 中，并将该元素替换成 ""
     * 注意：要使用 replaceFirst() 替换第一个出现的元素，而不能是全部替换
     *
     * 复杂度分析：
     * 时间复杂度：O(a * n), 需要同时遍历 A 和 A中每个元素的子元素
     * 空间复杂度：O(n), list需要占用 n 个空间
     *
     * @param A
     * @return
     */
    public static List<String> commonChars(String[] A) {
        List<String> list = new ArrayList<>();
        if (A == null || A.length == 0) return list;
        for (int i = 0; i < A[0].length(); i++) {
            String value = String.valueOf(A[0].charAt(i));
            boolean flag = true;
            for (int j = 1; j < A.length; j++) {
                if (A[j].contains(value)) {
                    A[j] = A[j].replaceFirst(value, "");
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) list.add(value);
        }
        return list;
    }

    /**
     * 映射法
     * 1. 使用一个数组，将元素和相应出现的次数添加到数组中，eg："aabc" = [2, 1, 1]
     * 2. 依次和数组 A 中的其他元素求交集，如果 res[j] == 0 || temp[j] == 0，说明元素并不是重复的
     * 3. 反之，我们要将 res 中该元素出现的数量修改为 和 temp 相比的最小值，eg: "aabc" --> [2, 1, 1],  "abc" ---> [1, 1, 1], 重复元素只有 "abc" --> [1, 1, 1]
     * 4. 最后，将数组遍历，取出我们需要的元素转为 list 即可
     *
     * 复杂度分析：
     * 时间复杂度：(n + a * (n + n) + n * c), 其中 n 表示数组 A 中每个元素的长度， a 表示 A.length, c 表示重复元素的数量
     * 空间复杂度：O(n)
     *
     * @param A
     * @return
     */
    public static List<String> commonChars2(String[] A) {
        //记录每个字符出现的情况
        List<String> list = new ArrayList<>();
        if (A == null || A.length == 0) return list;
        int[] res = new int[26];
        for (char c : A[0].toCharArray()) {
            res[c - 'a']++;
        }
        for (int i = 1; i < A.length; i++) {
            int[] temp = new int[26];
            for (char c : A[i].toCharArray()) {
                temp[c - 'a']++;
            }
            //求交集
            for (int j = 0; j < 26; j++) {
                if (res[j] == 0 || temp[j] == 0) {
                    res[j] = 0;
                } else {
                    res[j] = Math.min(res[j], temp[j]);
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (res[i] != 0) {
                for (int j = 0; j < res[i]; j++) {
                    list.add((char) ('a' + i) + "");
                }
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
        String[] A = {"cool","lock","cook"};
        System.out.println(Arrays.toString(A));
        System.out.println("查找常用字符:" + commonChars2(A));
    }

}
