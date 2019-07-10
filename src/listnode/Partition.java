package listnode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 86. 分隔链表
 *
 */
public class Partition {

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
     * 迭代法：
     * 1. 将当前节点值小于 x 的放入一个链表中， 大于 x 的放入一个链表中
     * 2. 将大于 x 的链表拼接到小于 x 的链表之后即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n), n 为链表的长度，我们需要遍历整个链表
     * 空间复杂度：O(x), x 为大于 x 的节点数量，由于我们需要将大于 x 的单独创建节点，所以时间复杂度为 O(x)
     *
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = head;
        ListNode prev = dummyHead;
        ListNode node = new ListNode(0);
        ListNode p = node;
        while (cur != null) {
            if (cur.val >= x) {
                prev.next = cur.next;
                p.next = new ListNode(cur.val);
                p = p.next;
            } else {
                prev = prev.next;
            }
            cur = cur.next;
        }
        prev.next = node.next;
        return dummyHead.next;
    }

    /**
     * 思路同上，只是不在多余创建节点
     *
     * 复杂度分析：
     * 时间复杂度：O(n), n 为链表长度
     * 空间复杂度：O(1)， 不需要单独在创建新的节点，所以只需常数级别的空间复杂度
     *
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition2(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = head;
        ListNode prev = dummyHead;
        ListNode node = new ListNode(0);
        ListNode p = node;
        while (cur != null) {
            if (cur.val >= x) {
                prev.next = cur.next;
                p.next = cur;
                p = p.next;
            } else {
                prev = prev.next;
            }
            cur = cur.next;
        }
        p.next = null;
        prev.next = node.next;
        return dummyHead.next;
    }

    /**
     * 队列法
     * 1. 将原链表中节点大于 x 的压入队列 queue1， 大于的压入 queue2
     * 2. 创建一个新的链表，依次出队然后拼接到新链表即可
     *
     * 时间复杂度：O(n), n 为链表长度
     * 空间复杂度：O(n + n)，时间复杂度为两个队列的空间 + 新链表的长度
     *
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition3(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        Queue<ListNode> queue1 = new LinkedList<>();
        Queue<ListNode> queue2 = new LinkedList<>();
        while (head != null) {
            if (head.val >= x) {
                queue1.add(head);
            } else {
                queue2.add(head);
            }
            head = head.next;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode prev = dummyHead;
        while (!queue2.isEmpty()) {
            prev.next = new ListNode(queue2.poll().val);
            prev = prev.next;
        }
        while (!queue1.isEmpty()) {
            prev.next = new ListNode(queue1.poll().val);
            prev = prev.next;
        }
        return dummyHead.next;
    }
    
    public static void main(String[] args) {
         int[] arr = new int[] {1, 4, 3, 2, 5, 2};
         ListNode listNode = new ListNode(arr);
         System.out.println(listNode);
         System.out.println("分割链表：" + partition3(listNode, 3));
    }

}
