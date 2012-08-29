import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Node first = null;
    private int N = 0;
    
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    public RandomizedQueue() { }          // construct an empty randomized queue

    public boolean isEmpty() {          // is the queue empty?
        return first == null;
    }
    public int size() {                 // return the number of items on the queue
        return N;
    }
    public void enqueue(Item item) {    // add the item
        Node oldFirst = first;
        
        if (item == null) throw new java.lang.NullPointerException();
        
        first = new Node();
        if (oldFirst != null) { oldFirst.next = first; }
        first.item = item;
        first.prev = oldFirst;
        N++;
    }
    public Item dequeue() {             // delete and return a random item
        Item item;
        
        if (N == 0) throw new java.util.NoSuchElementException();
        
        int rnd = StdRandom.uniform(N);
        Node current = first;
        for (int i = 0; i < rnd; i++)
            current = current.prev;
        N--;
        if (current.prev != null) { current.prev.next = current.next; }
        if (current.next != null) { current.next.prev = current.prev; }
        else { first = current.prev; }
        return current.item;
    }
    
    public Item sample() {              // return (but do not delete) a random item
        Item item;

        if (N == 0) throw new java.util.NoSuchElementException();

        int rnd = StdRandom.uniform(N);
        Node current = first;
        for (int i = 0; i < rnd; i++)
            current = current.prev;
        return current.item;
        
    }
    public Iterator<Item> iterator() {  // return an independent iterator over items in random order
        return new RQIterator();
    }
    
    private class RQIterator implements Iterator<Item> {
        private Node current = first;
        private Item item;
        
        public boolean hasNext() { return current != null;  }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (current != null) {
                item = current.item;
                current = current.prev;
                return item;
            }
            else throw new java.util.NoSuchElementException();
            
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("one");
        rq.enqueue("two");
        rq.enqueue("three");
        rq.enqueue("four");
        rq.enqueue("five");
        Iterator<String> i1 = rq.iterator();
        Iterator<String> i2 = rq.iterator();
        StdOut.println(i1.next());
        StdOut.println(i1.next());
        StdOut.println(i2.next());
        StdOut.println(i1.next());
        StdOut.println(i1.next());
        StdOut.println(i2.next());
    }

}
