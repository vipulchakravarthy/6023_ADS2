import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
class PageRank {
	private int vertices;
	HashMap<Integer, Double> map;
	HashMap<Integer, ArrayList<Integer>> inLinks;
	DiGraph graph;
	PageRank(DiGraph g) {
		graph = g;
		vertices = graph.vertices();
		map = new HashMap<Integer, Double>();
		inLinks = new HashMap<Integer, ArrayList<Integer>>();
	}
	public void calculatePR() {
		Double sum = 0.0;
		ArrayList<Integer> list;
		double temp = (double) vertices;
		Double initialPR = (1 / temp);
		for(int i = 0; i < vertices; i++) {
			if(graph.indegree[i] == 0) {
				map.put(i, 0.0);
			} else {
				map.put(i, initialPR);
			}
		}
		for(int i = 0; i < vertices; i++) {
				for(int w:graph.adj[i]) {
					list = new ArrayList<Integer>();
					if(inLinks.containsKey(w)) {
						ArrayList<Integer> tempList = inLinks.get(w);
						tempList.add(i);
						inLinks.put(w, tempList);
					} else {
						list.add(i);
						inLinks.put(w, list);
					}
				}
		}
		for(int j = 0; j < 1000; j++) {
			for(int i = 0; i < vertices; i++) {
				if(graph.indegree[i] != 0) {
					ArrayList<Integer> linksList = inLinks.get(i);
					for (int each: linksList) {
						Double value = map.get(each);
						sum += (value / graph.outdegree(each));
            		}
            		map.put(i, sum);
            	}
			}
		}
	}
	public void print() {
	System.out.println(map);
	}

}

public class Solution {
	Solution(){

	}
	public static void main(final String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		DiGraph graph = new DiGraph(scan);
		System.out.println(graph);
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph
		// Create page rank object and pass the graph object to the constructor
		PageRank prObj = new PageRank(graph);
		prObj.calculatePR();
		prObj.print();
		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		// String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
