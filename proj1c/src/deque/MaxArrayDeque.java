package deque;
import deque.ArrayDeque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> defaultComparator;
    public MaxArrayDeque(Comparator<T> c) {
        defaultComparator = c;
    }
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = iterator().next();
        for (T item : this) {
            if (defaultComparator.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = iterator().next();
        for (T item : this) {
            if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}
