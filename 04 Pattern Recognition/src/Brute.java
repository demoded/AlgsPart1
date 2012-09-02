import java.util.Arrays;


public class Brute {

    public static void main(String[] args) {
        int pointsNum;
        Point[] p;

//        StdDraw.setCanvasSize(900, 900);
//        StdDraw.setPenRadius(0.005);
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
        
        In in = new In(args[0]);
        pointsNum = in.readInt();
        p = new Point[pointsNum];
        
        for (int i = 0; i < pointsNum; i++) {
            p[i] = new Point(in.readInt(), in.readInt());
//            p[i].draw();
        }
        Point z = new Point(0, 0);
        Arrays.sort(p, z.SLOPE_ORDER);
        for (int i = 0; i < p.length-3; i++) {
            for (int j = i+1; j < p.length-2; j++) {
                for (int j2 = j+1; j2 < p.length-1; j2++) {
                    for (int k = j2+1; k < p.length; k++) {
                        if (p[i].slopeTo(p[j])  == p[i].slopeTo(p[j2]) &&
                            p[i].slopeTo(p[j2]) == p[i].slopeTo(p[k])) 
                        {
                            StdOut.println(p[i].toString() + " -> " +
                                         p[j].toString() + " -> " +
                                         p[j2].toString() + " -> " +
                                         p[k].toString());
//                            p[i].drawTo(p[k]);
                        }
                    }
                }
            }
        }

    }

}
