import java.util.Comparator;
/**
 * Class for minimum pq.
 * @param      <Key>  The key
 */
public class MinPQ<Key> {
    /**
     * store items at indices 1 to n.
     */
    private Key[] pq;
    /**
     * number of items on priority queue.
     */
    private int n;
    /**
     * optional comparator.
     */
    private Comparator<Key> comparator;
    /**
     * Initializes an empty priority queue with the given initial capacity.
     * @param  initCapacity the initial capacity of this priority queue
     */
    public MinPQ(final int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }
    /**
     * Initializes an empty priority queue.
     */
    public MinPQ() {
        this(1);
    }
    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     * @param  initCapacity the initial capacity of this priority queue
     * @param  comp the order in which to compare the keys
     */
    public MinPQ(final int initCapacity, final Comparator<Key> comp) {
        this.comparator = comp;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }
    /**
     * Returns true if this priority queue is empty.
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }
    /**
     * helper function to double the size of the heap array.
     * @param      capacity  The capacity
     * The time complexity for this method is O(N)
     */
    private void resize(final int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }
    /**
     * Adds a new key to this priority queue.
     * @param  x the key to add to this priority queue
     * The time complexity for this method is O(1)
     */
    public void insert(final Key x) {
        // double size of array if necessary
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
    }
    /**
     * Removes and returns a smallest key on this priority queue.
     * @return a smallest key on this priority queue
     * The time complexity for this method O(log N)
     */
    public Key delMin() {
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 2 + 2)) {
            resize(pq.length / 2);
        }
        return min;
    }
    /**
     * swim method.
     * @param      k     index.
     * The time complexity for this method is O(log N)
     */
    private void swim(final int k) {
        int k1 = k;
        while (k1 > 1 && greater(k1 / 2, k1)) {
            exch(k1, k1 / 2);
            k1 = k1 / 2;
        }
    }
    /**
     * sink method.
     * @param      k     index.
     * The time complexity for this method is O(log N)
     */
    private void sink(final int k) {
        int k1 = k;
        while (2 * k1 <= n) {
            int j = 2 * k1;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k1, j)) {
                break;
            }
            exch(k1, j);
            k1 = j;
        }
    }
    /**
     * greater method to compare the elements.
     * @param      i     index.
     * @param      j     index.
     * @return     true or false.
     */
    private boolean greater(final int i, final int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }
    /**
     * exch method to swap the elements.
     * @param      i     index.
     * @param      j     index.
     */
    private void exch(final int i, final int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
}
