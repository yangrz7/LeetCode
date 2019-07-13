package listnode;

import java.util.Stack;

/**
 * 143. 重排链表
 *
 */
public class ReorderList {

    private static class ListNode {
        int val;
        ListNode next;

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
     * 反转+指针
     *
     * 1. 先使用快慢指针找到整个链表的中点
     * 2. 将中点后面的后半部分链表进行反转
     * 3. 将反转后的链表拼接到原链表即可
     *
     * 注意：找到链表中点之后，一定要将后面置空
     *
     * 复杂度分析：
     * 时间复杂度：O(n + n/2): n为链表长度, 复杂度为两次遍历的时间和
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    private static ListNode reordList(ListNode head) {
        if (head == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //将后半部分链表反转
        ListNode rev = reverse2(slow.next);
        //将链表断开
        slow.next = null;
        ListNode cur = head;
        while (rev != null) {
            ListNode p = rev.next;
            rev.next = cur.next;
            cur.next = rev;
            rev = p;
            cur = cur.next.next;
        }
        return head;
    }

    /**
     * 入栈法：
     * 1. 使用快慢指针找到链表中点
     * 2. 将链表中点之后的元素压入栈
     * 3. 依次将元素出栈（目的是反转）然后进行拼接即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n + n / 2 + n / 2), 时间复杂度是遍历链表 + 遍历右半部分链表 + 遍历栈
     * 空间复杂度：O(n / 2 + n / 2), 空间复杂度为 栈所占用的空间 + 新建的链表所占用的空间
     *
     * @param head
     * @return
     */
    private static ListNode reordList2(ListNode head) {
        if (head == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode next = slow.next;
        while (next != null) {
            stack.push(next);
            next = next.next;
        }
        slow.next = null;
        ListNode cur = head;
        while (!stack.isEmpty()) {
            ListNode node = new ListNode(stack.pop().val);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }
        return head;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private static ListNode reverse(ListNode head) {
        ListNode prev  = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 递归反转
     * @param head
     * @return
     */
    private static ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    
    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);
        System.out.println("重排链表" + reordList2(listNode));
    }

}
