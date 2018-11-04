import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileNotFoundException;
/**
 *class for processing the.
 *word net
 */
final class Solution {
    /**
     *the graph object to build up a graph.
     */
    private static Digraph graph;
    /**
     *the hashmap to store the key values of.
     *word and vertices.
     */
    private static HashMap<Integer, ArrayList<String>> map;
    /**
     *another hashmap to store the word and.
     *corresponding vertices.
     */
    private static HashMap<String, ArrayList<Integer>> revmap;
    /**
     *an empty constructor.
     */
    private Solution() {
    }
    /**
     *the method is for reading the file and.
     *to put all the values in hashmap.
     * @param     file  The file
     */
    public  static void fileSynsets(final String file) {
        try {
            File folder = new File("Files");
            File[] files = folder.listFiles();
            map = new HashMap<Integer, ArrayList<String>>();
            revmap = new HashMap<String, ArrayList<Integer>>();
            ArrayList<String> list;
            for (File f : files) {
                if (f.getName().equals(file)) {
                    Scanner input = new Scanner(f);
                    while (input.hasNext()) {
                        String[] tokens = input.nextLine().split(",");
                        String[] words = tokens[1].split(" ");
                        list = new ArrayList<String>();
                        for (int i = 0; i < words.length; i++) {
                            list.add(words[i]);
                        }
                        map.put(Integer.parseInt(tokens[0]), list);
                    }
                }
            }
            for (Integer i : map.keySet()) {
                ArrayList<String> value = map.get(i);
                for (String strng : value) {
                    if (revmap.containsKey(strng)) {
                        ArrayList<Integer> it = revmap.get(strng);
                        it.add(i);
                        revmap.put(strng, it);
                    } else {
                        ArrayList<Integer> t = new ArrayList<Integer>();
                        t.add(i);
                        revmap.put(strng, t);
                    }
                }
            }
            int vertices = map.size();
            graph = new Digraph(vertices);
        } catch (FileNotFoundException e) {
            System.out.println("No file");
        }
    }
    /**
     *this method is to read the hypernynms.
     *and build the graph based on the.
     *edges given.
     *
     * @param      file  The file
     */
    public static void fileHypernm(final String file) {
        try {
            File folder = new File("Files");
            File[] files = folder.listFiles();
            for (File f : files) {
                if (f.getName().equals(file)) {
                    Scanner input = new Scanner(f);
                    while (input.hasNext()) {
                        String[] line = input.nextLine().split(",");
                        String token = line[0];
                        for (int i = 1; i < line.length; i++) {
                            graph.addEdge(Integer.parseInt(
                            token), Integer.parseInt(line[i]));
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file");
        }
    }
    /**
     *the main method is to read the.
     *input from user.
     *in this method we are going to.
     *check whether the graph is a
     *cycle or it has multiple roots or not.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String fileOne = scan.nextLine();
        String fileTwo = scan.nextLine();
        String type = scan.nextLine();
        int count = 0;
        fileSynsets(fileOne);
        fileHypernm(fileTwo);
        switch (type) {
        case "Graph":
            DirectedCycle cycleObj = new DirectedCycle(graph);
            for (int i = 0; i < graph.V(); i++) {
                int outdegree = graph.outdegree(i);
                if (outdegree == 0) {
                    count++;
                }
            }
            if (count > 1) {
                System.out.println("Multiple roots");
                return;
            }
            if (cycleObj.hasCycle()) {
                System.out.println("Cycle detected");
            } else {
                System.out.println(graph);
            }
            break;
        case "Queries":
            while (scan.hasNext()) {
                String[] tokens = scan.nextLine().split(" ");
                if (tokens[0].equals("null")) {
                    System.out.println("IllegalArgumentException");
                    return;
                }
                SAP sapobj = new SAP(graph);
                ArrayList<Integer> vertexOne = revmap.get(tokens[0]);
                ArrayList<Integer> vertexTwo = revmap.get(tokens[1]);
                int[] array = sapobj.length(vertexOne, vertexTwo);
                ArrayList<String> result = map.get(array[1]);
                String tmp = result.get(0);
                System.out.print("distance = " + array[0] + ", ancestor = ");
                for (int k = 0; k < result.size(); k++) {
                    if (k != result.size() - 1) {
                        System.out.print(result.get(k) + " ");
                    } else {
                        System.out.print(result.get(k));
                    }
                }
                System.out.println();
            }
            break;
        default: break;
        }
    }
}
