package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 965. 单值二叉树
 *
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。

 只有给定的树是单值二叉树时，才返回 true；否则返回 false。

 提示：

 给定树的节点数范围是 [1, 100]。
 每个节点的值都是整数，范围为 [0, 99] 。
 *
 */
public class IsUnivalTree {

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
     * 1. 递归终止条件为 当前节点为空
     * 2. 遍历根节点的左右子树和根节点的值进行比较，如果不同，return false 即可
     *
     * 时间复杂度：O(n), 需要遍历每个节点
     * 空间复杂度：O(n), 使用了递归，栈中存在 h 个方法调用，h 为二分搜索树的高度
     *
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTree(root, root.val);
    }

    private boolean isUnivalTree(TreeNode root, int val) {
        if (root == null) return true;
        if (root.val != val) return false;
        return isUnivalTree(root.left, val) && isUnivalTree(root.right, val);
    }

    /**
     * 迭代法
     *
     * 1. 创建队列或栈， 将根节点添加进去
     * 2. 取出队首元素，和根节点的值进行比较，如果不相同就 return false即可
     * 3. 将根节点的左右子树添加进去即可
     *
     * 时间复杂度：O(n)， 需要遍历每个节点
     * 空间复杂度：O(n), 队列中需要存储 n 个元素
     *
     * @param root
     * @return
     */
    public boolean isUnivalTree2(TreeNode root) {
        if (root == null) return true;
        int val = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val != val) return false;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return true;
    }

}
