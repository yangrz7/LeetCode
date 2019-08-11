package set;

import java.util.Set;
import java.util.TreeSet;

/**
 * 771. 宝石与石头
 *
 *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。

 J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。

 示例 1:

 输入: J = "aA", S = "aAAbbbb"
 输出: 3
 示例 2:

 输入: J = "z", S = "ZZ"
 输出: 0
 注意:

 S 和 J 最多含有50个字母。
  J 中的字符不重复。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/jewels-and-stones
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class NumJewelsInStones {

    /**
     * 遍历
     * 1. 分别遍历字符串 J ，S， 使用 charAt() 判断如果有相同元素则 num++ 即可
     *
     * 复杂度分析：
     * 时间复杂度：O(m * n): m、n 分别代表字符串的长度
     * 空间复杂度：O(1), 只需要常数级别的空间复杂度
     *
     * @param J
     * @param S
     * @return
     */
    public static int numJewelsInStones(String J, String S) {
        if (J.length() == 0 || S.length() == 0) return 0;
        int num = 0;
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < J.length(); j++) {
                if (S.charAt(i) == J.charAt(j)) {
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * 迭代法
     * 1. 遍历字符串 S，依次取出每个元素；
     * 2. 使用 indexOf() 判断 J 中是否包含该元素
     *
     * 复杂度分析:
     * 时间复杂度：O(n), n 为字符串 S 的长度
     * 空间复杂度：O(1)
     *
     * @param J
     * @param S
     * @return
     */
    public static int numJewelsInStones2(String J, String S) {
        if (J.length() == 0 || S.length() == 0) return 0;
        int num = 0;
        for (int i = 0; i < S.length(); i++) {
            String str = S.substring(i, i + 1);
            if (J.indexOf(str) != -1) num++;
        }
        return num;
    }

    /**
     * set集合法
     * 1. 将字符串依次放入 set 集合中
     * 2. 遍历字符串 S，判断 set 中是否包含相同元素
     *
     * 复杂度分析：
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(m), m 为字符串 J 的长度
     *
     * @param J
     * @param S
     * @return
     */
    public static int numJewelsInStones3(String J, String S) {
        if (J.length() == 0 || S.length() == 0) return 0;
        Set<Character> set = new TreeSet<>();
        int num = 0;
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        for (int j = 0; j < S.length(); j++) {
            if (set.contains(S.charAt(j))) num++;
        }
        return num;
    }
    
    public static void main(String[] args) {
         String J = "aA";
         String S = "aAAbbbb";
         System.out.println(J);
         System.out.println(S);
         System.out.println("宝石与石头：" + numJewelsInStones(J, S));
    }

}
