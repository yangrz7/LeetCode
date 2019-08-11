package listnode;

/**
 * 83. 删除排序链表中的重复元素
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

 示例 1:

 输入: 1->1->2
 输出: 1->2
 示例 2:

 输入: 1->1->2->3->3
 输出: 1->2->3

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class DeleteDuplicates {

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
     * 常规解法
     * 1.遍历该链表，如果 cur.val == cur.next.val，则删除掉重复元素
     * 2.如果 cur.val ！= cur.next.val，则将节点向后移动一位
     * 3.那么当到最后一个节点时，就可以删除掉重复元素了
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 快慢指针法
     *
     * 1.初始化两个指针，一个慢指针slow指向head， 而快指针fast指向head.next
     * 2.当slow.var == fast.var 时，删除掉当前fast节点指向的元素，同时将fast向后移
     * 3.当slow.var != fast,var 时，同时将两个指针向后移动一位
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
           if (fast.val != slow.val) {
               fast = fast.next;
               slow = slow.next;
           } else {
               slow.next = fast.next;
               fast = fast.next;
           }
        }
        return head;
    }

    /**
     * 递归法
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = deleteDuplicates3(head.next);
        return head.val == p.val ? p : head;
    }

    /**
     * 递归法
     * 1.假设头节点之后的节点都是没有重复的，那个我们只需判断 heap.next.val 和 heap.val 值是否相同
     * 2.如果相同，返回 heap.next，不同返回 head即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates4(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates4(head.next);
        return head.val == head.next.val ? head.next : head;
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {1, 1, 2, 3, 3, 4};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);
        System.out.println("删除链表中的重复元素" + deleteDuplicates3(listNode));
    }

}
