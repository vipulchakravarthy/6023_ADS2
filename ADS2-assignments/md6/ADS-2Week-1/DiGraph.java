import java.util.Scanner;
/**
 *class for digraph.
 */
class DiGraph {
    /**
     *the variable to store vetices.
     */
    private int vertices;
    /**
    *the variable to store number.
    *of edges.
    */
    private int edges;
    /**
     *the array to store bag of arrays.
     */
    public Bag<Integer>[] adj;
    public int[] indegree;
    DiGraph(int V) {
        // if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.vertices = V;
        this.edges = 0;
        indegree = new int[vertices];
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     *the constructor is to initialize the input given.
     *for the graph.
     *
     * @param      scan  The scan
     */
    DiGraph(final Scanner scan) {
        vertices = Integer.parseInt(scan.nextLine());
        // int edge = scan.nextInt();
        indegree = new int[vertices];
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        for(int k = 0; k < vertices; k++) {
            String[] tokens = scan.nextLine().split(" ");
            int vertexOne  = Integer.parseInt(tokens[0]);
            if(tokens.length > 2) {
                for(int i = 1; i < tokens.length; i++) {
                  addEdge(vertexOne, Integer.parseInt(tokens[i]));
                }
            } else if(tokens.length == 2) {
                addEdge(vertexOne, Integer.parseInt(tokens[1]));
            }
        }
    }
    /**
     *the method is to add an edge between.
     *two vertices.
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     * the time complexity is O(E + V)
     * V is the vertices
     * E denotes the number of edges we have in graph.
     *.
     */
    public void addEdge(final int vertexOne, final int vertexTwo) {
        adj[vertexOne].add(vertexTwo);
        indegree[vertexTwo]++;
        edges++;
    }
    /**
     *returns the vertices of graph.
     *
     * @return vertices of graph
     */
    public int vertices() {
        return vertices;
    }
    /**
     *returns the edges of graph.
     *
     * @return edges of graph
     */
    public int edges() {
        return edges;
    }
    public int outdegree(int v) {
        return adj[v].size();
    }
    public int indegree(int v) {
        return indegree[v];
    }
    /**
     *the method is to maintian a iteratable for.
     *bag
     *returns an interator.
     * @param      vertex  The vertex
     *
     * @return  iterator.
     */
    public Iterable<Integer> adj(final int vertex) {
        return adj[vertex];
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges " + "\n");
        for (int v = 0; v < vertices; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append("\n");
        }
        return s.toString();
    }
    public DiGraph reverse() {
        DiGraph reverse = new DiGraph(vertices);
        for (int v = 0; v < vertices; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }
}
