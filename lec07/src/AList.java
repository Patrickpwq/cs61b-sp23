import java.util.ArrayList;

public class AList<Glorp> {
    private int size;
    private Glorp[] items;
    /** Creates an empty list. */
    public AList() {
        items = (Glorp[]) new Object[100];
        size = 0;
    }

    /** Resize items when array gets too big */
    private void resize(int capacity) {
        Glorp[] a =(Glorp[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;

    }
    /** Inserts X into the back of the list. */
    public void addLast(Glorp x) {
        if (size == items.length) {
            resize(size + 1);
        }
        items[size++] = x;
     }
    /** Returns the item from the back of the list. */
    public Glorp getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Glorp get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Glorp removeLast() {
        Glorp last = getLast();
        items[(size--) - 1] = null;
        return last;
    }
} 