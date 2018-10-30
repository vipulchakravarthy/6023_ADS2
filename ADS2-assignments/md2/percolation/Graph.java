import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *the class graph is to construct a graph.
 */
public class Graph {
    /**
     *the variable to maintain vertex.
     */
    private final int V;
    /**
     *the variable to maintain edges.
     */
    private int E;
    /**
     *the variable to adjacency matrix.
     */
    private boolean[][] adj;
    /**
     *the constructor to initialize.
     *
     * @param      V  vertices
     */
    public Graph(final int V) {
        this.V = V;
        this.E = 0;
        this.adj = new boolean[V][V];
    }
    /**
     *
     *the method to return vertices.
     * @return vertices.
     */
    public int V() {
        return V;
    }
    /**
     *the method is to return edges.
     *
     * @return  edges.
     */
    public int E() {
     return E;
 }
 /**
  * adds an edge
  *
  * @param      v  vertexOne
  * @param      w  vertexTwo
  */
    public void addEdge(final int v,
    final int w) {
        if (!adj[v][w]) E++;
        adj[v][w] = true;
        adj[w][v] = true;
    }
    /**
     *the method returns whether there is.
     *a connection between two vertices.
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return connection is there are not.
     */
    public boolean contains(final int v,
    final int w) {
        return adj[v][w];
    }
    /**
     * return list of neighbors of v
     *
     * @param      v  vertex
     * time complexity is O(N)
     * N is the vertices.
     * @return  iterator
     */
    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }
    /**
     *support iteration over graph vertices
     */
    private class AdjIterator implements Iterator<Integer>,
     Iterable<Integer> {
     	/**
     	 *the variable to make vertex count.
     	 */
        private int v;
        /**
         *the variable to store another vertex.
         */
        private int w = 0;
        /**
         *the constructor to initialize.
         *
         * @param      v vertex
         */

        AdjIterator(int v) {
            this.v = v;
        }
        /**
         *an iterator method.
         *
         * @return an iterator.
         */
        public Iterator<Integer> iterator() {
            return this;
        }
/**
 * has next method to maitain the connection.
 *
 * @return     True if has next, False otherwise.
 */
        public boolean hasNext() {
            while (w < V) {
                if (adj[v][w]) return true;
                w++;
            }
            return false;
        }
        /**
         * to go to next element.
         *
         * @return     { description_of_the_return_value }
         */
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return w++;
        }

        // public void remove()  {
        //     throw new UnsupportedOperationException();
        // }
    }
    /**
     *string representation of Graph - takes quadratic time.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
