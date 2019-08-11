package heap;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

 示例 1:

 输入: nums = [1,1,1,2,2,3], k = 2
 输出: [1,2]
 示例 2:

 输入: nums = [1], k = 1
 输出: [1]
 说明：

 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class TopKFrequent {

    private class Freq implements Comparable<Freq> {

        int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            return this.freq - another.freq;
//            if (this.freq > another.freq) return 1;
//            else if (this.freq < another.freq) return -1;
//            else return 0;
        }
    }

    /**
     * 优先队列法：
     * 1. 遍历数组，将每个元素及其出现的频率存入 map 中
     * 2. 创建一个优先队列(最小堆)，先存入 k 个元素
     * 3. 如果当前堆中已经有了 k 个元素，则判断下一个元素的频率是否堆顶元素(最小值), 如果大的话，就将最小的元素删除掉，将该元素添加进去
     * 4. 遍历该队列，将元素加入 list 即可
     *
     * 时间复杂度：O(nlog(k))，在遍map历过程中如果需要在堆中添加元素，添加元素复杂度为 logk
     * 空间复杂度: O(n), map所占用的空间
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Freq> heap = new PriorityQueue<>();
        for (int key : map.keySet()) {
            if (heap.size() < k) {
                heap.add(new Freq(key, map.get(key)));
            } else if (map.get(key) > heap.peek().freq) {
                heap.poll();
                heap.add(new Freq(key, map.get(key)));
            }
        }
        List<Integer> list = new LinkedList<>();
        while (!heap.isEmpty()) {
            list.add(heap.poll().e);
        }
        return list;
    }

    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        for (int key : map.keySet()) {
            if (heap.size() < k) {
                heap.add(key);
            } else if (map.get(key) > map.get(heap.peek())) {
                heap.poll();
                heap.add(key);
            }
        }
        List<Integer> list = new LinkedList<>();
        while (!heap.isEmpty()) {
            list.add(heap.poll() );
        }
        return list;
    }
    
    public static void main(String[] args) {
         int[] nums  = {4,1,-1,2,-1,2,3};
         System.out.println(Arrays.toString(nums));
         TopKFrequent topKFrequent = new TopKFrequent();
         System.out.println("前 K 个高频元素:" + topKFrequent.topKFrequent(nums, 2));
    }

}
