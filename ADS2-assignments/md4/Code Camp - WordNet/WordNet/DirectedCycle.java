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
    DirectedCycle(final Digraph graph) {
        marked = new boolean[graph.V()];
        onStack = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
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
    public void dfs(final Digraph graph,
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
