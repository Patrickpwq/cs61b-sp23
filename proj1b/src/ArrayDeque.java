import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
// warning: this is a piece of shit, only passing some basic cases
public class ArrayDeque<T> implements Deque<T>{
    public static void main(String[] args) {
        Deque<Character> ad = new ArrayDeque<>();
        ad.addLast('i');
        ad.addLast('j');
        ad.addLast('p');
        ad.addLast('p');
        ad.addLast('p');
        ad.addLast('p');
        ad.addLast('p');
        ad.addLast('p');
        ad.addLast('p');
        ad.addFirst('a');
        ad.addFirst('b');
        ad.addFirst('c');
        ad.addFirst('d');
        ad.addFirst('e');
        ad.addFirst('f');
        ad.addFirst('g');
        ad.addFirst('h');


        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();

    }
    private T[] items;
    private int size, refactor = 2;
    private double sizeDownFactor = 0.25;
    private int last, offset;

    private int getFirst() {
        return items.length + offset;
    }

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        offset = 0;
        last = 7;
    }
    private void resizeUp(int capacity) {
        T[] newAd = (T[]) new Object[capacity];
        int expandSize = capacity - size;
        for (int i = 0; i <= last; i++) {
            newAd[i] = items[i];
        }
        if (last + 1 == getFirst()) {
            for (int i = getFirst(); i < size; i++) {
                newAd[i + expandSize] = items[i];
            }

        }

        items = newAd;
    }
    private void resizeDown(int capacity) {
        T[] newAd = (T[]) new Object[capacity];
        int downSize = items.length - capacity;

        if (last < getFirst()) {
            for (int i = 0; i <= last; i++) {
                newAd[i] = items[i];
            }
            for (int i = getFirst(); i < size; i++) {
                newAd[i - downSize] = items[i];
            }
        }
        else {
            int tmp = getFirst();
            for (int i = getFirst(); i <= last; i++) {
                newAd[i - getFirst()] = items[i];
            }
            offset = -capacity;
            last = last - tmp;
        }
        items = newAd;
    }
    @Override
    public void addFirst(T x) {
        if (items.length == size) {
            resizeUp(size * refactor);
        }
        if (getFirst() == 0) {
            offset = -1;
        }
        else {
            offset--;
        }

        size++;
        items[getFirst()] = x;

    }

    @Override
    public void addLast(T x) {
        if (items.length == size) {
            resizeUp(size * refactor);
        }
        if (last == items.length - 1) {
            last = 0;
        }
        else {
            last++;
        }
        size++;
        items[last] = x;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (isEmpty()) {
            return returnList;
        }
        if (last < getFirst()) {
            for (int i = getFirst(); i < items.length; i++) {
                returnList.add(items[i]);
            }
            for (int i = 0; i <= last; i++) {
                returnList.add(items[i]);
            }
        }
        else {
            for (int i = getFirst(); i <= last; i++) {
                returnList.add(items[i]);
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = items[getFirst()];
        if (getFirst() == items.length - 1) {
            offset = -items.length;
        }
        else {
            offset += 1;
        }
        size--;
        if (items.length >= 16 && (size < items.length * sizeDownFactor)) {
            resizeDown(items.length / 2);
        }
        return temp;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = items[last];
        if (last == 0) {
            last = items.length - 1;
        }
        else {
            last -= 1;
        }
        size--;
        if (items.length >= 16 && (size < items.length * sizeDownFactor)) {
            resizeDown(items.length / 2);
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (getFirst() + index < items.length) {
            return items[getFirst() + index];
        }
        else {
            return items[offset + index];
        }
    }
}
