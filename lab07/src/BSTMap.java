import java.util.*;

public class BSTMap<K extends Comparable<K>, V extends Comparable<V>> implements Map61B<K, V> {
    private class BSTNode {
        K key;
        V value;
        BSTNode left, right;
        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    private BSTNode root = null;
    private int size = 0;
    @Override
    public void put(K key, V value) {
        size += 1;
        root = putHelper(key, value, root);
    }

    private BSTNode putHelper(K key, V value, BSTNode node) {
        if (node == null) {
            return new BSTNode(key, value);
        }
        if (node.key.compareTo(key) > 0) {
            node.left = putHelper(key, value, node.left);
        } else if (node.key.compareTo(key) < 0) {
            node.right = putHelper(key, value, node.right);
        } else {
            size -= 1;
            node.value = value;
        }
        return node;
    }
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            return getHelper(key, node.left);
        } else if (node.key.compareTo(key) < 0) {
            return getHelper(key, node.right);
        } else {
            return node.value;
        }
    }
    @Override
    public boolean containsKey(K key) {
        return containsHelper(key, root);
    }
    private boolean containsHelper(K key, BSTNode node) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) > 0) {
            return containsHelper(key, node.left);
        } else if (node.key.compareTo(key) < 0) {
            return containsHelper(key, node.right);
        } else {
            return true;
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public Set<K> keySet() {
       Set<K> keySet = new TreeSet<>();
       traversal(keySet, root);
       return keySet;
    }

    private void traversal(Set<K> keySet, BSTNode node) {
        if (node == null) {
            return;
        }
        keySet.add(node.key);
        traversal(keySet, node.left);
        traversal(keySet, node.right);
    }
    @Override
    public V remove(K key) {
        // Use successor to implement
        size--;
        V value = get(key);
        root = removeHelper(key, root);
        return value;
    }

    private BSTNode removeHelper(K key, BSTNode node) {
        if (node == null) {
            size++;
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            node.left = removeHelper(key, node.left);
        } else if (node.key.compareTo(key) < 0) {
            node.right = removeHelper(key, node.right);
        } else {
            // Find the node
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            BSTNode temp = node;
            node = getSuccessor(node);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }
    private BSTNode getSuccessor(BSTNode node) {
        return getLeftMost(node.right);
    }
    private BSTNode getLeftMost(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    private BSTNode deleteMin(BSTNode node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator(root);
    }

    private class BSTMapIterator implements Iterator<K> {
        private Stack<BSTNode> stack = new Stack<>();
        public BSTMapIterator(BSTNode root) {
            pushLeft(root);
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        private void pushLeft(BSTNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }
    }

    // for debug
    public void printTree() {
        printSubtree(root, 0, "root");
    }

    // Recursive method to print a subtree
    private void printSubtree(BSTNode node, int level, String side) {
        if (node == null) {
            return;
        }

        // Print right subtree
        printSubtree(node.right, level + 1, "right");

        // Print node
        printNode(node, level, side);

        // Print left subtree
        printSubtree(node.left, level + 1, "left");
    }

    // Print a single node with indentation based on its level
    private void printNode(BSTNode node, int level, String side) {
        for (int i = 0; i < level; i++) {
            System.out.print("       ");
        }
        System.out.println("[" + side + "] " + node.key + " => " + node.value);
    }

}
