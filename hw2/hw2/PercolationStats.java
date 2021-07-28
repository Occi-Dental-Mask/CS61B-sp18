package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import java.lang.Math.*;
public class PercolationStats {
    private final int numOfTime;
    private double[] ti;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        int t = 0;
        this.numOfTime = T;
        this.ti = new double[T];
        while (t < T) {
            Percolation per = pf.make(N);
            while(!per.percolates()) {
                int i = StdRandom.uniform(N);
                int j = StdRandom.uniform(N);//To generate a random int number in [0,n)
                per.open(i, j);
            }
            double xi = (double) per.numberOfOpenSites() / (double) (N * N);
            ti[t] = xi;
            t++;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.ti);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.ti);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double sqrT = Math.sqrt(numOfTime);
        return mean() - 1.96 * stddev() / sqrT;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double sqrT = Math.sqrt(numOfTime);
        return mean() + 1.96 * stddev() / sqrT;
    }
}
