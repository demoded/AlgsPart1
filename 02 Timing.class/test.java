public class test {
    public static void main(String[] args) {
        Stopwatch s = new Stopwatch();
        Timing.trial(512, 511881);
        double t = s.elapsedTime();
        StdOut.println(t);
    }
}