import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;
import java.util.Comparator;
public class CircularSuffixArray {
    private String input;
    private Integer[] indices;
    public CircularSuffixArray(String s) {
        if(s == null) {
            throw new IllegalArgumentException("Input index is invalid.");
        }
        input = s;
        indices = new Integer[length()];
        for(int i  = 0 ; i < indices.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                int firstIndex = first;
                int secondIndex = second;
                for(int i = 0; i < input.length(); i++) {
                    char a = input.charAt(firstIndex);
                    char b = input.charAt(secondIndex);
                    if( a < b) {
                        return -1;
                    } else if(a > b) {
                        return 1;
                    }
                    ++firstIndex;
                    ++secondIndex;
                    if(firstIndex == input.length()) {
                        firstIndex = 0;
                    }
                    if(secondIndex == input.length()) {
                        secondIndex = 0;
                    }
                 }
                 return 0;
            }
        });
    }

    public int length() {
    	return input.length();
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
        if (i < 0 || i >= input.length()) {
            throw new IllegalArgumentException("Input index is invalid.");
        }
    	return indices[i];
    }
    // public static void main(String[] args) {
    //     CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
    //     for (int i = 0; i < csa.length(); ++i) {
    //         StdOut.print(csa.index(i) + " ");
    //     }
    //     StdOut.println();
    // }
}
