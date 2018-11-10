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
    public String toString() {
        return String.format("%d-%d %.5f",
         vertexOne, vertexTwo, weight);
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
     *the variable to store edges.
     */
    private int edges;
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
    EdgeWeightedGraph(final int v, final int e) {
        this.vertices = v;
        this.edges = e;
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
     *time complexity is O(V)
     * @return iterator at that vertex.
     */
    public Iterable<Edge> adj(final int vertex) {
        return adj[vertex];
    }
    /**
     *returns all the edges in graph.
     *time complexity is O(E)
     * @return bag of all the edges.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices " + edges + " edges" + "\n");
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
 }
 /**
 *the class for dijkstra's algorithm.
 *to find the shortest path.
 */
class DijkstrasSP {
    /**
     *the distTo array to store.
     *distance from one vertex to another.
     */
    private Double[] distTo;
    /**
     *edge to is to store the edge connected.
     */
    private Edge[] edgeTo;
    /**
     *indexed minpq to store the key value.
     *pair.
     */
    private IndexMinPQ<Double> pq;
    /**
     *the graph object.
     */
    private EdgeWeightedGraph graph;
    /**
     *the constructor to initialize the objects.
     *the time complexity is O(E + V).
     * @param      g  graph object.
     * @param      source  The source
     */
    DijkstrasSP(final EdgeWeightedGraph g,
                final int source) {
        graph = g;
        distTo = new Double[graph.vertices()];
        edgeTo = new Edge[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        pq = new IndexMinPQ<Double>(graph.vertices());
        pq.insert(source, distTo[source]);
        while (!pq.isEmpty()) {
            int vertex = pq.delMin();
            for (Edge edge : graph.adj(vertex)) {
                relax(edge, vertex);
            }
        }
    }
    /**
     *this method is to relax the edges.
     *time complexity is O(logE)
     * @param      edge    The edge
     * @param      vertex  The vertex
     */
    private void relax(final Edge edge,
    final int vertex) {
        int vertexTwo = edge.other(vertex);
        if (distTo[vertexTwo] > distTo[vertex] + edge.weight()) {
            distTo[vertexTwo] = distTo[vertex] + edge.weight();
            edgeTo[vertexTwo] = edge;
            if (pq.contains(vertexTwo)) {
                pq.decreaseKey(vertexTwo, distTo[vertexTwo]);
            } else {
                pq.insert(vertexTwo, distTo[vertexTwo]);
            }
        }
    }
    /**
     *the method returns the distance.
     *from the source to given vertex.
     *
     * @param      v  vertex
     *
     * @return distance between two vertices.
     */
    public double distTo(final int v) {
        return distTo[v];
    }
    /**
     *whether the path is there or not.
     *
     * @param      v another vertex.
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     *shortest path to given vertex.
     *
     * @param      v  vertex.
     *time complexity is O(ElogV)
     * @return shortest path is returned from the source.
     */
    public Iterable<Edge> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
    /**
     *returns the shortest distance between.
     *two vertices.
     *time complexity O(E)
     * @param      vertex  The vertex
     *
     * @return shortest distance between two vertices.
     */
    public double distance(final int vertex) {
        double sum = 0;
        for (Edge each : pathTo(vertex)) {
            sum += each.weight();
        }
        return sum;
    }
}
final class Solution {
	private Solution() {

	}
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		Edge edgeObj;
		EdgeWeightedGraph graph
		= new EdgeWeightedGraph(vertices, edges);
		for(int i = 0; i < edges; i++) {
			String[] tokens = scan.nextLine().split(" ");
            edgeObj = new Edge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[1]),
                               Double.parseDouble(tokens[2]));
            graph.addEdge(edgeObj);
		}
		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			System.out.println(graph);
			break;

		case "DirectedPaths":
			String[] check = scan.nextLine().split(" ");
			DijkstrasSP disp
			= new DijkstrasSP(graph, Integer.parseInt(check[0]));
			if(disp.hasPathTo(Integer.parseInt(check[1]))) {
				System.out.println(disp.distance(Integer.parseInt(check[1])));
			} else {
				System.out.println("No Path Found.");
			}
			break;
		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}
