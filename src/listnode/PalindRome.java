package listnode;

import java.util.Stack;

/**
 * 234. 回文链表
 *
 *请判断一个链表是否为回文链表。

 示例 1:

 输入: 1->2
 输出: false
 示例 2:

 输入: 1->2->2->1
 输出: true
 进阶：
 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？



 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class PalindRome {

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
     * 将链表压入栈，比较对应两个节点 val 是否相等即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n),由于需要开辟一个栈，所以空间复杂度为O(n)
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
            size++;
        }
        int mid = size / 2;
        for (int i = 0; i < mid; i++) {
            if (head.val == stack.peek().val) {
                head = head.next;
                stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 快慢指针 + 反转
     *
     * 1. 使用快慢指针找出中间节点
     * 2. 将后半部分链表反转
     * 3. 与前半部分进行比较，如果相同则是回文链表
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //针对链表长度是奇数或偶数的处理
        ListNode last = fast == null ? slow : slow.next;
        ListNode cur = last;
        ListNode prev = null;
        while (cur != null) {
            ListNode p = cur.next;
            cur.next = prev;
            prev = cur;
            cur = p;
        }
        while (prev != null) {
            if (head.val == prev.val) {
                head = head.next;
                prev = prev.next;
            } else {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
         int[] arr = new int[] {1, 2, 1, 2, 1};
         ListNode listNode = new ListNode(arr);
         System.out.println(listNode);
         System.out.println("是否是回文链表" + isPalindrome2(listNode));
    }


}
