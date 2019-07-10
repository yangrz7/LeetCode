package listnode;

/**
 * 82. 删除排序链表中的重复元素 II
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

 示例 1:

 输入: 1->2->3->3->4->4->5
 输出: 1->2->5
 示例 2:

 输入: 1->1->1->2->3
 输出: 2->3

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class DeleteDuplicates2 {

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
     * 1. 初始化两个指针，slow 指向虚拟头节点 dummyHead, fast 指向头节点 dummyHead.next
     * 2. 每次判断 fast.val == fast.next.val 是否相等，如果不相等，slow fast 均指向下一个节点
     * 3. 如果相等的话，那么一定存在相同的元素，只移动 fast 指针，然后将相同的节点全部删除 slow.next = fast.next
     *
     * 复杂度分析：
     * 时间复杂度：O(n), n为链表的长度
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode slow = dummyHead;
        ListNode fast = dummyHead.next;
        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val) fast = fast.next;
            if (slow.next == fast) {
                slow = slow.next;
            } else {   //这种情况一定是存在了相同的元素
                slow.next = fast.next;
            }
            fast = fast.next;
        }
        return dummyHead.next;
    }

    /**
     * 递归法：
     * 从宏观理解
     *
     * 复杂度分析：
     * 时间复杂度：O(n), n为链表长度
     * 空间复杂度：O(n), 递归
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) head = head.next;
            return deleteDuplicates2(head.next);
        } else {
            head.next = deleteDuplicates2(head.next);
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 3, 4, 4, 5};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);
        System.out.println("删除排序链表中的重复元素" + deleteDuplicates2(listNode));
    }

}
