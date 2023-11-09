import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public int n, numOfOpenSites = 0;
    public final int virtualTop, virtualBottom;
    public int[] sitesState;
    public WeightedQuickUnionUF WQUUFforPercolation, WQUUFforisFull;
    // -1 -> blocked; 0 -> open
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        sitesState = new int[N * N];
        n = N;
        virtualTop = n * n;
        virtualBottom = n * n + 1;
        for (int i = 0; i < n * n; i++) {
            sitesState[i] = -1;
        }
        WQUUFforPercolation = new WeightedQuickUnionUF(n * n + 2);
        WQUUFforisFull = new WeightedQuickUnionUF(n * n + 1);
    }
    private int locationHelper(int row, int col) {
        return row * n + col;
    }
    public void open(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }
        int loc = locationHelper(row, col);
        if (sitesState[loc] == 0) { // Already open, ignore it
            return;
        }
        numOfOpenSites++;
        sitesState[loc] = 0;
        // connect virtual nodes
        if (row == 0) {
            WQUUFforPercolation.union(virtualTop, loc);
            WQUUFforisFull.union(virtualTop, loc);
        }
        if (row == n - 1) {
            WQUUFforPercolation.union(virtualBottom, loc);
        }
        int left = locationHelper(row, col - 1);
        int right = locationHelper(row, col + 1);
        int top = locationHelper(row - 1, col);
        int bottom = locationHelper(row + 1, col);
        if (col > 0 && isOpen(row, col - 1)) {
            WQUUFforPercolation.union(loc, left);
            WQUUFforisFull.union(loc, left);

        }
        if (col < n - 1 && isOpen(row, col + 1)) {
            WQUUFforPercolation.union(loc, right);
            WQUUFforisFull.union(loc, right);
        }
        if (row > 0 && isOpen(row - 1, col)) {
            WQUUFforPercolation.union(loc, top);
            WQUUFforisFull.union(loc, top);
        }
        if (row < n - 1 && isOpen(row + 1, col)) {
            WQUUFforPercolation.union(loc, bottom);
            WQUUFforisFull.union(loc, bottom);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }
        return sitesState[locationHelper(row, col)] == 0;
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }
        return WQUUFforisFull.connected(virtualTop, locationHelper(row, col));
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        return WQUUFforPercolation.connected(virtualTop, virtualBottom);
    }
    public void Print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isOpen(i, j)) {
                    System.out.print(0);
                }
                else {
                    System.out.print(1);
                }
            }
            System.out.print('\n');
        }
    }

}
