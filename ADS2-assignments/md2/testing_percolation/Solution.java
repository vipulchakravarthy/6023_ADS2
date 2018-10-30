import java.util.Scanner;
class Percolation {
	private int vertices;
	Percolation(final int vertex) {
		vertices = vertex;
	}
	public boolean percolates(final boolean[][] grid, final Graph graph) {
		for (int i = 0 ; i < vertices ; i++) {
			for (int j = 0 ; j < vertices; j++) {
				if (grid[i][j]) {
					int tmp = trans(i, j);
					if (i == 0) {
						graph.addEdge(tmp , vertices * vertices);
					}
					if (i == vertices - 1) {
						graph.addEdge(tmp , vertices * vertices + 1);
					}
					if (i - 1 >= 0 && grid[i - 1][j]) {
						graph.addEdge(tmp , trans(i - 1, j));
					}
					if (i + 1 < vertices  && grid[i + 1][j]) {
						graph.addEdge(tmp , trans(i + 1, j));
					}
					if (j - 1 >= 0 && grid[i][j - 1]) {
						graph.addEdge(tmp , trans(i, j - 1));
					}
					if (j + 1 < vertices && grid[i][j + 1]) {
						graph.addEdge(tmp , trans(i, j + 1));
					}
				}
			}
		}
		DepthFirstPaths object = new DepthFirstPaths(graph, vertices * vertices);
		return object.hasPathTo(vertices * vertices + 1);
	}
	public int  trans(int i , int j) {
		return ((i * vertices) + j);
	}
}
final class Solution {
	private Solution() {}
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		int vertices = sc.nextInt();
		Percolation obj = new Percolation(vertices);
		boolean[][] grid = new boolean[vertices][vertices];
		Graph graph = new Graph(vertices * vertices + 2);
		try {
			while (true) {
				int row = sc.nextInt();
				int col = sc.nextInt();
				grid[row - 1][col - 1] = true;
			}
		} catch (Exception e) {
		} finally {
			System.out.println(obj.percolates(grid, graph));
		}
	}
}
