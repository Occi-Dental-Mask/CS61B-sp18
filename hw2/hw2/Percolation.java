package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean a[][];
    private WeightedQuickUnionUF quickUnion1;
    private WeightedQuickUnionUF quickUnion2;
    private final int size;//the Size of the world
    private int openSize = 0;//the number of cell that has been opened
    private final int virtualTop;
    private final int virtualBottom;

    /* create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        this.size = N;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        this.quickUnion1 = new WeightedQuickUnionUF(N * N + 2);
        this.quickUnion2 = new WeightedQuickUnionUF(N * N + 1);
        this.a = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            quickUnion1.union(virtualTop, transTo(0, j));
            quickUnion1.union(virtualBottom, transTo(size - 1, j));
            quickUnion2.union(virtualTop, transTo(0, j));
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = false;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        a[row][col] = true;
        openSize++;
        union4D(row, col, row - 1, col);//左
        union4D(row, col, row + 1, col);//右
        union4D(row, col, row, col + 1);//上
        union4D(row, col, row, col - 1);//下
    }
    private void union4D(int row, int col, int r2, int c2) {
        if (r2 >= 0 && r2 <= size - 1 && c2 >= 0 && c2 <= size - 1){
            if (isOpen(r2, c2)) {
                quickUnion1.union(transTo(row, col), transTo(r2, c2));
                quickUnion2.union(transTo(row, col), transTo(r2, c2));
            }
        }
    }


    private int transTo(int row, int col) {
        return size * row + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return a[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        return quickUnion2.connected(virtualTop, transTo(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSize;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickUnion1.connected(virtualTop, virtualBottom);
    }
    public static void main(String[] args) {

    }  // use for unit testing (not required)
}
