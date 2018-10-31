import java.util.Scanner;
class Bipartite{
    private boolean[] marked;
    private boolean[] color;
    private boolean flag = true;
    Bipartite(Graph graph) {
        marked = new boolean[graph.vertices()];
        color = new boolean[graph.vertices()];
        for(int i = 0; i < graph.vertices(); i++) {
            if(!marked[i]) {
                dfs(graph, i);
            }
        }
    }
    private void dfs(Graph graph, int vertex) {
        marked[vertex] = true;
        for (int each : graph.adj(vertex)) {
            if(!marked[each]) {
                color[each] = !color[vertex];
                dfs(graph, each);
            }
            else if (color[each] == color[vertex]) {
                flag = false;
            }
        }
    }
    public boolean isBipartite() {
        return flag;
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
        Graph obj = new Graph(scan);
        Bipartite bipartiteObj = new Bipartite(obj);
        if (bipartiteObj.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
