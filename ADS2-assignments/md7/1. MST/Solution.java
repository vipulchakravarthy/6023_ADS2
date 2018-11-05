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
	private double weight;
	Edge(final int v, final int w,
	final double cost) {
		this.vertexOne = v;
		this.vertexTwo = w;
		this.weight = cost;
	}
	public double weight() {
		return this.weight;
	}
	public int either() {
		return vertexOne;
	}
	public int other(final int v) {
		if(v == vertexOne) {
			return vertexTwo;
		} else {
			return vertexOne;
		}
	}
	public int compareTo(Edge that) {
		if (this.weight < that.weight) {
			return -1;
		} else if(this.weight > that.weight) {
			return 1;
		} else {
			return 0;
		}
	}
}
class EdgeWeightedGraph {
	private int vertices;
	private Bag<Edge>[] adj;
	EdgeWeightedGraph(final int v) {
		this.vertices = v;
		adj = (Bag<Edge>[]) new Bag[vertices];
		for (int i = 0; i < vertices; i++) {
			adj[i] = new Bag<Edge>();
		}
	}
	public int vertices() {
		return this.vertices;
	}
	public void addEdge(final Edge edge) {
		int vertexOne = edge.either();
		int vertexTwo = edge.other(vertexOne);
		adj[vertexOne].add(edge);
		adj[vertexTwo].add(edge);
	}
	public Iterable<Edge> adj(final int vertex) {
		return adj[vertex];
	}
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

class MinST {
	private EdgeWeightedGraph graph;
	private Queue<Edge> mst;
	MinST(EdgeWeightedGraph g) {
		graph = g;
		mst = new Queue<Edge>();
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for (Edge edge: graph.edges()) {
				pq.insert(edge);
		}
		UF ufObj = new UF(graph.vertices());
		while(!pq.isEmpty() && mst.size() < graph.vertices() - 1) {
			Edge edge = pq.delMin();
			int vertexOne = edge.either();
			int vertexTwo = edge.other(vertexOne);
			if (!ufObj.connected(vertexOne, vertexTwo)) {
				ufObj.union(vertexOne, vertexTwo);
				mst.enqueue(edge);
			}
		}
	}
	public Iterable<Edge> edges() {
		return mst;
	}

	public double total() {
		double sum = 0.0;
		for(Edge e: edges()) {
			sum += e.weight();
		}
		return sum;
	}
}
/**
 *class for main method.
 */
class Solution{
	/**
	 *an empty constructor.
	 */
	Solution(){

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
		EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
		for(int i = 0; i < edges; i++) {
			String[] tokens = scan.nextLine().split(" ");
			edgeObj = new Edge(Integer.parseInt(tokens[0]),
			Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]));
			graph.addEdge(edgeObj);
		}
		MinST mstObj = new MinST(graph);
		double result = mstObj.total();
		System.out.format("%.5f", result);
	}
}
