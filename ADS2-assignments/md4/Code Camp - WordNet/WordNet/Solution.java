import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileNotFoundException;
class Solution {
	static Digraph graph;
	static HashMap<Integer, ArrayList<String>> map;
	static HashMap<String ,ArrayList<Integer>> revmap;
	Solution() {
	}
	public  static void fileSynsets(String file) {
	try{
		File folder = new File("Files");
		File[] files = folder.listFiles();
		map = new HashMap<Integer, ArrayList<String>>();
		revmap = new HashMap<String,ArrayList<Integer>>();
		ArrayList<String> list;
		for (File f : files) {
			if(f.getName().equals(file)) {
				Scanner input = new Scanner(f);
				while(input.hasNext()) {
					String[] tokens = input.nextLine().split(",");
					String[] words = tokens[1].split(" ");
					list = new ArrayList<String>();
					for(int i = 0; i < words.length; i++) {
						list.add(words[i]);
					}
					map.put(Integer.parseInt(tokens[0]), list);
				}
			}
		}
		for (Integer i : map.keySet()){
			ArrayList<String> value = map.get(i);
			for (String strng : value) {
				if (revmap.containsKey(strng)) {
					ArrayList<Integer> it = revmap.get(strng);
					it.add(i);
					revmap.put(strng,it);
				} else {
					ArrayList<Integer> t = new ArrayList<Integer>();
					t.add(i);
					revmap.put(strng,t);
				}
			}
		}
		int vertices = map.size();
		graph = new Digraph(vertices);
	} catch (FileNotFoundException e) {
            System.out.println("No file");
    }
	}
    public static void fileHypernm(String file) {
	try{
		File folder = new File("Files");
		File[] files = folder.listFiles();
		for (File f : files) {
			if(f.getName().equals(file)) {
				Scanner input = new Scanner(f);
				while(input.hasNext()) {
					String[] line = input.nextLine().split(",");
					String token = line[0];
					for(int i = 1; i < line.length; i++) {
						graph.addEdge(Integer.parseInt(token), Integer.parseInt(line[i]));
    			}
    		}
    	}
    }
	}catch(FileNotFoundException e) {
            System.out.println("No file");
    }
	}
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String fileOne = scan.nextLine();
		String fileTwo = scan.nextLine();
		String type = scan.nextLine();
		int count = 0;
		fileSynsets(fileOne);
		fileHypernm(fileTwo);
		switch(type) {
			case "Graph":
				DirectedCycle cycleObj = new DirectedCycle(graph);
				for(int i = 0; i < graph.V(); i++) {
					int outdegree = graph.outdegree(i);
					if(outdegree == 0 || outdegree == (graph.V() - 1)) {
						count++;
					}
				}
				if(count > 1) {
					System.out.println("Multiple roots");
					return;
				}
				if(cycleObj.hasCycle()) {
					System.out.println("Cycle detected");
				} else {
					System.out.println(graph);
				}
				break;
			case "Queries":
				while(scan.hasNext()) {
					String[] tokens = scan.nextLine().split(" ");
					if(tokens[0].equals(null)) {
						System.out.println("IllegalArgumentException");
						return;
					}
				// 	BreadthFirstDirectedPaths bfsObj = new BreadthFirstDirectedPaths();
				// 	ArrayList<Integer> distance = new ArrayList<Integer>();
				// 	ArrayList<Integer> listOne = revmap.get(tokens[0]);
				// 	for(int i = 0; i < listOne.size(); i++) {
				// 		bfsObj = new BreadthFirstDirectedPaths(graph, listOne.get(i));
				// 	}

				}
			 	break;
		}
		}
}
