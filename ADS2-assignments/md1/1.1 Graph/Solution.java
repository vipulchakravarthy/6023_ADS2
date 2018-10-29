import java.util.Scanner;
import java.util.Arrays;
class GraphMat {
    private String[] tokens;
    private int[][] matrix;
    private int vertices;
    private int edges;
    GraphMat() {
        edges = 0;
    }
    GraphMat(Scanner scan) {
        this.vertices = Integer.parseInt(scan.nextLine());
        matrix = new int[vertices][vertices];
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addE(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }

    public void addE(int vertexOne, int vertexTwo) {
    if(vertexOne != vertexTwo) {
        if(!hasEdge(vertexOne, vertexTwo)) {
        matrix[vertexOne][vertexTwo] = 1;
        matrix[vertexTwo][vertexOne] = 1;
        edges++;
        }
    }
    }
    public boolean hasEdge(int vertexOne, int vertexTwo) {
        if(matrix[vertexOne][vertexTwo] == 1) {
            return true;
        }
        return false;
    }
    public void print() {
        String str = "";
        str += vertices + " vertices, " + edges + " edges" + "\n";
        if (edges > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    str += matrix[i][j] + " ";
                }
                str += "\n";
            }
            System.out.println(str);
        } else {
             str += "No edges";
            System.out.println(str);
        }
    }
}
class GraphList {
    private int vertices;
    private int edges;
    private Bag<Integer>[] adj;
    private String[] tokens;
    GraphList() {
    }
    GraphList(Scanner scan) {
        this.vertices = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
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
        if (vertexOne != vertexTwo) {
            if(!hasEdge(vertexOne, vertexTwo)) {
            adj[vertexOne].add(vertexTwo);
            adj[vertexTwo].add(vertexOne);
            edges++;
            }
        } else {
            return;
        }
    }
    public Iterable<Integer> adj(int vertex) {
        return adj[vertex];
    }
    public boolean hasEdge(int vertexOne, int vertexTwo) {
        for(int each: adj(vertexOne)) {
            if(each == vertexTwo) {
                return true;
            }
        }
        return false;
    }
    public String toString() {
            StringBuilder s = new StringBuilder();
            s.append(vertices + " vertices, " + edges + " edges" + "\n");
        if (edges > 0) {
            for (int i = 0; i < vertices; i++) {
                s.append(tokens[i] + ": ");
                for (int j : adj[i]) {
                    s.append(tokens[j] + " ");
                }
                s.append("\n");
            }
            return s.toString();
        } else {
            s.append("No edges");
            return s.toString();
        }
    }
}
public class Solution {
    Solution() {
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        switch (type) {
        case "List":
            GraphList graphObj = new GraphList(scan);
            System.out.println(graphObj);
            break;
        case "Matrix":
            GraphMat graphMat = new GraphMat(scan);
            graphMat.print();
            break;
        default : break;
        }
    }
}

