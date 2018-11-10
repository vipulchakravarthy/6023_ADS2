import java.util.Iterator;
/**
 * Stack class.
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**
     * top of stack.
     */
    private Node<Item> first;
    /**
     * size of the stack.
     */
    private int n;
    /**
     * Class for node.
     * @param      <Item>  The item
     */
    private static class Node<Item> {
        /**
         * item of type Item.
         */
        private Item item;
        /**
         * next of type Node.
         */
        private Node<Item> next;
    }
    /**
     * Initializes an empty stack.
     */
    public Stack() {
        first = null;
        n = 0;
    }
    /**
     * Returns true if this stack is empty.
     * @return true if this stack is empty;
     * false otherwise
     * Time complexity for this method is O(1).
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
     * Returns the number of items in this stack.
     * @return the number of items in this stack
     * Time complexity for this method is O(1).
     */
    public int size() {
        return n;
    }
    /**
     * Adds the item to this stack.
     * @param  item the item to add
     * Time complexity for this method is O(1).
     */
    public void push(final Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**
     * Removes and returns the item most recently added to this stack.
     * @return the item most recently added
     * Time complexity for this method is O(1).
     */
    public Item pop() {
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        return item;                   // return the saved item
    }
    /**
     * Returns (but does not remove) the item most recently
     * added to this stack.
     * @return the item most recently added to this stack
     * Time complexity for this method is O(1).
     */
    public Item peek() {
        return first.item;
    }
    /**
     * Returns a string representation of this stack.
     * @return the sequence of items in this stack in
     * LIFO order, separated by spaces
     * Time complexity for this method is O(N).
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
    /**
     * Returns an iterator to this stack that
     * iterates through the items in LIFO order.
     * @return an iterator to this stack that iterates
     * through the items in LIFO order
     */
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    /**
     * Class for list iterator.
     * @param      <Item>  The item
     */
    private class ListIterator<Item> implements Iterator<Item> {
        /**
         * current of type Node.
         */
        private Node<Item> current;
        /**
         * Constructs the object.
         * @param      f  The first
         */
        ListIterator(final Node<Item> f) {
            current = f;
        }
        /**
         * Determines if it has next.
         * @return     True if has next, False otherwise.
         * Time complexity for this method is O(1).
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * remove method.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * This returns the next item of the current item.
         * @return     Item.
         * Time complexity for this method is O(1).
         */
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

