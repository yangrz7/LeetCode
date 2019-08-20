package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。

 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

 说明: 叶子节点是指没有子节点的节点。

 示例：
 给定二叉树 [3,9,20,null,null,15,7]，

  3
 / \
 9  20
 /  \
 15   7
 返回它的最大深度 3 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MaxDepth {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归法
     * 1. 递归终止条件为当前节点为 null
     * 2. 依次递归求出根节点的左右子树的高度
     * 3. 左右子树高度最大值在加上根节点（+1）即为整个二叉树的高度
     *
     * 时间复杂度：O(n), 需要依次遍历所有节点
     * 空间复杂度：O(n), 使用了递归，最坏情况下栈中需要存放 h 个方法调用，h 为二叉树的高度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 迭代法
     * 使用队列进行层序遍历
     * 1. 创建一个队列，将根节点添加进队列中
     * 2. 遍历该队列，每次将高度 height++，获取队列的大小，取出队首的元素，依次将其左右节点加入队列中，
     * 3. 返回高度 height 即可
     *
     * 时间复杂度：O(n), 需要遍历每个节点元素
     * 空间复杂度：O(n), 由于使用了队列，队列大小为节点数量
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            height++;
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                size--;
            }
        }
        return height;
    }

}
