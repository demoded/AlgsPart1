import java.util.Comparator;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();        // compare points by slope to this point

    private final int x;
    private final int y;

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (slopeTo(a) > slopeTo(b)) return -1;
            if (slopeTo(a) < slopeTo(b)) return +1;
            return 0;
        }
    }
    
    public Point(int x, int y) {                        // construct the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() {                              // draw this point
        StdDraw.setPenRadius(0.005);
        StdDraw.point(x, y);      
    }
    
    public void drawTo(Point that) {                  // draw the line segment from this point to that point
        StdDraw.setPenRadius(0.002);
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {                          // string representation
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {               // is this point lexicographically smaller than that point?
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.y == that.y) {
            if (this.x < that.x) return -1;
            if (this.x > that.x) return +1;
        }
        return 0;
    }
    
    public double slopeTo(Point that) {                 // the slope between this point and that point
        if (this.x == that.x) 
            if (this.y != that.y) return Double.POSITIVE_INFINITY;
            else return Double.NEGATIVE_INFINITY;
        return (double)(that.y - this.y) / (double)(that.x - this.x);
    }

    public static void main(String[] args) {
        
    }

}
