/*************************************************************************
 *  Compilation:  javac Graph.java
 *  Execution:    java Graph input.txt
 *  Dependencies: Bag.java In.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/tinyG.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java Graph tinyG.txt
 *  13 vertices, 13 edges
 *  0: 6 2 1 5
 *  1: 0
 *  2: 0
 *  3: 5 4
 *  4: 5 6 3
 *  5: 3 4 0
 *  6: 0 4
 *  7: 8
 *  8: 7
 *  9: 11 10 12
 *  10: 9
 *  11: 9 12
 *  12: 11 9
 *
 *  % java Graph mediumG.txt
 *  250 vertices, 1273 edges
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15
 *  1: 220 203 200 194 189 164 150 130 107 72
 *  2: 141 110 108 86 79 51 42 18 14
 *  ...
 *
 *************************************************************************/

import java.util.Scanner;
/**
 *  The <tt>Graph</tt> class represents an undirected graph of vertices
 *  named 0 through V-1.
 *  It supports the following operations: add an edge to the graph,
 *  iterate over all of the neighbors adjacent to a vertex.
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/51undirected">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Graph {
    private final int vertices;
    private int edges;
    private Bag<Integer>[] adj;

   /**
     * Create an empty graph with V vertices.
     */
    public Graph(int vertices) {
        if (vertices < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.vertices = vertices;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

   /**
     * Create a random graph with V vertices and E edges.
     * Expected running time is proportional to V + E.
     */
    public Graph(int vertices, int edges) {
        this(vertices);
        if (edges < 0) throw new RuntimeException("Number of edges must be nonnegative");
        for (int i = 0; i < edges; i++) {
            int v = (int) (Math.random() * vertices);
            int w = (int) (Math.random() * vertices);
            addEdge(v, w);
        }
    }

   /**
     * Create a digraph from input stream.
     */
    Graph(final Scanner scan) {
        this.vertices = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        int edge = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }

   /**
     * Copy constructor.
     */
/*    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }*/

   /**
     * Return the number of vertices in the graph.
     */
    public int vertices() {
        return vertices;
    }

   /**
     * Return the number of edges in the graph.
     */
    public int edges() {
        return edges;
    }
   /**
     * Add the edge v-w to graph.
     */
    public void addEdge(int v, int w) {
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }


   /**
     * Return the list of neighbors of vertex v as in Iterable.
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


   // /**
   //   * Return a string representation of the graph.
   //   */
   //  public String toString() {
   //      StringBuilder s = new StringBuilder();
   //      String NEWLINE = System.getProperty("line.separator");
   //      s.append(vertices + " vertices, " + E + " edges " + NEWLINE);
   //      for (int v = 0; v < V; v++) {
   //          s.append(v + ": ");
   //          for (int w : adj[v]) {
   //              s.append(w + " ");
   //          }
   //          s.append(NEWLINE);
   //      }
   //      return s.toString();
   //  }

}