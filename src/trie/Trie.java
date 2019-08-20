package trie;

import java.util.TreeMap;

/**
 * 208. 实现 Trie (前缀树)
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

 示例:

 Trie trie = new Trie();

 trie.insert("apple");
 trie.search("apple");   // 返回 true
 trie.search("app");     // 返回 false
 trie.startsWith("app"); // 返回 true
 trie.insert("app");
 trie.search("app");     // 返回 true
 说明:

 你可以假设所有的输入都是由小写字母 a-z 构成的。
 保证所有输入均为非空字符串。


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Trie {

    private class Node {

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    /**
     * 向Trie中添加单词
     * 时间复杂度：O(m), m为要查询字符串的长度
     * 空间复杂度：O(m), 最坏情况下没有公共节点，需要添加 m 个节点
     *
     * @param word
     */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /**
     * 判断单词是否在 Trie 中
     * 时间复杂度：O(m), m为要查询字符串的长度
     * 空间复杂度：O(1)
     * @param word
     * @return
     */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询是否在Trie中有单词以preifx为前缀
     * 时间复杂度：O(m), m为要查询字符串的长度
     * 空间复杂度：O(1)
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }


}
