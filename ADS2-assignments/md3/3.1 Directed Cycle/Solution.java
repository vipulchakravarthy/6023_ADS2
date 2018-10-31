 import java.util.Scanner;
 class DirectedCycle {
 	private boolean[] marked;
 	private boolean[] onStack;
 	private int[] edgeTo;
 	private Stack<Integer> stack;
 	DirectedCycle(final DiGraph graph) {
 		marked = new boolean[graph.vertices()];
 		onStack = new boolean[graph.vertices()];
 		edgeTo = new int[graph.vertices()];
 		for(int i = 0; i < graph.vertices(); i++) {
 			if(!marked[i] && stack == null) {
 				dfs(graph, i);
 			}
 		}
 	}
 	public void dfs(DiGraph graph, int vertex) {
 		onStack[vertex] = true;
 		marked[vertex] = true;
 		for(int each: graph.adj(vertex)) {
 			if(stack != null) {
 				return;
 			}
 			else if(!marked[vertex]) {
 				edgeTo[each] = vertex;
 				dfs(graph, each);
 			}
            else if (onStack[each]) {
                stack = new Stack<Integer>();
                for (int j = vertex; j != each; j = edgeTo[j]) {
                    stack.push(j);
                }
                stack.push(each);
                stack.push(vertex);
            }
 		}
 		onStack[vertex] = false;
 	}
    public boolean hasCycle(){
    	return stack != null;
    }

 }
 class DiGraph{
 	private int vertices;
 	private int edges;
 	private Bag<Integer>[] adj;
 	DiGraph(final Scanner scan) {
 		vertices = scan.nextInt();
 		int edge = scan.nextInt();
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
 		for(int i = 0; i < edge; i++) {
 			int vertexOne = scan.nextInt();
 			int vertexTwo = scan.nextInt();
 			addEdge(vertexOne, vertexTwo);
 		}
 	}
 	public void addEdge(final int vertexOne, final int vertexTwo) {
 		adj[vertexOne].add(vertexTwo);
 		edges++;
 	}
 	public int vertices(){
 		return vertices;
 	}
 	public int edges(){
 		return edges;
 	}
 	public Iterable<Integer> adj(final int vertex) {
 		return adj[vertex];
 	}
 }
 class Solution{
 	Solution(){
 	}
 	public static void main(final String[] args) {
 		Scanner scan = new Scanner(System.in);
 		DiGraph obj = new DiGraph(scan);
 		DirectedCycle cycleObj = new DirectedCycle(obj);
 		if (cycleObj.hasCycle()) {
 				System.out.println("Cycle exists.");
 		} else {
 			System.out.println("Cycle doesn't exists.");
 		}
 	}
 }
