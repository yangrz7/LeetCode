package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历
 *
 * 给定一个二叉树，返回它的 后序 遍历。

 示例:

 输入: [1,null,2,3]
 1
 \
 2
 /
 3

 输出: [3,2,1]
 进阶: 递归算法很简单，你可以通过迭代算法完成吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class PostorderTraversal {

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
     * 递归法：
     * 1. 先遍历左子树
     * 2. 在遍历右子树
     * 3. 最后遍历根节点
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n), 最坏情况下需要 O(n), 此时相当于链表， 平均情况为 O(logn)
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    public void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
    }

    /**
     * 迭代法
     *
     * 复杂度分析：
     * 时间复杂度：访问每个节点恰好一次，因此时间复杂度为 O(N)，其中 N 是节点的个数，也就是树的大小。
     * 空间复杂度：取决于树的结构，最坏情况需要保存整棵树，因此空间复杂度为 O(N)。
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return list;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.addFirst(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return list;
    }

}
