import java.lang.Exception;
public class IntList {
    public int first;
    public IntList rest;
    public IntList(int x, IntList r) {
        first = x;
        rest = r;
    }

    public void addFirst (int x) {
        first = x;
        rest = this;
    }
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }
    public int iterativeSize() {
        int size = 0;
        IntList r = this;
        while (r != null) {
            r = r.rest;
            size++;
        }
        return size;
    }

    public int get(int n) {
        if (n == 1) {
            return first;
        }
        return rest.get(n - 1);
    }

    public static IntList incrList(IntList L, int x) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first + x, incrList(L.rest, x));
    }

    public static IntList dincrList(IntList L, int x) {
        if (L == null) {
            return null;
        }
        L.first += x;
        L.rest = dincrList(L.rest, x);
        return L;
    }
}
