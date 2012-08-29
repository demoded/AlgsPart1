
public class Subset {

    public static void main(String[] args) {
        String s;

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int elements = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        } 
            
        for (int i = 0; i < elements; i++) {
            StdOut.println(rq.dequeue());
        }
        
    }

}
