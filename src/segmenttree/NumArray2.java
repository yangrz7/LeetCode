package segmenttree;

import java.util.Arrays;

/**
 * 307. 区域和检索 - 数组可修改
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

 update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。

 示例:

 Given nums = [1, 3, 5]

 sumRange(0, 2) -> 9
 update(1, 2)
 sumRange(0, 2) -> 8
 说明:

 数组仅可以在 update 函数下进行修改。
 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/range-sum-query-mutable
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class NumArray2 {

    private int[] sum;
    private int[] data;

    private int[] tree;

    public NumArray2(int[] nums) {
        data = nums;

        sum = new int[nums.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        tree = new int[data .length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 构建线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int mid = l + (r - l) / 2;
        int lefeTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        buildSegmentTree(lefeTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = tree[lefeTreeIndex] + tree[rightTreeIndex];
    }

    /**
     * 查询线段树中区间[queryL, queryR]的值
     * @param queryL
     * @param queryR
     * @return
     */
    public int sumRange3(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is Illegal");
        }
        return sumRange3(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     *
     * @param treeIndex 根节点
     * @param l 左边界
     * @param r 右边界
     * @param queryL 查询的左区间
     * @param queryR 查询的右区间
     * @return
     */
    private int sumRange3(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            //查询区间在右子树
            return sumRange3(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            //查询区间在左子树
            return sumRange3(leftTreeIndex, l, mid, queryL, queryR);
        }
        int leftResult = sumRange3(leftTreeIndex, l, mid, queryL, mid);
        int rightResult = sumRange3(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return leftResult + rightResult;
    }

    /**
     * 在以treeIndex为根的线段树中更新index的值为e
     * @param index
     * @param e
     */
    public void update3(int index, int e) {
        if (index < 0 || index >= data.length) throw new IllegalArgumentException("Index is Illegal");
        data[index] = e;
        update3(0, 0, data.length - 1, index, e);
    }

    /**
     * 更新
     * @param treeIndex 根节点索引
     * @param l 左边界
     * @param r 右边界
     * @param index 更新的索引
     * @param e 要更新的值
     */
    private void update3(int treeIndex, int l, int r, int index, int e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1) {
            //在右子树
            update3(rightTreeIndex, mid + 1, r, index, e);
        } else {
            //左子树
            update3(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
    }

    /**
     * 返回完全二叉树数组表示中，左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     *  返回完全二叉树数组表示中，右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public void update(int i, int val) {
        data[i] = val;
        for (int k = i + 1; k < sum.length; k++) {
            sum[k] = sum[k - 1] + data[k - 1];
        }
    }

    /**
     * 预处理法(但由于数组可变，该解法超时)
     *
     * 在每次进行update() 更新操作的时候都要重新计算数组 sum 的值
     *
     * 复杂度分析：
     * 时间复杂度：O(n), 每次 update() 都是 O(n) 的复杂度
     * 空间复杂度：O(n)
     *
     * @param i
     * @param j
     * @return
     */
    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    /**
     * 暴力解法
     * @param i
     * @param val
     */
    public void update2(int i, int val) {
        data[i] = val;
    }

    public int sumRange2(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += data[k];
        }
        return sum;
    }
    
    public static void main(String[] args) {
         int[] nums = {9, -8};
         NumArray2 numArray2 = new NumArray2(nums);
         System.out.println(Arrays.toString(nums));
         numArray2.update3(0, 3);
         System.out.println("sumRange(0, 2) ->:" + numArray2.sumRange3(1, 1));
         System.out.println("sumRange(0, 2) ->:" + numArray2.sumRange3(0, 1));
         numArray2.update3(1, -3);
         System.out.println("sumRange(0, 2) ->" + numArray2.sumRange3(0, 1));
    }

}
