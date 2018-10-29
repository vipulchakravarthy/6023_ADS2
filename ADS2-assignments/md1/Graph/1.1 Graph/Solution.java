import java.util.Scanner;
import java.util.Arrays;
// interface Graph {
//     public int V();
//     public int E();
//     public void addEdge(int v, int w);
//     public Iterable<Integer> adj(int v);
//     public boolean hasEdge(int v, int w);
// }
class Graph {
    private int vertices;
    private int edges;
    private Bag<Integer>[] adj;
    private String[] tokens;
    Graph(){
    }
    Graph(Scanner scan) {
        this.vertices = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for(int i = 0; i < edge; i++){
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    public int vertices() {
        return vertices;
    }
    public int edges() {
        return edges;
    }
    public void addEdge(int vertexOne, int vertexTwo) {
        adj[vertexOne].add(vertexTwo);
        adj[vertexTwo].add(vertexOne);
        edges++;
    }
    public Iterable<Integer> adj(int vertex) {
        return adj[vertex];
    }
    // public boolean hasEdge(int vertexOne, int vertexTwo) {

    // }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges" + "\n");
        for (int i = 0; i < vertices; i++) {
            s.append(tokens[i] + ": ");
            for (int j : adj[i]) {
                s.append(tokens[j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
public class Solution {
    Solution() {
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        Graph graphObj = new Graph(scan);
        System.out.println(graphObj);
    }
}

