package listnode;

/**
 * LeetCode 206
 *
 * 反转一个单链表。

 示例:

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL
 进阶:
 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseList {

    public static class ListNode {

        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int[] arr) {
            if (arr == null || arr.length == 0) throw new NullPointerException("array is Empty");
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
     *
     * 迭代法：遍历该链表，每次将 val 添加到一个新的链表的最前面即可完成反转
     *        但是，该方法会超出时间限制
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newNode = null;
        while (head != null) {
            ListNode node = new ListNode(head.val);
            node.next = newNode;
            newNode = node;
            head = head.next;
        }
        return newNode;
    }

    /**
     *    1 -> 2 -> 3 -> null
     *         |
     *   prev : 1 -> null
     *         |
     *   prev : 2 -> 1 -> null
     *         |
     *   prev : 3 -> 2 -> 1 -> null
     *
     *
     * 改进后的迭代法：将当前节点指向的下一个节点改为指向前一个节点即可
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode p = cur.next;
            cur.next = prev;
            prev = cur;
            cur = p;
        }
        return prev;
    }

    /**
     * 使用递归
     * 复杂度分析：
     * 时间复杂度：O(n)，假设 n 是列表的长度，那么时间复杂度为 O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 n 层。
     *
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        System.out.println("反转后的链表" + reverseList2(head));
    }

}
