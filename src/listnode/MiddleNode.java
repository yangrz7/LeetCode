package listnode;

/**
 * 876. 链表的中间结点
 *
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。

 如果有两个中间结点，则返回第二个中间结点。

  

 示例 1：

 输入：[1,2,3,4,5]
 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 示例 2：

 输入：[1,2,3,4,5,6]
 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MiddleNode {

    private static class ListNode {

        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        //测试用例
        public ListNode (int[] arr) {
            if (arr == null || arr.length == 0) throw  new NullPointerException("array is empty");
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
     * 1.遍历该链表，计算该链表的大小 size
     * 2.求出最中间的位置 mid = size / 2
     * 3.找到 mid 索引所对应的节点， 返回该节点即可
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        int mid = size / 2;
        ListNode p = head;
        for (int i = 0; i < mid; i++) {
            p = p.next;
        }
        return p;
    }

    /**
     * 快慢指针法
     *
     * 1.新建两个指针 fast、slow，让fast指针的移动速度是slow的2倍
     * 2.那么当链表长度为奇数时，当fast到最后，slow正好在中间，即为我们需要的
     * 3.当链表长度为偶数时，fast正好指向最后一个节点的下一个节点，而slow只需在向后移动一个位置即可
     *
     * 复杂度分析：
     * 时间复杂度：O(N)，其中 N 是给定列表的结点数目。
     * 空间复杂度：O(1)，slow 和 fast 用去的空间。
     *
     * @param head
     * @return
     */
    public static ListNode middleNode2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 数组法
     *
     * 1.创建一个ListNode数组，将链表中的元素一次添加到数组中
     * 2.取出数组中最终间的元素即可
     *
     * 复杂度分析：
     * 时间复杂度：O(N)，其中 N 是给定列表的结点数目。
     * 空间复杂度：O(n)，n为数组用去的空间
     *
     * @param head
     * @return
     */
    public static ListNode middleNode3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode[] arr = new ListNode[100];
        int index = 0;
        ListNode cur = head;
        while (cur != null) {
            arr[index++] = cur;
            cur = cur.next;
        }
        return arr[index / 2];
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);
        System.out.println("链表的中间节点为：" + middleNode2(listNode));
    }

}
