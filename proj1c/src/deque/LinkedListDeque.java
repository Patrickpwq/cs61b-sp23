package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = sentinel;
            private int count = 0;
            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = current.next.item;
                current = current.next;
                count++;
                return item;
            }
        };
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other instanceof LinkedListDeque otherLLD) {
            if (this.size != otherLLD.size) { return false; }
            List L1 = this.toList();
            List L2 = otherLLD.toList();
            for (int i = 0; i < L1.size(); i++) {
                if (L1.get(i) != L2.get(i)) { return false; }
            }
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return toList().toString();
    }
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }
    public static void main(String[] args) {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);
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
