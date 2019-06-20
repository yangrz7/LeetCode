package stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

   有效字符串需满足：

   左括号必须用相同类型的右括号闭合。
   左括号必须以正确的顺序闭合。
   注意空字符串可被认为是有效字符串。

   输入: "()"      输入: "([)]"
   输出: true      输出: false

   来源：力扣（LeetCode）
   链接：https://leetcode-cn.com/problems/valid-parentheses
   著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class EffectiveBrackets {

    private HashMap<Character, Character> map;

    public EffectiveBrackets() {
        map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
    }

    /**
     * 官方解法
     * 复杂度分析
       时间复杂度：O(n)，因为我们一次只遍历给定的字符串中的一个字符并在栈上进行 O(1)的推入和弹出操作。
       空间复杂度：O(n)，当我们将所有的开括号都推到栈上时以及在最糟糕的情况下，我们最终要把所有括号推到栈上。例如 ((((((((((。
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (this.map.containsKey(c)) {
                char topChar = stack.isEmpty() ? '#' : stack.pop();
                if (topChar != this.map.get(c)) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 使用栈的先进后出特性
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c || '{' == c || '[' == c) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                //取出栈顶元素进行匹配
                char topChar = stack.pop();   
                if (')' == c && '(' != topChar) return false;
                if ('}' == c && '{' != topChar) return false;
                if (']' == c && '[' != topChar) return false;
            }
        }
        return stack.isEmpty();
    }
    
    
    public static void main(String[] args) {
        EffectiveBrackets brackets = new EffectiveBrackets();
         System.out.println("()是否有效" + brackets.isValid2("()"));
    }

}
