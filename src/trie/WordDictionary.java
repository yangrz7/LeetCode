package trie;

import java.util.TreeMap;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 *
 * 设计一个支持以下两种操作的数据结构：

 void addWord(word)
 bool search(word)
 search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

 示例:

 addWord("bad")
 addWord("dad")
 addWord("mad")
 search("pad") -> false
 search("bad") -> true
 search(".ad") -> true
 search("b..") -> true
 说明:

 你可以假设所有单词都是由小写字母 a-z 组成的。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class WordDictionary {

    private class Node {
         boolean isWord;
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

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
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

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    /**
     * 递归实现
     * 1. 递归终止条件 当前index到达字符串末尾，返回当前节点 node.isWord 即可
     * 2. 依次取出字符串中每个元素，如果当前元素不是"."， 则判断当前节点的下一个节点是否为空，如果是，则一定不包含该字符串， 否则继续递归
     * 3. 如果当前元素为 "."， 则需要遍历它下面的所有节点， 如果字符串包含其中，则返回 true， 否则返回 false 即可
     *
     * @param node
     * @param word
     * @param index
     * @return
     */
    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c !=  '.') {
            if (node.next.get(c) == null) return false;
            return match(node.next.get(c), word, index + 1);
        } else {
            for (char nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

}
