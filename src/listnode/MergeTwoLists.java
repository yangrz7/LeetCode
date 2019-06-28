package listnode;

public class MergeTwoLists {

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
     * 迭代法
     *
     * 1.创建一个虚拟头节点 dummyhead, 并维护一个指针 prev
     * 2.如果 l1.val <= l2.val 的话，将 prev.next -> l1, 并将 l1 -> l1.next
     * 3.如果 l2.val > l1.val, 将 prev.next -> l2, 并将 l2 -> l2.next
     * 4.最后判断 l1 是否为空， 如果是则将 prev.next -> l2, 否则 prev.next -> l1;
     *
     * 复杂度分析：
     * 时间复杂度：O(m + n), 因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中，
     *            while 循环的次数等于两个链表的总长度。所有其他工作都是常数级别的，所以总的时间复杂度是线性的。
     * 空间复杂度：O(1),迭代的过程只会产生几个指针，所以它所需要的空间是常数级别的。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //创建一个虚拟头节点
        ListNode dummyHead = new ListNode(0);
        ListNode prev = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }

    /**
     * 递归法
     * 复杂度分析：
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(m + n)
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l2.next, l1);
            return l2;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2};
        int[] arr2 = new int[] {1, 3};
        ListNode listNode1 = new ListNode(arr);
        System.out.println(listNode1);
        ListNode listNode2 = new ListNode(arr2);
        System.out.println(listNode2);
        System.out.println("合并两个有序的链表" + mergeTwoLists2(listNode1, listNode2));
    }

}
