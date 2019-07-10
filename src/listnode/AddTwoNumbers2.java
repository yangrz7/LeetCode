package listnode;
import java.util.Stack;

/**
 * 445. 两数相加 II
 *
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。

 你可以假设除了数字 0 之外，这两个数字都不会以零开头。

 进阶:

 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

 示例:

 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出: 7 -> 8 -> 0 -> 7

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class AddTwoNumbers2 {

    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int[] arr) {
            if (arr == null || arr.length == 0) throw new NullPointerException("array is empty");
            this.val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                res.append(cur.val + "->");
                cur = cur.next;
            }
            res.append("NULL");
            return res.toString();
        }
    }

    /**
     * 使用递归 + 反转
     *
     * 1. 将每个链表都进行反转，之后按照之前的方法一次相加
     * 2. 相加之后在进行反转一次即可
     *
     * 复杂度分析：
     * 时间复杂度：O(max(m , n))， m 和 n 表示两个链表的长度
     * 空间复杂度：O(max(m, n) + （(m + n）+ max(m, n)）), 空间复杂度需要将递归的复杂度加上新的节点占用的空间
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reverseL1 = reverse(l1);
        ListNode reverseL2 = reverse(l2);
        return reverse(add(reverseL1, reverseL2, 0));
    }

    public static ListNode add(ListNode l1, ListNode l2, int post) {
        if (l1 == null && l2 == null && post == 0) return null;
        int x = l1 == null ? 0 : l1.val;
        int y = l2 == null ? 0 : l2.val;
        int sum = x + y + post;
        ListNode node = new ListNode(sum % 10);
        node.next = add(l1 == null ? null : l1.next, l2 == null ? null : l2.next, sum / 10);
        return node;
    }

    /**
     * 入栈 + 反转
     *
     * 1. 新建两个栈，并将两个链表的元素一次入栈
     * 2. 每次出栈栈顶元素相加，步骤同上方法
     * 3. 最后将新的链表反转一次即可
     *
     * 复杂度分析：
     * 时间复杂度：O(max(m , n))， m 和 n 表示两个链表的长度
     * 空间复杂度：O(max(m, n) + (m + n) + max(m, n)), 空间复杂度需要将栈的复杂度O(m + n)加上新的节点占用的空间O(max(m, n))
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode dummyHead = new ListNode(0);
        ListNode prev = dummyHead;
        int mod = 0;
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        while (!stack1.isEmpty() || !stack2.isEmpty() || mod != 0) {
            int x = stack1.isEmpty() ? 0 : stack1.peek().val;
            int y = stack2.isEmpty() ? 0 : stack2.peek().val;
            int sum = x + y + mod;
            mod = sum / 10;
            prev.next = new ListNode(sum % 10);
            prev = prev.next;
            if (!stack1.isEmpty()) stack1.pop();
            if (!stack2.isEmpty()) stack2.pop();
        }
        return reverse(dummyHead.next);
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 数学方法：
     *
     * 链表可能存在几种情况：
     * 1. 链表长度相同，不需要进行处理
     * 2. 一个链表为空，直接返回另一个链表即可
     * 3. 链表长度不同，在短的链表前面补0，直到连个链表长度相同
     *
     * 步骤：
     * 1. 将链表补齐后，依次相加，暂时先不要处理进位的情况
     * 2. 从尾节点到头节点依次遍历，遇到需要进位的情况，就将前一个节点加上 mod
     * 3. 返回该链表即可
     *
     * 复杂度分析：
     *  时间复杂度：O(m + n + max(m , n))， m 和 n 表示两个链表的长度
     *  空间复杂度：O(max(m, n)), 最多只需要占用max(m, n)用来存储新的节点
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
         if (l1 == null) return l2;
         if (l2 == null) return l1;
         ListNode cur1 = l1;
         ListNode cur2 = l2;
         ListNode dummyHead = new ListNode(0);
         ListNode prev = dummyHead;
         while (cur1 != null && cur2 != null) {
             cur1 = cur1.next;
             cur2 = cur2.next;
         }
         if (cur1 != null) {    //说明l1长度大于l2，将l2前面补齐
             while (cur1 != null) {
                 prev.next = new ListNode(0);
                 prev = prev.next;
                 cur1 = cur1.next;
             }
             //将部位好的链表和短的链表进行拼接
             prev.next = l2;
             cur1 = l1;
             cur2 = dummyHead.next;
         } else {   //l2长度大于l1，将l1前面补齐
             while (cur2 != null) {
                 prev.next = new ListNode(0);
                 prev = prev.next;
                 cur2 = cur2.next;
             }
             prev.next = l1;
             cur2 = l2;
             cur1 = dummyHead.next;
         }

         //链表进行相加，暂时先不考虑进位情况
        ListNode sumHead = new ListNode(0);
        ListNode prevSum = sumHead;
        while (cur1 != null) {
            prevSum.next = new ListNode(cur1.val + cur2.val);
            cur1 = cur1.next;
            cur2 = cur2.next;
            prevSum = prevSum.next;
        }

        int mod = 0;   //余数
        //从尾节点开始，处理进位情况
        while (prevSum != sumHead) {
            int sum = prevSum.val + mod;
            mod = sum / 10;
            prevSum.val = sum % 10;
            //需要找到prevSum之前的一个节点，用于向前遍历新链表
            ListNode front = sumHead;
            while (front.next != prevSum) {
                front = front.next;
            }
            prevSum = front;
        }

        //处理最后还需要进位的情况
        if (mod > 0) {
            ListNode finalNode = new ListNode(mod);
            finalNode.next = sumHead.next;
            sumHead.next = finalNode;
        }
        return sumHead.next;
    }

    public static void main(String[] args) {
         int[] arr1 = new int[] {7, 2, 4, 3};
         int[] arr2 = new int[] {5, 6, 4};
         ListNode listNode1 = new ListNode(arr1);
         System.out.println(listNode1);
         ListNode listNode2 = new ListNode(arr2);
         System.out.println(listNode2);
         System.out.println("两数相加：" + addTwoNumbers3(listNode1, listNode2));
    }

}
