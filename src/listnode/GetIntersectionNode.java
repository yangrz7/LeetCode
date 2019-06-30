package listnode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 160. 相交链表
 *
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 示例 1：

 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 输出：Reference of the node with value = 8
 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

 注意：

 如果两个链表没有交点，返回 null.
 在返回结果后，两个链表仍须保持原有的结构。
 可假定整个链表结构中没有循环。
 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class GetIntersectionNode {

    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 暴力解法
     *
     * 1. 分别计算出链表 A 和 B 的长度 lenA 和 lenB
     * 2. |lenA - lenB| 计算出长度差 abs，让长度较长的链表先走 abs
     * 3. 在比较 pA == pB 相等返回即可
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA;
        ListNode pB = headB;
        int lenA = 0, lenB = 0;
        while (pA != null) {
            lenA++;
            pA = pA.next;
        }
        while (pB != null) {
            lenB++;
            pB = pB.next;
        }
        pA = headA;
        pB = headB;
        int dif = lenA - lenB;
        if (dif > 0) {
            while (dif != 0) {
                dif--;
                pA = pA.next;
            }
        } else {
            int abs = Math.abs(dif);
            while (abs != 0) {
                abs--;
                pB = pB.next;
            }
        }
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }


    /**
     * 双指针法
     *
     * 1.初始化两个指针 pA pB 分别指向两个链表的头节点
     * 2.分别遍历这两个链表，如果 pA == null，则把 pA = headB；而 pb == null 时，把 pB = headA
     *   这样做的目的是消除两个链表的长度差
     * 3.当 pA == pB 时，就是我们要找的相交链表
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 使用set
     * 1.将链表 A 添加到set集合中
     * 2.遍历链表 B， 判断set中是否包含 pB, 如果包含，返回pB即可，不包含则 pB = pB.next
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n), 由于使用了set 存储链表元素，所以空间复杂度为O(n)
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Set<ListNode> set = new HashSet<>();
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != null) {
            set.add(pA);
            pA = pA.next;
        }
        while (pB != null) {
            if (set.contains(pB)) {
                return pB;
            } else {
                pB = pB.next;
            }
        }
        return null;
    }

    /**
     * 入栈法
     * 1. 将两个链表分别压入栈
     * 2. 每次比较栈顶元素是否相同， 如果相同，则出栈，直到元素不相同位置，返回上一个出栈的元素即可
     *
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)，由于开辟了多余的栈空间，导致时间复杂度很高
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != null) {
            stackA.push(pA);
            pA = pA.next;
        }
        while (pB != null) {
            stackB.push(pB);
            pB = pB.next;
        }
        ListNode res = null;
        while (!stackA.empty() && !stackB.empty() && stackA.peek() == stackB.peek()) {
            res = stackA.peek();
            stackA.pop();
            stackB.pop();
        }
        return res;
    }

}
