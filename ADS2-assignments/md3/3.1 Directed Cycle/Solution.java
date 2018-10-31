import java.util.Scanner;
/**
 *the class for directed cycle.
 */
class DirectedCycle {
    /**
     *the marked array for.
     *maitaining the visited vertices.
     */
    private boolean[] marked;
    /**
     *the variable to maintain the stack.
     *status.
     */
    private boolean[] onStack;
    /**
     *edge to method to consider the.
     *vertices to which they are connected.
     */
    private int[] edgeTo;
    /**
     *stack for storing the path.
     */
    private Stack<Integer> stack;
    /**
     *the constructor for initialization.
     *
     * @param      graph  The graph
     */
    DirectedCycle(final DiGraph graph) {
        marked = new boolean[graph.vertices()];
        onStack = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i] && stack == null) {
                dfs(graph, i);
            }
        }
    }
    /**
     *the method for performing dfs.
     *time complexity is O(E)
     *E is the edges
     * @param      graph   The graph
     * @param      vertex  The vertex
     */
    public void dfs(final DiGraph graph,
                    final int vertex) {
        onStack[vertex] = true;
        marked[vertex] = true;
        for (int each : graph.adj(vertex)) {
            if (stack != null) {
                return;
            } else if (!marked[each]) {
                edgeTo[each] = vertex;
                dfs(graph, each);
            } else if (onStack[each]) {
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
    /**
     *the method to check whether cycle is.
     *there or not.
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return stack != null;
    }
}
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
    private Bag<Integer>[] adj;
    /**
     *the constructor is to initialize the input given.
     *for the graph.
     *
     * @param      scan  The scan
     */
    DiGraph(final Scanner scan) {
        vertices = scan.nextInt();
        int edge = scan.nextInt();
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        for (int i = 0; i < edge; i++) {
            int vertexOne = scan.nextInt();
            int vertexTwo = scan.nextInt();
            addEdge(vertexOne, vertexTwo);
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
}
/**
 *the class for solution.
 */
final class Solution {
    /**
     *an empty constructor.
     */
    private Solution() {
    }
    /**
     *the main to read the input.
     *
     * @param      args  The arguments
     */
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
