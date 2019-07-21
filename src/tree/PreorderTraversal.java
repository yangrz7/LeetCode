package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历
 *
 * 给定一个二叉树，返回它的 前序 遍历。

  示例:

 输入: [1,null,2,3]
 1
 \
 2
 /
 3

 输出: [1,2,3]
 进阶: 递归算法很简单，你可以通过迭代算法完成吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class PreorderTraversal {

    static class TreeNode implements Comparable<TreeNode> {
        private Integer val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int[] arr) {
            if (arr == null || arr.length == 0) throw new NullPointerException("array is empty");
            this.val = arr[0];
            TreeNode root = this;
            for (int i = 1; i < arr.length; i++) {
                add(root, arr[i]);
            }
        }


        public TreeNode add(TreeNode root, Integer val) {
            if (root == null) return new TreeNode(val);
            if (val.compareTo(root.val) < 0) root.left = add(root.left, val);
            if (val.compareTo(root.val) > 0) root.right = add(root.right, val);
            return root;
        }

        @Override
        public int compareTo(TreeNode o) {
            return this.val.compareTo(o.val);
        }
    }

    /**
     * 递归法
     *
     * 复杂度分析：
     * 时间复杂度：O(n), 访问每个节点恰好一次，时间复杂度为 O(n)，其中 n 是节点的个数，也就是树的大小。
     * 空间复杂度：O(n), n为元素数量，由于使用了递归，空间复杂度会达到O(n)
     *
     * @param root
     * @return
     */
    static List<Integer> list = new ArrayList<>();
    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return list;
    }

    /**
     * 入栈法
     *
     * 复杂度分析：
     * 时间复杂度：O(n), 访问每个节点恰好一次，时间复杂度为 O(n)，其中 n 是节点的个数，也就是树的大小。
     * 空间复杂度：O(n), n为元素数量，由于使用了栈，空间复杂度会达到O(n)
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            list.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return list;
    }
    
    public static void main(String[] args) {
         int[] arr = {1, 2, 3};
         TreeNode treeNode = new TreeNode(arr);
         List<Integer> list = preorderTraversal2(treeNode);
         StringBuilder res = new StringBuilder();
         res.append("前序遍历：[");
         for (int i = 0; i < list.size(); i++) {
             res.append(list.get(i));
             if (i != list.size() - 1) {
                 res.append(",");
             }
         }
         res.append("]");
         System.out.println(res.toString());
    }

}
