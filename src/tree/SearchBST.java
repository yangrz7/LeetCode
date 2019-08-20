package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 700. 二叉搜索树中的搜索
 *
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * 例如，

 给定二叉搜索树:

 4
 / \
 2   7
 / \
 1   3

 和值: 2
 你应该返回如下子树:

 2
 / \
 1   3
 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SearchBST {

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
     * 1. 递归终止条件：当前节点为 null
     * 2. 比较当前节点的值和要查找的值，如果相等，则就是我们要找的节点，返回该节点即可
     * 3. 如果当前节点值大于要查找的值，那么查找的一定在左子树，否则在右子树
     *
     * 时间复杂度：O(log(n)), 最坏情况下退化成链表，复杂度为 O(n)
     * 空间复杂度：O(n), 使用了递归，栈中存在 h 个方法调用，h 为二分搜索树的高度
     *
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (val == root.val) return root;
        if (val > root.val) return searchBST(root.right, val);
        else return searchBST(root.left, val);
    }

    /**
     * 迭代法
     * 进行层序遍历
     * 1. 创建一个队列，并将根节点添加进去
     * 2. 遍历队列，取出队首元素，和我们要查找的元素进行判断，如果相等，直接返回即可
     * 3. 如果当前节点的值大于要查找的元素，将左子树节点添加进队列， 反之将右子树添加进队列
     *
     * 时间复杂度：O(log(n)), 最坏情况下退化成链表，复杂度为 O(n)
     * 空间复杂度：O(log(n))
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val == val) return cur;
            if (cur.left != null && cur.val > val) queue.add(cur.left);
            else if (cur.right != null && cur.val < val) queue.add(cur.right);
        }
        return null;
    }

}

