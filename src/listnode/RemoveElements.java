package listnode;

/**
 * LeetCode 203
 * 删除链表中等于给定值 val 的所有节点。

 示例:

 输入: 1->2->6->3->4->5->6, val = 6
 输出: 1->2->3->4->5

 *
 */
public class RemoveElements {

      public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }

          /**
           * 测试用例
           */
          public ListNode(int[] arr) {
              if (arr == null || arr.length == 0) throw new NullPointerException("arrary is empty");
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
     * @param head 头节点
     * @param val 要删除的元素
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
          while (head != null && head.val == val) {
//              ListNode delNode = head;
              head = head.next;
//              delNode.next = null;
          }
          if (head == null) return null;
          ListNode preNode = head;
          while (preNode.next != null) {
              if (preNode.next.val == val) {
                  ListNode delNode = preNode.next;
                  preNode.next = delNode.next;
                  delNode.next = null;
              } else {
                  preNode = preNode.next;
              }
          }
          return head;
    }

    /**
     * 使用虚拟头节点
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode preNode = dummyHead;
        while (preNode.next != null) {
            if (preNode.next.val == val) {
                ListNode delNode = preNode.next;
                preNode.next = delNode.next;
                delNode.next = null;
            } else {
                preNode = preNode.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 使用递归
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements3(ListNode head, int val) {
        if (head == null) return null;
        ListNode resNode = removeElements3(head.next, val);
        if (head.val == val) return resNode;
        else {
            head.next = resNode;
            return head;
        }
    }

    /**
     * 优化递归
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements4(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements4(head.next, val);
        return head.val == val ? head.next : head;
    }
    
    public static void main(String[] args) {
         int[] nums = new int[] {1, 2, 6};
         ListNode head = new ListNode(nums);
         System.out.println(head);
         System.out.println("移除链表元素后为：" + removeElements4(head, 6));
    }

}
