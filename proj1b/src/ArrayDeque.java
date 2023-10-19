import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
// warning: this is a piece of shit, only passing some basic cases
public class ArrayDeque<T> implements Deque<T>{
    public static void main(String[] args) {
        Deque<Character> ad = new ArrayDeque<>();


    }
    private T[] items;
    private int size, refactor = 2;
    private double sizeDownFactor = 0.25;
    private Integer first, nextFirst, lastFirst;
    private Integer last, nextLast, lastLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = null;
        nextFirst = 3;
        lastFirst = null;
        last = null;
        nextLast = 4;
        lastLast = null;
    }
    private void resizeUp(int capacity) {
        T[] newAd = (T[]) new Object[capacity];
        if (first == 0 && last == items.length - 1) {
            for (int i = first; i <= last; i++) {
                newAd[i] = items[i];
            }
            nextFirst = capacity - 1;
            nextLast = items.length;
        }
        else {
            for (int i = first; i < items.length; i++) {
                newAd[i - first] = items[i];
            }
            for (int i = 0; i <= last; i++) {
                newAd[i + items.length - first] = items[i];
            }
            first = 0; nextFirst = capacity - 1; lastFirst = 1;
            last = items.length - 1; nextLast = items.length; lastLast = items.length - 2;
        }
        items = newAd;
    }
    private void resizeDown(int capacity) {
        T[] newAd = (T[]) new Object[capacity];

        if (first <= last) {
            for (int i = first; i <= last; i++) {
                newAd[i] = items[i];
            }
            int len = last - first + 1;
            first = 0; nextFirst = capacity - 1; lastFirst = 1;
            last =  len - 1; nextLast = len; lastLast = len - 2;
        }
        else {
            for (int i = first; i < items.length; i++) {
                newAd[i - first] = items[i];
            }
            for (int i = 0; i <= last; i++) {
                newAd[i + items.length - first] = items[i];
            }
            int len = items.length - first + last + 1;
            first = 0; nextFirst = capacity - 1; lastFirst = 1;
            last =  len - 1; nextLast = len; lastLast = len - 2;
        }
        items = newAd;
    }
    @Override
    public void addFirst(T x) {
        if (items.length == size) {
            resizeUp(size * refactor);
        }
        if (size == 0) {
            last = 3;
            lastLast = 2;
        }
        items[nextFirst] = x;
        lastFirst = first;
        first = nextFirst;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        else {
            nextFirst = nextFirst - 1;
        }

        size++;


    }

    @Override
    public void addLast(T x) {
        if (items.length == size) {
            resizeUp(size * refactor);
        }
        if (size == 0) {
            // if x is the first element to be added, "first" should change
            first = 4;
            lastFirst = 5; // in case of "addLast addLast addLast removeFirst
        }
        items[nextLast] = x;
        lastLast = last;
        last = nextLast;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        }
        else {
            nextLast = nextLast + 1;
        }

        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (isEmpty()) {
            return returnList;
        }
        if (first <= last) {
            for (int i = first; i <= last; i++) {
                returnList.add(items[i]);
            }
        }
        else {
            for (int i = first; i < items.length; i++) {
                returnList.add(items[i]);
            }
            for (int i = 0; i <= last; i++) {
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
        size--;
        T temp = items[first];
        if (size == 0) {
            //  only one element -> reset
            items[first] = null;
            first = null;
            nextFirst = 3;
            lastFirst = null;
            last = null;
            nextLast = 4;
            lastLast = null;
            return temp;
        }

        items[first] = null;
        nextFirst = first;
        first = lastFirst;

        if (lastFirst == items.length - 1) {
            lastFirst = 0;
        }
        else {
            lastFirst = lastFirst + 1;
        }

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
        size--;
        T temp = items[last];
        if (size == 0) {
            items[last] = null;
            first = null;
            nextFirst = 3;
            lastFirst = null;
            last = null;
            nextLast = 4;
            lastLast = null;
            return temp;
        }
        items[last] = null;
        nextLast = last;
        last = lastLast;
        if (last == 0) {
            lastLast = items.length - 1;
        }
        else {
            lastLast -= 1;
        }
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
        if (first <= last) {
            return items[first + index];
        }
        else {
            if (first + index < items.length) {
                return items[first + index];
            }
            else {
                return items[first + index - items.length];
            }
        }
    }
    /*
        For test only
     */
    public int getItemsLength() {
        return items.length;
    }
}
