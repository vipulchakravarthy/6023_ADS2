import java.util.Scanner;
/**
 *class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     *the variable to store.
     *vetexOne
     */
    private int vertexOne;
    /**
     *the variable to store.
     *other vertex.
     */
    private int vertexTwo;
    /**
     *the variable to store the weight of.
     *each edge.
     */
    private double weight;
    /**
     *the constructor to initialize the.
     *vertices and their edge weight
     * @param      v  vertexOne
     * @param      w  vertexTwo
     * @param      cost  weight of edge
     */
    Edge(final int v, final int w,
         final double cost) {
        this.vertexOne = v;
        this.vertexTwo = w;
        this.weight = cost;
    }
    /**
     *this method returns the weight of edge.
     *
     * @return  weight of edge
     */
    public double weight() {
        return this.weight;
    }
    /**
     *this method returns one vertex.
     *
     * @return  one end of edge.
     */
    public int either() {
        return vertexOne;
    }
    /**
     *returns the other end of vertex.
     *
     * @param      v already connected vertex
     *
     * @return another vertex
     */
    public int other(final int v) {
        if (v == vertexOne) {
            return vertexTwo;
        } else {
            return vertexOne;
        }
    }
    /**
     *
     *the method is to compare the two weights.
     *of edges.
     * @param      that  another edge
     *
     * @return  if this edge is  greater than that.
     * returns 1.
     * equal returns zero.
     */
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
/**
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**
     *the variable to store vertices.
     */
    private int vertices;
    /**
     *the array of bags to store.
     *complete adjacency list.
     */
    private Bag<Edge>[] adj;
    /**
     *the constructor to initialize.
     *
     * @param      v vertices count
     */
    EdgeWeightedGraph(final int v) {
        this.vertices = v;
        adj = (Bag<Edge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**
     *this method returns the vertices count.
     *
     * @return  number of vertices.
     */
    public int vertices() {
        return this.vertices;
    }
    /**
     *this method is to build the graph.
     *
     * @param      edge  The edge
     */
    public void addEdge(final Edge edge) {
        int vertexOne = edge.either();
        int vertexTwo = edge.other(vertexOne);
        adj[vertexOne].add(edge);
        adj[vertexTwo].add(edge);
    }
    /**
     *returns the iterator to return the bag at that.
     *vertex.
     * @param      vertex  The vertex
     *
     * @return iterator at that vertex.
     */
    public Iterable<Edge> adj(final int vertex) {
        return adj[vertex];
    }
    /**
     *returns all the edges in graph.
     *
     * @return bag of all the edges.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int i = 0; i < vertices(); i++) {
            // int selfLoops = 0;
            for (Edge e : adj(i)) {
                list.add(e);
            }
        }
        return list;
    }
}
/**
 *class for minimum spanning tree.
 */
class MinST {
    /**
     *the graph object.
     */
    private EdgeWeightedGraph graph;
    /**
     *queue to give the mst.
     */
    private Queue<Edge> mst;
    /**
     *intializes  the values.
     *
     * @param      g  graph object.
     */
    MinST(final EdgeWeightedGraph g) {
        graph = g;
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge edge : graph.edges()) {
            pq.insert(edge);
        }
        UF ufObj = new UF(graph.vertices());
        while (!pq.isEmpty()
                && mst.size() < graph.vertices() - 1) {
            Edge edge = pq.delMin();
            int vertexOne = edge.either();
            int vertexTwo = edge.other(vertexOne);
            if (!ufObj.connected(vertexOne, vertexTwo)) {
                ufObj.union(vertexOne, vertexTwo);
                mst.enqueue(edge);
            }
        }
    }
    /**
     *it returns all the edges on mst.
     *
     * @return  queue which contains all
     * vertices.
     */
    public Iterable<Edge> edges() {
        return mst;
    }
    /**
     *this method returns the total weight.
     *of mst.
     * @return weight of mst.
     */
    public double total() {
        double sum = 0.0;
        for (Edge e : edges()) {
            sum += e.weight();
        }
        return sum;
    }
}
/**
 *class for main method.
 */
final class Solution {
    /**
     *an empty constructor.
     */
    private Solution() {
    }
    /**
     *the main method to read the input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Edge edgeObj;
        EdgeWeightedGraph graph
            = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = scan.nextLine().split(" ");
            edgeObj = new Edge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[1]),
                               Double.parseDouble(tokens[2]));
            graph.addEdge(edgeObj);
        }
        MinST mstObj = new MinST(graph);
        double result = mstObj.total();
        System.out.format("%.5f", result);
    }
}
