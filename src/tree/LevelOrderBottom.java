package tree;

import java.util.*;

/**
 * 107. 二叉树的层次遍历 II
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

 例如：
 给定二叉树 [3,9,20,null,null,15,7],

  3
 / \
 9  20
 /  \
 15   7
 返回其自底向上的层次遍历为：

 [
 [15,7],
 [9,20],
 [3]
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LevelOrderBottom {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 迭代法
     * 1. 创建一个队列，将根节点添加进队列中
     * 2. 遍历该队列，获取队列的大小，取出队首的元素，将其加入到 temp 中，依次将其左右节点加入队列中，
     * 3. 将 temp 加入 list 中即可.
     *
     * 时间复杂度：O(n), 需要遍历每个元素添加到队列中
     * 空间复杂度：O(n), 复杂度为队列的空间大小，也就是节点数量
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                size--;
            }
            list.add(temp);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 递归法
     * 1. 递归终止条件为当前节点为空
     * 2. 如果当前处于第新的一层时，就为 list 中第0个元素创建新的 list, 用于存储节点值
     * 3. 将节点的值添加进去
     * 4. 依次添加左右子树节点
     *
     * 时间复杂度：O(n), 需要遍历每个节点
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        return list;
    }

    private void helper(TreeNode root, List<List<Integer>> list, int depth) {
        if (root == null) return;
        //将元素添加到index位置，如果原位置已经有元素了，就将原来的元素向右移动
        if (list.size() == depth) list.add(0, new ArrayList<>());
        list.get(list.size() - depth - 1).add(root.val);
        helper(root.left, list, depth + 1);
        helper(root.right, list, depth + 1);
    }

}
