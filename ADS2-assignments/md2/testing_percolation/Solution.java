import java.util.*;
class Solution{
	static int V;
	static boolean checkPerc(boolean[][] grid ,graph gr){
		for(int i = 0 ; i < V ; i++){
			for(int j = 0 ; j < V; j++){
				if(grid[i][j]) {

					int tmp = trans(i,j);
					if(i == 0) gr.addEdge(tmp , V * V);
					if(i == V - 1)gr.addEdge(tmp , V * V + 1);
					if(i - 1 >= 0 && grid[i-1][j]) gr.addEdge(tmp , trans(i-1,j));
					if(i + 1 < V  && grid[i+1][j]) gr.addEdge(tmp , trans(i+1,j));
					if(j - 1 >= 0 && grid[i][j - 1]) gr.addEdge(tmp , trans(i,j - 1));
					if(j + 1 < V && grid[i][j + 1]) gr.addEdge(tmp , trans(i,j+ 1));
				}
			}
		}
		DepthFirstPaths d = new DepthFirstPaths(gr, V * V);
		return d.hasPathTo(V * V + 1);
	}

	static int trans(int i , int j){
		return i * V + j;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		boolean[][] grid = new boolean[V][V];
		graph gr = new graph(V * V + 2);
		try{
			while(true){
				int row = sc.nextInt(), col = sc.nextInt();
				grid[row-1][col-1] = true;
			}
		}catch(Exception e){
		}finally{
			checkPerc(grid, gr);
		}


	}
}
