package listnode;

/**
 * 2. 两数相加
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 示例：

 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/add-two-numbers
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class AddTwoNumbers {

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
     * 数学方法解决
     *
     * 1. 遍历两个链表，分别计算每个节点的和，并把计算的和 sum % 10 插入到一个新的链表中
     * 2. 如果相加的和 >10 需要进位的，则把 sum/10 存储下来加入到下个节点的和中
     * 3. 如果最后一位相加还需要进位的情况，则直接把余数 post 插入到新链表的末尾即可
     * 4. 最后只需要返回虚拟头节点的 dummyHead.next 即可
     *
     * 注意 ：要小心链表大小不一的情况，获取空链表的变量可能会出现 NullPointerException
     *
     * 复杂度分析：
     * 时间复杂度：O(max(m, n))， 假设 m 和 n 分别表示 l1 和 l2 的长度，算法最多执行 max(m, n)次
     * 空间复杂度：O(max(m, n))， 新链表的长度最多为 max(m, n) + 1
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode prev = dummyHead;
        int post = 0; //余数
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + post;
            post = sum / 10;
            prev.next = new ListNode(sum % 10);
            prev = prev.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        //最后两个节点相加进位的情况
        if (post > 0) prev.next = new ListNode(post);
        return dummyHead.next;
    }

    /**
     * 递归法
     *
     * 复杂度分析：
     * 时间复杂度：O(max(m, n))， 假设 m 和 n 分别表示 l1 和 l2 的长度，算法最多执行 max(m, n)次
     * 空间复杂度：O(max(m, n))， 新链表的长度最多为 max(m, n) + 1
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    public static ListNode add(ListNode l1, ListNode l2, int post) {
        if (l1 == null && l2 == null && post == 0) return null;
        int x = l1 != null ? l1.val : 0;
        int y = l2 != null ? l2.val : 0;
        int sum = x + y + post;
        ListNode node = new ListNode(sum % 10);
        node.next = add(l1 != null ? l1.next : null, l2 != null ? l2.next : null, sum / 10);
        return node;
    }

    public static void main(String[] args) {
         int[] arr1 = new int[] {2, 4, 2};
         int[] arr2 = new int[] {5, 6, 4};
         ListNode listNode1 = new ListNode(arr1);
         ListNode listNode2 = new ListNode(arr2);
         System.out.println(listNode1);
         System.out.println(listNode2);
         System.out.println("两数相加" + addTwoNumbers2(listNode1, listNode2));
    }

}
