public class DepthFirstPaths {
    /**
     *the array to mark the visited.
     *vertex
     */
    private boolean[] marked;
    /**
     *the array to maintian the.
     *relation path.
     */
    private int[] edgeTo;
         // edgeTo[v] = last edge on s-v path
    /**
     *the variable to maintain source vertex.
     */
    private final int s;         // source vertex
    /**
     * Computes a path between s and
     * every other vertex in graph
     * @param graph the graph
     * @param s the source vertex
     */
    public DepthFirstPaths(final Graph graph,
    final int st) {
        this.s = st;
        edgeTo = new int[graph.vertices()];
        marked = new boolean[graph.vertices()];
        validateVertex(s);
        dfs(graph, s);
    }

    /**
     *the method is to perform the depth.
     *first search.
     *time complexity is O(E)
     *E is the edges.
     * @param      graph     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Graph graph, final int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    /**
     * Is there a path between the source.
     * vertex { s} and vertex {v}?
     * @param v the vertex
     * @return true if there is a path,
     * false} otherwise
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     *throw an IllegalArgumentException unless.
     *@param v is the vertex
     */
    private void validateVertex(final int v) {
        int len = marked.length;
        if (v < 0 || v >= len) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (len - 1));
        }
    }
}
