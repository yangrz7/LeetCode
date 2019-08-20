package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树
 *
 * 翻转一棵二叉树。

 示例：

 输入：

   4
 /   \
 2     7
 / \   / \
 1 3 6   9
 输出：

    4
 /   \
 7     2
 / \   / \
 9 6 3   1
 备注:
 这个问题是受到 Max Howell 的 原问题 启发的 ：

 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/invert-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InvertTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归算法
     *
     * 时间复杂度：O(n), 需要遍历每个元素
     * 空间复杂度：O(n),使用了递归，在最坏情况下栈内需要存放 O(h) 个方法调用，其中 h 是树的高度。可得出空间复杂度为 O(n)。
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        root.left = invertTree(rightNode);
        root.right = invertTree(leftNode);
        return root;
    }

    /**
     * 迭代法
     *
     * 时间复杂度：O(n), 需要遍历每个元素将其添加到队列
     * 空间复杂度：O(n), 队列中添加每个元素所占用的空间
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return root;
    }

}
