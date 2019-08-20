package tree;

/**
 * 108. 将有序数组转换为二叉搜索树
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

 示例:

 给定有序数组: [-10,-3,0,5,9],

 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

   0
 /   \
 -3   9
 /   /
 -10  5


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SortedArrayToBST {

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
     * 由于要求是平衡二叉搜索树，所以可以采用线段树的创建过程
     * 1. 取出数组的中间节点作为根节点，左边的元素为左子树，右边的元素作为右子树
     * 2. 递归调用创建即可
     *
     * 注意：为防止当数组很大时，使用（l + r）/ 2 求中间位置可能会溢出
     *
     * 时间复杂度：O(n), 需要遍历每个元素添加到二叉树中
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : buildTree(nums, 0, nums.length);
    }

    private TreeNode buildTree(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = l + (r - l) / 2;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = buildTree(nums, l, mid - 1);
        cur.right = buildTree(nums, mid + 1, r);
        return cur;
    }

}
