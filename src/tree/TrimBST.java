package tree;

/**
 * 669. 修剪二叉搜索树
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。

 示例 1:

 输入:
   1
  / \
 0   2

 L = 1
 R = 2

 输出:
 1
 \
 2

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/trim-a-binary-search-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class TrimBST {

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
     * 1. 判断当前根节点的值和区间的左右边界
     * 2. 如果根节点的值都大于区间的右边界，那么右子树的值一定都大于右区间，我们只需修剪左子树即可, 反之同理
     * 3. 如果当前根节点在区间内，分别修剪左右子树即可
     *
     * 时间复杂度：O(n), 最坏情况下每个节点都需要访问一次
     * 空间复杂度：O(n), 在最糟糕的情况下，我们递归调用的栈可能与节点数一样大。
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        //如果根节点的值都大于区间的右边界，那么右子树的值一定都大于右区间，我们只需修剪左子树即可
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }
        //同理
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
