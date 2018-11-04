import java.util.ArrayList;
/**
 *the class to find the shortest.
 *ancestor path.
 */
public class SAP {
    /**
     *the graph object.
     */
    Digraph graph;
    /**
     *constructor is to initialize objects
     *
     * @param     g    graph object
     */
    public SAP(Digraph g) {
        graph = g;
    }
    public int[] length(ArrayList<Integer> listOne, ArrayList<Integer> listTwo) {
        int min = graph.V();
        int tempOne = 0;
        for(int i = 0; i < listOne.size(); i++) {
            for(int k = 0; k < listTwo.size(); k++) {
            BreadthFirstDirectedPaths bfsOne = new BreadthFirstDirectedPaths(graph, listOne.get(i));
            BreadthFirstDirectedPaths bfsTwo = new BreadthFirstDirectedPaths(graph, listTwo.get(k));
            for(int j = 0; j < graph.V(); j++) {
                if(bfsOne.hasPathTo(j) && bfsTwo.hasPathTo(j)) {
                    int sum = bfsOne.distTo[j] + bfsTwo.distTo[j];
                    if(sum < min) {
                        min = sum;
                        tempOne = j;
                    }
                }
            }
        }
    }
    int[] result = {min, tempOne};
    return result;
        }
}
