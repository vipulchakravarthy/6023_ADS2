import java.util.Scanner;
/**
 *class for ternary search trie.
 *
 * @param    <Value>  The value
 */
class TST<Value> {
    /**
     *the size of the trie.
     */
    private int size;
    /**
     *root of the trie.
     */
    private Node<Value> root;
    /**
     * Class for node.
     *
     * @param      <Value>  The value
     */
    private class Node<Value> {
        /**
         *variable which store character.
         */
        private char c;
        /**
         *it determines the node left.
         *right and middle links.
         */
        private Node<Value> left, mid, right;
        /**
         *the value to the corresponding node.
         */
        private Value val;
    }
    /**
     *an empty constructor.
     */
    TST() {
    }
    /**
     *returns the size of trie.
     *
     * @return returns the size of trie.
     */
    public int size() {
        return size;
    }
    /**
     *the method returns whether the key contains.
     *or not
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(final String key) {
        return get(key) != null;
    }
    /**
     *the method to get the value corresponding.
     *to string
     * @param      key   The key
     *time complexity is O(L + logN)
     *L is the length of string and.
     *N is the size of trie.
     * @return     { description_of_the_return_value }
     */
    public Value get(final String key) {
        Node<Value> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }
    /**
     *the private method for get method.
     *time complexity is O(L + logN)
     *L is the length of string and.
     *N is the size of trie.
     * @param      x    node
     * @param      key   The key
     * @param      d index of string
     *
     * @return  the value of string.
     */
    private Node<Value> get(final Node<Value> x,
                            final String key, final int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }
    /**
     *this method is to insert.
     *the string.
     *time complexity is O(L + logN)
     *L is the length of string and.
     *N is the size of trie.
     * @param      key   The key
     * @param      val   The value
     */
    public void put(final String key,
                    final Value val) {
        if (!contains(key)) {
            size++;
        }
        root = put(root, key, val, 0);
    }
    /**
     *the method is to insert the string.
     *time complexity is O(L + logN)
     *L is the length of string and.
     *N is the size of trie.
     * @param      temp   node
     * @param      key   The key
     * @param      val   The value
     * @param      d index of the string
     *
     * @return root  node.
     */
    private Node<Value> put(final Node<Value> temp,
                            final String key, final Value val, final int d) {
        Node<Value> x = temp;
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<Value>();
            x.c = c;
        }
        if (c < x.c) {
            x.left  = put(x.left,  key, val, d);
        } else if (c > x.c)   {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid   = put(x.mid,   key, val, d + 1);
        } else   {
            x.val   = val;
        }
        return x;
    }
    /**
     *this method returns keys with.
     *prefix
     *time complexity is O(L + logN)
     *L is the length of string and.
     *N is the size of trie.
     * @param      prefix  The prefix
     *
     * @return queue
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        Queue<String> queue = new Queue<String>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) {
            return queue;
        }
        if (x.val != null) {
            queue.enqueue(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }
    /**
     *collects all the elements with.
     *prefix and store in a queue
     *time complexity is O(L + logN)
     *L is the length of string and.
     *N is the size of trie.
     * @param      x  node
     * @param      prefix  The prefix
     * @param      queue   The queue
     */
    private void collect(final Node<Value> x,
                         final StringBuilder prefix,
                          final Queue<String> queue) {
        if (x == null) {
            return;
        }
        collect(x.left,  prefix, queue);
        if (x.val != null) {
            queue.enqueue(prefix.toString() + x.c);
        }
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }
}
/**
 *class for solution.
 */
final class Solution {
    /**
     *an empty constructor.
     */
    private Solution() {

    }
    /**
     *the main method to read the input.
     *time complexity O(words * word.length())
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        Scanner scan = new Scanner(System.in);
        String prefix = scan.nextLine();
        TST obj = new TST();
        for (int i = 0; i < words.length; i++) {
            String[] suffixes = new String[words[i].length()];
            for (int j = 0; j < words[i].length(); j++) {
                suffixes[j] = words[i].substring(j, words[i].length());
                obj.put(suffixes[j], 0);
            }
        }
        System.out.println(obj.keysWithPrefix(prefix));
    }
    /**
     * the method is to read the words.
     *
     * @return words array.
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}

