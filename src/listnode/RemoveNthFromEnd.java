package listnode;

/**
 * 19. 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

 示例：

 给定一个链表: 1->2->3->4->5, 和 n = 2.

 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 说明：

 给定的 n 保证是有效的。

 进阶：

 你能尝试使用一趟扫描实现吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class RemoveNthFromEnd {

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
     * 两次遍历算法
     * 1.计算出整个链表的长度size
     * 2.找出要删除节点的前一个节点
     * 3.删除节点即可
     *
     * 复杂度分析：
     * 时间复杂度：O(L),假设链表长度为L，该算法对链表进行了两次遍历，实际操作执行了 2L - n
     * 空间复杂度：O(1),我们只用了常量级的额外空间。
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        if (n == size) return head.next;
        else if (n > size) return head;
        int index = size - n - 1;
        cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        ListNode retNode = cur.next;
        cur.next = retNode.next;
        retNode.next = null;
        return head;
    }

    /**
     * 使用一个虚拟头节点，思路和上个方法一样
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        length -= n;
        ListNode prev = dummyHead;
        while (length > 0) {
            length--;
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return dummyHead.next;
    }

    /**
     * 双指针法
     * 1.创建一个虚拟头节点dummyHead, 两个指针first、second并都指向虚拟头节点
     * 2.first先移动n+1次，然后两个节点同时移动，直到first == null，这时候second指向的就是待删除节点的前一个
     *
     * 复杂度分析：
     * 时间复杂度：O(L),该算法对含有 L 个结点的列表进行了一次遍历。因此时间复杂度为 O(L)。
     * 空间复杂度：O(1),我们只用了常量级的额外空间。
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode first = dummyHead;
        ListNode second = dummyHead;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummyHead.next;
    }


    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);
        System.out.println("删除链表的倒数第N个节点" + removeNthFromEnd3(listNode, 2));
    }

}
