package listnode;

/**
 * 328. 奇偶链表
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

 示例 1:

 输入: 1->2->3->4->5->NULL
 输出: 1->3->5->2->4->NULL
 示例 2:

 输入: 2->1->3->5->6->4->7->NULL
 输出: 2->3->6->7->1->5->4->NULL
 说明:

 应当保持奇数节点和偶数节点的相对顺序。
 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class OddEvenList {

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
     * 双指针法
     * 1.使用一个快指针fast，每次fast = fast.next.next, 所以fast走的移动是奇数的节点
     * 2.将fast节点删除掉，在cur后面添加一个fast.val的节点即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n),需要编译一次整个链表
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head;
        ListNode cur = head;
        ListNode prev = head;
        while (fast.next!= null && fast.next.next != null) {
            prev = prev.next;
            fast = fast.next.next;
            ListNode node = new ListNode(fast.val);
            prev.next = fast.next;
            node.next = cur.next;
            cur.next = node;
            cur = cur.next;
        }
        return head;
    }

    /**
     *
     * 最优解
     * 1.将奇节点放在一个链表中，偶节点放入一个链表中
     * 2.将偶节点链表插入奇节点链表即可
     *
     * 复杂度分析：
     * 时间复杂度： O(n),总共有 n 个节点，我们每个遍历一次。
     * 空间复杂度： O(1),我们只需要 4 个指针。
     *
     * @param head
     * @return
     */
    public static ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
         int[] arr = new int[] {1, 2, 3, 4, 5};
         ListNode listNode = new ListNode(arr);
         System.out.println(listNode);
         System.out.println("奇偶链表" + oddEvenList2(listNode));
    }

}
