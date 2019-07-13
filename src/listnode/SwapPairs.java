package listnode;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 示例:

 给定 1->2->3->4, 你应该返回 2->1->4->3.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SwapPairs {

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
     * 双指针法：
     * 1. 我们维护一个头节点 prev, 有头节点创建两个指针 slow = prev.next, fast = prev.next.next
     * 2. 交换 slow 和 fast 节点的位置
     * 3. 交换完成之后将 slow 节点指向 prev, 用于交换下一组节点
     *
     * 复杂度分析：
     * 时间复杂度：O(n), n为链表长度
     * 空间复杂度：O(1), 只需要常数级别的空间复杂度
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null && prev.next.next != null) {
            ListNode slow = prev.next;
            ListNode fast = prev.next.next;
            slow.next = fast.next;
            prev.next = fast;
            fast.next = slow;
            prev = slow;
        }
        return dummyHead.next;
    }

    /**
     * 递归法
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }
    
    public static void main(String[] args) {
         int[] arr = new int[] {1, 2, 3, 4};
         ListNode listNode = new ListNode(arr);
         System.out.println(listNode);
         System.out.println("两两交换链表中的节点" + swapPairs2(listNode));
    }

}
