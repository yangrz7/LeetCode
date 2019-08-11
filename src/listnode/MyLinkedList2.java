package listnode;

public class MyLinkedList2 {

    private class Node {

        private int val;
        private Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this(val, null);
        }

        public Node() {
            this(0, null);
        }
    }

    private Node head;
    private int size;

    public MyLinkedList2() {
        head = null;
        size = 0;
    }

    public void addAtHead(int val) {
//        Node node = new Node(val);
//        node.next = heap;
//        heap = node;
        head = new Node(val, head);
        size ++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            prev.next = new Node(val, prev.next);
            size ++;
        }

    }

    public void deleteAtHead() {
        head = head.next;
        size--;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            deleteAtHead();
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            Node cur = prev.next;
            prev.next = cur.next;
            cur.next = null;
            size--;
        }
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        if (index == 0) {
            return head.val;
        } else {
            Node cur = head.next;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            return cur.val;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

    public static void main(String[] args) {
         MyLinkedList2 myLinkedList2 = new MyLinkedList2();
         myLinkedList2.addAtHead(1);
        System.out.println(myLinkedList2);
         myLinkedList2.addAtTail(3);
        System.out.println(myLinkedList2);
         myLinkedList2.addAtIndex(1, 2);
        System.out.println(myLinkedList2);
         myLinkedList2.deleteAtIndex(1);
        System.out.println(myLinkedList2);
         myLinkedList2.get(1);
         System.out.println(myLinkedList2);
    }

}
