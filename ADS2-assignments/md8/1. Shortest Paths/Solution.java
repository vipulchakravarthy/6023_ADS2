import java.util.Scanner;
import java.util.HashMap;
/**
 *class for edge.
 */
class DirectedEdge {
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
    DirectedEdge(final int v, final int w,
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
 }
 class EdgeWeightedDiGraph {
   /**
     *the variable to store vertices.
     */
    private int vertices;
    /**
     *the array of bags to store.
     *complete adjacency list.
     */
    private Bag<DirectedEdge>[] adj;
    /**
     *the constructor to initialize.
     *
     * @param      v vertices count
     */
    EdgeWeightedDiGraph(final int v) {
        this.vertices = v;
        adj = (Bag<DirectedEdge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<DirectedEdge>();
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
    public void addEdge(final DirectedEdge edge) {
        int vertexOne = edge.either();
        int vertexTwo = edge.other(vertexOne);
        adj[vertexOne].add(edge);
        adj[vertexTwo].add(edge);
    }
    /**
     *returns the iterator to return the bag at that.
     *vertex.
     * @param      vertex  The vertex
     *time complexity is O(1)
     * @return iterator at that vertex.
     */
    public Iterable<DirectedEdge> adj(final int vertex) {
        return adj[vertex];
    }
 }
 class DijkstrasSP {
 	private Double[] distTo;
 	private DirectedEdge[] edgeTo;
 	private IndexMinPQ<Double> pq;
 	EdgeWeightedDiGraph graph;
 	DijkstrasSP(EdgeWeightedDiGraph g, int source) {
 		graph = g;
 		distTo = new Double[graph.vertices()];
 		edgeTo = new DirectedEdge[graph.vertices()];
 		for (int i = 0; i < graph.vertices(); i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[source] = 0.0;
        pq = new IndexMinPQ<Double>(graph.vertices());
        pq.insert(source, distTo[source]);
        while(!pq.isEmpty()) {
        	int vertex = pq.delMin();
        	for(DirectedEdge edge: graph.adj(vertex)) {
        		relax(edge, vertex);
        	}
        }
 	}

 	private void relax(DirectedEdge edge, int vertex) {
 		int vertexTwo = edge.other(vertex);
 		if(distTo[vertexTwo] > distTo[vertex] + edge.weight()) {
 			distTo[vertexTwo] = distTo[vertex] + edge.weight();
 			edgeTo[vertexTwo] = edge;
 			if(pq.contains(vertexTwo)) {
 				pq.decreaseKey(vertexTwo, distTo[vertexTwo]);
 			} else {
 				pq.insert(vertexTwo, distTo[vertexTwo]);
 			}
 		}
 	}
     public double distTo(int v) {
       return distTo[v];
    }
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        int x = v;
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
    public double distance(int vertex) {
    	double sum = 0;
    	for(DirectedEdge each: pathTo(vertex)) {
    		sum += each.weight();
    	}
    	return sum;
    }
 }
class Solution{
	Solution(){
	}
	/**
	 *
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] tokens = scan.nextLine().split(" ");
		int edges = Integer.parseInt(tokens[1]);
		String[] vertices = scan.nextLine().split(" ");
		for(int i = 0; i < vertices.length; i++) {
			map.put(vertices[i], i);
		}
		DirectedEdge edgeObj;
		EdgeWeightedDiGraph digraph = new EdgeWeightedDiGraph(vertices.length);
		for(int i = 0; i < edges; i++) {
			String[] directPath = scan.nextLine().split(" ");
			edgeObj = new DirectedEdge(map.get(directPath[0]),
				map.get(directPath[1]),
				Double.parseDouble(directPath[2]));
			digraph.addEdge(edgeObj);
		}
		int queries = Integer.parseInt(scan.nextLine());
		DijkstrasSP spObj;
		for (int i = 0; i < queries; i++) {
			String[] check = scan.nextLine().split(" ");
			int source = map.get(check[0]);
			spObj = new DijkstrasSP(digraph, source);
			System.out.println((int) spObj.distance(map.get(check[1])));
		}
	}
}
