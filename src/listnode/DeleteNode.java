package listnode;

/**
 * LeetCode 237
 *
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

 示例 1:

 输入: heap = [4,5,1,9], node = 5
 输出: [4,1,9]
 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class DeleteNode {

      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
     }

     public void deleteNode(ListNode node) {
          node.val = node.next.val;
          node.next = node.next.next;
     }

}
