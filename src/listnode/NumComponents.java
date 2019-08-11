package listnode;

import sun.rmi.runtime.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * 817. 链表组件
 * 给定一个链表（链表结点包含一个整型值）的头结点 heap。

 同时给定列表 G，该列表是上述链表中整型值的一个子集。

 返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。

 示例 1：

 输入:
 heap: 0->1->2->3
 G = [0, 1, 3]
 输出: 2
 解释:
 链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。
 示例 2：

 输入:
 heap: 0->1->2->3->4
 G = [0, 3, 1, 4]
 输出: 2
 解释:
 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 注意:

 如果 N 是给定链表 heap 的长度，1 <= N <= 10000。
 链表中每个结点的值所在范围为 [0, N - 1]。
 1 <= G.length <= 10000
 G 是链表中所有结点的值的一个子集.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/linked-list-components
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class NumComponents {

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
     * 1. 同时遍历链表和数组，如果链表中存在节点值和数组中元素相同，则标记 isContains 为true
     * 2. 如果包含相同元素的话，做一个标记，如果是连续节点包含的话，只有第一次才计数， 也就是一个组件
     * 注意：每次遍历链表时都要再将 isContains 赋值为 false
     *
     * 复杂度分析：
     * 时间复杂度：O(N * G), N 和 G 分别为链表和数组的长度
     * 空间复杂度：O(1), 只需要常数级别的空间复杂度
     *
     * @param head
     * @param G
     * @return
     */
    public static int numComponents(ListNode head, int[] G) {
        if (G == null || G.length == 0) return 0;
        ListNode cur = head;
        int res = 0;
        boolean isContains;  //标记是否包含相同元素
        boolean isComponent = false;

        while (cur != null) {
            isContains = false;
            for (int i = 0; i < G.length; i++) {
                if (cur.val == G[i]) {
                    isContains = true;
                    break;
                }
            }
            if (isContains) {
                if (!isComponent) {
                    isComponent = true;
                    res++;
                }
            } else {
                if (isComponent) {
                    isComponent = false;
                }
            }
            cur = cur.next;
        }
        return res;
    }

    /**
     * set集合法
     * 1. 将数组放入set集合中
     * 2. 遍历该链表，判断该节点的值是否存在于set集合中，如果存在继续遍历到下一个不存在的的节点，则为一个组件
     * 3. 如果不存在，继续往下遍历即可
     * 注意：当链表的最后一个节点的值也包含于set时，要注意如果下一个节点为空的话，也应该将组件 +1
     *
     * 复杂度分析：
     * 时间复杂度：O(N + G)，N为链表的长度，G为数组长度, 我们需要遍历一次数组和一次链表
     * 空间复杂度：O(G), G为数组长度, 由于需要将数组放入set集合中，会浪费G个空间
     *
     * @param head
     * @param G
     * @return
     */
    public static int numComponents2(ListNode head, int[] G) {
        if (G == null || G.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : G) {
            set.add(num);
        }
        ListNode cur = head;
        int res = 0;
        while (cur != null) {
            if (set.contains(cur.val)) {
                cur = cur.next;
                if (cur == null || !set.contains(cur.val)) {
                    res++;
                }
            } else {
                cur = cur.next;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
         int[] array = new int[] {0, 1, 2, 3, 4};
         int[] G = new  int[] {0, 3, 1, 4};
         ListNode listNode = new ListNode(array);
         System.out.println(listNode);
         System.out.println(" 链表组件" + numComponents(listNode, G));
    }

}
