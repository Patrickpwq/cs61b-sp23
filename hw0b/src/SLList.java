public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    private IntNode first;
    private int size = 0;
    public SLList() {
        first = null;
        size = 0;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    public void addLast(int x) {
        size++;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }
    private int size(IntNode p) {
        if (p.next == null)
            return 1;
        return 1 + size(p.next);
    }
    public int size() {
        return size(first);
    }
}
