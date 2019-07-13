package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 938. 二叉搜索树的范围和
 *
 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

 二叉搜索树保证具有唯一的值。

 示例 1：

 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 输出：32
 示例 2：

 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 输出：23

 提示：

 树中的结点数量最多为 10000 个。
 最终的答案保证小于 2^31。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class RangeSumBST{

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
         public String toString() {
            TreeNode root = this;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            StringBuilder res = new StringBuilder();
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                res.append(node + ", ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            return res.toString();
         }

         @Override
        public int compareTo(TreeNode o) {
            return this.val.compareTo(o.val);
        }

    }


    public static void main(String[] args) {
         int[] arr = new int[] {10, 5, 15, 3, 7, 0, 18};
         TreeNode treeNode = new TreeNode(arr);
         System.out.println(treeNode);
    }

}
