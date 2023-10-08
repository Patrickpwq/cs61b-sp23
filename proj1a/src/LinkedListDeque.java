import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;
    public class Node {
        public Node next, pre;
        public T item;
        public Node(T x, Node nextNode, Node preNode) {
            next = nextNode;
            pre = preNode;
            item = x;
        }
    }
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(1);
        lld.addLast(2);
    }

    @Override
    public void addFirst(T x) {
        Node newNode, originNode;
        originNode = sentinel.next;
        newNode = new Node(x, originNode, sentinel);
        sentinel.next = newNode;
        originNode.pre = newNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newNode, originNode;
        originNode = sentinel.pre;
        newNode = new Node(x, sentinel, originNode);
        sentinel.pre = newNode;
        originNode.next = newNode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node now = sentinel.next;
        while (now != sentinel) {
            returnList.add(now.item);
            now = now.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        return last;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node now = sentinel;
        for (int i = 0; i <= index; i++) {
            now = now.next;
        }
        return now.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    public T getRecursiveHelper(int index, Node now) {
        if (index == 0) {
            return now.item;
        }
        return getRecursiveHelper(index - 1, now.next);
    }
}
