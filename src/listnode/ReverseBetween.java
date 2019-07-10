package listnode;

/**
 * 92. 反转链表 II
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

 说明:
 1 ≤ m ≤ n ≤ 链表长度。

 示例:

 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 输出: 1->4->3->2->5->NULL

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ReverseBetween {

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
     * 使用递归反转：
     *
     * 1.找到要反转链表，从原来的链表中删除掉
     * 2.反转分离出来的链表
     * 3.将反转之后的链表拼接到原来的链表中
     *
     * 复杂度分析：
     * 时间复杂度：O(n), 其中n为原链表的长度
     * 空间复杂度：O((n-m+1) + (n-m+1), 复杂度为递归的复杂度加上反转链表所占用的空间
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(0);
        ListNode prev = dummyHead;
        ListNode dum = new ListNode(0);
        dum.next = head;
        ListNode p = dum;
        ListNode cur = p;
        for (int i = 1; i < m; i++) {
            p = p.next;
            cur = p;
        }
        for (int j = m; j <= n; j++) {
            p = p.next;
            prev.next = new ListNode(p.val);
            prev = prev.next;
        }
        //删除掉节点
        cur.next = p.next;
        ListNode rev = reverse(dummyHead.next);
        cur.next.next = p.next;
        cur.next = rev;
        return dum.next;
    }
    
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 不使用递归
     * 思路同上
     *
     * 复杂度分析：
     * 时间复杂度：O(n), 其中n为链表的长度
     * 空间复杂度：O(1)
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummHead = new ListNode(0);
        dummHead.next = head;
        ListNode prev = dummHead;
        //找到待反转的前一个节点
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }
        ListNode node = null;
        ListNode cur = prev.next;
        for (int i = 0; i < n -m + 1; i++) {
            ListNode p = cur.next;
            cur.next = node;
            node = cur;
            cur = p;
        }
        prev.next.next = cur;
        prev.next = node;
        return dummHead.next;
    }

    /**
     * 双指针法：
     * 1. 找到待反转链表之前的那个节点 prev
     * 2. 初始化两个指针 start = prev.next, tail = start.next
     * 3. 调换位置，完成反转
     *
     * 复杂度分析：
     * 时间复杂度：
     * 空间复杂度：
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween3(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        //找到带反转的前一个节点
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }
        ListNode start = prev.next;
        ListNode tail = start.next;
        for (int i = 0; i < n - m; i++) {
            start.next = tail.next;
            tail.next = prev.next;
            prev.next = tail;
            tail = start.next;
        }
        return dummyHead.next;
    }
    
    public static void main(String[] args) {
         int[] arr = new int[] {1, 2, 3, 4, 5};
         ListNode listNode = new ListNode(arr);
         System.out.println(listNode);
         System.out.println("反转链表" + reverseBetween3(listNode, 2, 4));
    }

}
