import java.util.Scanner;
class ConnectedComp{
	private boolean[] marked;
	private int[] identifier;
	private int count;
	ConnectedComp(Graph graph, int source){
		marked = new boolean[graph.vertices()];
		identifier  = new int[graph.vertices()];
		for(int i = 0; i < graph.vertices(); i++) {
			if(!marked[i]) {
				dfs(graph,i);
				count++;
			}
		}
	}
	public int count(){
		return count;
	}
	public int identifer(int vertex) {
		return identifier[vertex];
	}
	private void dfs(Graph graph, int vertex) {
		marked[vertex] = true;
		identifier[vertex] = count;
		for(int each: graph.adj(vertex)) {
			if(!marked[each]) {
				dfs(graph, each);
			}
		}
	}
	public boolean percolates(){
		if(count == 1){
			return true;
		}
		return false;
	}
}
class Graph {
	private int[][] grid;
	private int vertices;
	Graph(final int size) {
		grid = new int[size][size];
		vertices = size;
	}
	public int vertices(){
		return vertices;
	}
	public void open(final int row, final int col){
		grid[row][col] = 1;
	}
    public int[] adj(final int vertex) {
        return grid[vertex];
    }
}
final class Solution {
	Solution() {
	}
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String arraySize = scan.nextLine();
		Graph percolatesObj = new Graph(
		    Integer.parseInt(arraySize));
		while (scan.hasNext()) {
			String line = scan.nextLine();
			String[] tokens = line.split(" ");
			percolatesObj.open(Integer.parseInt(tokens[0]) - 1,
			                   Integer.parseInt(tokens[1]) - 1);
		}
		ConnectedComp obj = new ConnectedComp(percolatesObj,
		 Integer.parseInt(arraySize));
		System.out.println(obj.percolates());
	}
}
