package listnode;

import java.util.Arrays;

/**
 * 725. 分隔链表
 *
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 返回一个符合上述规则的链表的列表。

 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]

 示例 1：

 输入:
 root = [1, 2, 3], k = 5
 输出: [[1],[2],[3],[],[]]
 解释:
 输入输出各部分都应该是链表，而不是数组。
 例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
 第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
 最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
 示例 2：

 输入:
 root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 解释:
 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
  

 提示:
 root 的长度范围： [0, 1000].
 输入的每个节点的大小范围：[0, 999].
 k 的取值范围： [1, 50].
  

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SplitListToParts {

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
     * 1. 计算整个链表的长度， 判断 length 和 k 的大小关系
     * 2. 如果 length <= k，那我们直接新建一个节点依次存入数组即可
     * 3. 如果 length > k，首先我们要确定数组中每个元素应该存储多少个节点
     *    以本题为例，length = 10, k = 3
     *    可以看出，数组中每个元素至少应该存储 length / k  = 10 / 3 = 3个节点
     *    那么，剩余的最后一个节点 length % = 10 % 3 = 1 根据题意应该放在数组的第一个元素中
     * 4. 确定节点个数之后，我们遍历链表将相应节点存储进数组就可以了
     *
     * 复杂度分析：
     * 时间复杂度：O(2(K + N)）), 其中N为链表长度，K为数组长度，最好情况下，也就是说 k >= length 是，复杂度为 O(3N)
     * 空间复杂度：O(K + N), N为链表的长度，复杂度为 链表占用的空间 N + 数组 ListNode 占用空间，
     *           在 k >= length 的情况下，需要新建链表， 而 k < length, 其空间复杂度为 O(2K), 用于数组count所占用的空间 + ListNode数组占用的空间
     *
     * @param root
     * @param k
     * @return
     */
    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        ListNode cur = root;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        cur = root;
        //如果 k>=length，直接把节点放入数组即可
        if (k >= length) {
            for (int i = 0; i < length; i++) {
                res[i] = new ListNode(cur.val);
                cur = cur.next;
            }
        } else {
            int consult = length / k;   //数组中每个元素至少分配consult个节点
            int mod = length % k;       //多出来的节点依次放入前面的元素
            //新建一个数组存放每个元素存储的节点数量
            int[] count = new int[k];
            for (int i = 0; i < k; i++) {
                count[i] = mod-- > 0 ? consult + 1 : consult;
            }
            //存储链表元素
            for (int i = 0; i < k; i++) {
                int num = count[i];
                res[i] = cur;
                while (--num > 0) {
                    cur = cur.next;
                }
                //截断链表
                ListNode p = cur.next;
                cur.next = null;
                cur = p;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
         int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
         ListNode listNode = new ListNode(arr);
         System.out.println(listNode);
         ListNode[] arrNode = splitListToParts(listNode, 3);
         System.out.println("分隔链表：" + Arrays.toString(arrNode));
    }

}
