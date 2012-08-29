//import    java.awt.Color;

public class Percolation {
    private boolean[][]    field;
    private boolean[] wetRoots;
    private boolean fullOnBottom = false;
    private int N;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        this.N = N;
        this.field = new boolean[N+1][N+1];
        this.wetRoots = new boolean[(N*N)+1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                this.field[i][j] = false;
                this.wetRoots[ijToNum(i, j)] = false;
            }
        uf = new  WeightedQuickUnionUF(N*N+1);
    }
    
    private int ijToNum(int i, int j) {
        return this.N * (j - 1) + i;
    }
    
    private boolean zeroOrLess(int z) {
        if (z <= 0) {
            return true;
        } 
        if (z > this.N) { 
            return true;
        }
        return false;
    }
    
    private void unionIt(int a, int b, int c, int d) {
        int r1, r2;
        
        r1 = uf.find(ijToNum(a, b));
        r2 = uf.find(ijToNum(c, d));
        if (b == 1 || d == 1 || wetRoots[r1] || wetRoots[r2]) {
            wetRoots[r1] = true;
            wetRoots[r2] = true;
        }
        this.uf.union(r1, r2);
    }
    
    public void open(int i, int j) {
        if (zeroOrLess(i) || zeroOrLess(j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        //make the site opened
        this.field[i][j] = true;
        
        //if 1 row then make this root wet
        if (j == 1) {
            wetRoots[uf.find(ijToNum(i, j))] = true;
        }
        
        if (i > 1 && this.isOpen(i-1, j)) {
            unionIt(i, j, i-1, j);
        }
        if (i < this.N && this.isOpen(i+1, j)) {
            unionIt(i, j, i+1, j);
        }
        if (j > 1 && this.isOpen(i, j-1)) {
            unionIt(i, j, i, j-1);
        }
        if (j < this.N && this.isOpen(i, j+1)) {
            unionIt(i, j, i, j+1);
        }
        for (int j2 = 1; j2 <= this.N; j2++) {
            if (isOpen(j2, j2)) {
                if (wetRoots[uf.find(ijToNum(j2, this.N))]) {
                    this.fullOnBottom = true;
                }
            }
        }
    }
    
    public boolean isOpen(int i, int j) {
        if (zeroOrLess(i) || zeroOrLess(j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return field[i][j];
    }
    
    public boolean isFull(int i, int j) {
        if (zeroOrLess(i) || zeroOrLess(j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return wetRoots[uf.find(ijToNum(i, j))];
    }
    
    public boolean percolates() {
        return fullOnBottom;
    }
//    public void draw(){
//        for (int i = 1; i <= this.N; i++) 
//            for (int j = 1; j <= this.N; j++) {
//                if (!this.isOpen(i, j)){
//                    StdDraw.setPenColor(new Color(127, 127, 127));
//                    StdDraw.filledRectangle(i*0.09, 1 - j*0.09, 0.042, 0.042);
//                }
//                else {
//                    if (this.isFull(i, j)) {
//                        StdDraw.setPenColor(new Color(127, 127, 255));
//                        StdDraw.filledRectangle(i*0.09, 1 - j*0.09, 0.042, 0.042); 
//                    } else {
//                        StdDraw.setPenColor(new Color(255, 255, 255));
//                        StdDraw.filledRectangle(i*0.09, 1 - j*0.09, 0.042, 0.042); 
//                    }
//                }
//                
//            }
//        
//    }

}
