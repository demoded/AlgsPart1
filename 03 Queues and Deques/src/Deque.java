import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Item[]  s;
    private int     N = 0;
    private int     pFirst = 0;
    private int     pLast = 0;

    public Deque() { // construct an empty  deque
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty() { // is the deque empty?
        return N == 0;
    }

    public int size() { // return the number of items on the deque
        return N;
    }

    private void resizeMore() {
        Item[] t = (Item[]) new Object[s.length * 3];
        int tStart = t.length / 2 - N / 2;
        for (int i = pLast+1; i < pFirst; i++) {
            t[tStart++] = s[i];
        }
        s = t;
        pLast = t.length / 2 - N / 2 - 1;
        pFirst = tStart;
    }
    
    private void resizeLess() {
        Item[] t = (Item[]) new Object[s.length / 3];
        int tStart = t.length / 2 - N / 2;
        for (int i = pLast+1; i < pFirst; i++) {
            t[tStart++] = s[i];
        }
        s = t;
        if (N > 0) { pLast = t.length / 2 - N / 2 - 1; }
        else { pLast = 0; }
        pFirst = tStart;
    }
    
    public void addFirst(Item item) { // insert the item at the front
        if (item == null) { throw new java.lang.NullPointerException(); }
        if (pFirst <= s.length-1) {
            s[pFirst] = item;
            N++;
            if (pFirst == pLast) {
                pFirst++;
                pLast--;
            }
            else {
                pFirst++;
            }
        }
        else {
            resizeMore();
            s[pFirst++] = item;
            N++;
        }
    }

    public void addLast(Item item) { // insert the item at the end
        if (item == null) { throw new java.lang.NullPointerException(); }
        if (pLast < 0) {
            resizeMore();
            s[pLast--] = item;
        }
        else {
            s[pLast] = item;
            if (pLast == pFirst) {
                pLast--;
                pFirst++;
            }
            else {
                pLast--;
            }
        }
        N++;
    }

    public Item removeFirst() { // delete and return the item at the front
        if (N == 0) { throw new java.util.NoSuchElementException(); }
        Item item = s[--pFirst];
        s[pFirst] = null;
        N--;
        if (N <= (s.length / 9) && N > 0) { resizeLess(); }
        return item;
    }

    public Item removeLast() { // delete and return the item at the end
        if (N == 0) { throw new java.util.NoSuchElementException(); }
        Item item = s[++pLast];
        s[pLast] = null;
        N--;
        if (N <= (s.length / 9) && N > 0) { resizeLess(); }
        return item;
    }

    public Iterator<Item> iterator() { // return an iterator over items in order
                                       // from front to end
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {

        private Item item;
        private int current = pFirst - 1;
        
        public boolean hasNext() { return current > pLast; }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (s.length > current && s[current] != null){
                return s[current--];    
            }
            else throw new java.util.NoSuchElementException();
        }
    }
    
    private static void printAll(Deque d) {
        int count = 0;
        Iterator<Integer> i = d.iterator();
        while (i.hasNext()) {
            Integer n = i.next();
            StdOut.println(count++ + " " + n);
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Deque deq = new Deque<Integer>();

        deq.addFirst(1);
        deq.addFirst(2);
        deq.addFirst(3);
        Iterator<Integer> i1 = deq.iterator();
        Iterator<Integer> i2 = deq.iterator();
        StdOut.println(i1.next());
        StdOut.println(i2.next());
        StdOut.println(i1.next());
        StdOut.println(i1.next());
        StdOut.println(i2.next());
        
        
    }

}
