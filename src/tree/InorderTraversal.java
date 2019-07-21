package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 *
 * 给定一个二叉树，返回它的中序 遍历。

 示例:

 输入: [1,null,2,3]
 1
 \
 2
 /
 3

 输出: [1,3,2]
 进阶: 递归算法很简单，你可以通过迭代算法完成吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class InorderTraversal {

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
     * 递归
     *
     * 复杂度分析：
     * 时间复杂度；O(n)，递归函数 T(n) = 2 * T(n / 2) + 1;
     * 空间复杂度：最坏情况下需要空间O(n)，平均情况为O(log n)
     *
     * @param root
     * @return
     */
    static List<Integer> list = new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return list;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }

    /**
     * 入栈法
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    /**
     * 莫里斯遍历
     *
     * Step 1: 将当前节点current初始化为根节点

     Step 2: While current不为空，

     若current没有左子节点

     a. 将current添加到输出
     b. 进入右子树，亦即, current = current.right

     否则

     a. 在current的左子树中，令current成为最右侧节点的右子节点
     b. 进入左子树，亦即，current = current.left

     复杂度分析：
     时间复杂度：O(n)
     空间复杂度：O(n), 使用了长度为 n 的数组
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        TreeNode prev;
        while (cur != null) {
            if (cur.left == null) {   //左子树为空，直接添加到输出
                list.add(cur.val);
                cur = cur.right;
            } else {
                prev = cur.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = cur;
                TreeNode temp = cur;
                cur = cur.left;
                temp.left = null;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        TreeNode treeNode = new TreeNode(arr);
        List<Integer> list = inorderTraversal2(treeNode);
        StringBuilder res = new StringBuilder();
        res.append("中序遍历：[");
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
