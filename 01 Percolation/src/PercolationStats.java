public class PercolationStats {
    /**
     * @param args
     */
    private int opened = 0;
    private double[] openSites;
    private int expNum;
    private int sideLength;

    public PercolationStats(int N, int T) {
        Percolation perc;
        int x, y;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.setExperimentsNum(T);
        this.sideLength = N;
        for (int i = 1; i <= T; i++) {
            perc = new Percolation(N);

            while (!perc.percolates()) {
                x = this.randomInt(N);
                y = this.randomInt(N);

                if (!perc.isOpen(x, y)) {
                    this.increaseOpened();
                    perc.open(x, y);
//                    perc.draw();
                }
            }
            this.setOpenSitesFraction(i);
            this.resetOpened();
            perc = null;
        }
        StdOut.printf("mean                    = %f\n", mean());
        StdOut.printf("stddev                  = %f\n", stddev());
        StdOut.printf("95%% confidence interval = %f, %f", 
                        leftConfInterval(), 
                        rightConfInterval());
    }

    private void increaseOpened() {
        this.opened++;
    }
    
    private void resetOpened() {
        this.opened = 0;
    }
    
    private int randomInt(int N) {
        return (int) Math.round((N - 1) * Math.random() + 1);
    }
    
    private void setOpenSitesFraction(int iteration) {
        this.openSites[iteration-1] = this.opened / this.getFieldSize();
    }
    
    private double getOpenSites(int iteration) {
        return this.openSites[iteration-1];
    }
    
    private void setExperimentsNum(int T) {
        this.expNum = T;
        this.openSites = new double[T];
    }
    
    private int getExperimentsNum() {
        return    this.expNum;
    }
    
    private double getFieldSize() {
        return this.sideLength * this.sideLength;
    }
    
    public double mean() {
        double ret = 0.0;

        for (int i = 1; i <= this.getExperimentsNum(); i++) {
            ret += this.getOpenSites(i);
        }
        return ret / this.getExperimentsNum();
    }
    public double stddev() {
        if (this.getExperimentsNum() == 1) {
            return Double.NaN;
        }
        double ret = 0;
        double mu = this.mean();
        for (int i = 1; i <= this.getExperimentsNum(); i++) {
            ret += (this.getOpenSites(i) - mu) * (this.getOpenSites(i) - mu); 
        }

        return Math.sqrt(ret / (this.getExperimentsNum() - 1));
    }

    private double leftConfInterval() {
        double ret = 0.0;
        ret = mean() - (1.96 * stddev() / Math.sqrt(this.getFieldSize()));
        return ret;
    }

    private double rightConfInterval() {
        double ret = 0.0;
        ret = mean() + (1.96 * stddev() / Math.sqrt(this.getFieldSize()));
        return ret;
    }
    
    public static void main(String[] args) throws Exception {

//    	Percolation p = new Percolation(10);
//    	StdOut.println(p.isOpen(5, 1));
//    	StdOut.println(p.isFull(5, 1));
//    	p.open(5, 1);
//    	StdOut.println(p.isOpen(5, 1));
//    	StdOut.println(p.isFull(5, 1));
    	
        if (args.length > 0) {
            new PercolationStats(Integer.parseInt(args[0]),
                                 Integer.parseInt(args[1]));
        }
        else {
            throw new java.lang.Exception("You must enter parameters [N] [T]");
        }

    }

}
