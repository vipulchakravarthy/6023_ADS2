import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileNotFoundException;
class Solution {
	static Digraph graph;
	Solution() {
	}
	public  static void fileSynsets(String file) {
	try{
		File folder = new File("Files");
		File[] files = folder.listFiles();
		HashMap<Integer, ArrayList<String>> map
		= new HashMap<Integer, ArrayList<String>>();
		HashMap<String ,ArrayList<Integer>> revmap
		= new HashMap<String,ArrayList<Integer>>();
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
		fileSynsets(fileOne);
		fileHypernm(fileTwo);
		switch(type) {
			case "Graph":
				System.out.println(graph);
				break;
			case "Queries":
			 	break;
		}
		}
}
