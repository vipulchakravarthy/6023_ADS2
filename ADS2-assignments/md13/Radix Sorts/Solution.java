import java.util.Scanner;
import java.util.Arrays;
/**
 *class for least significant digit.
 *string sort.
 */
class LSDSort {
    /**
     *an empty constructor.
     */
    LSDSort() {
    }
    /**
     *this method is to perform the.
     *lsd sorting technique.
     *time complexity is O(W *N)
     *W is the fixed length
     *N is the array length
     * @param      array        The array
     * @param      fixedLength  The fixed length
     *
     * @return sorted array.
     */
    public String[] sort(final String[] array,
                         final int fixedLength) {
        int length = array.length;
        final int radix = 256;
        String[] aux = new String[length];
        for (int d = fixedLength - 1; d >= 0; d--) {
            int[] count = new int[radix + 1];
            for (int i = 0; i < length; i++) {
                count[array[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < radix; r++) {
                count[r + 1] += count[r];
            }
            for (int i = 0; i < length; i++) {
                aux[count[array[i].charAt(d)]++] = array[i];
            }
            for (int i = 0; i < length; i++) {
                array[i] = aux[i];
            }
        }
        return array;
    }
}
/**
 *class for solution.
 */
final class Solution {
    /**
     *an empty solution.
     */
    private Solution() {

    }
    /**
     *the main method for user input.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int inputs = Integer.parseInt(scan.nextLine());
        String[] rollNumbers = new String[inputs];
        for (int i = 0; i < inputs; i++) {
            rollNumbers[i] = scan.nextLine();
        }
        LSDSort lsdObj = new LSDSort();
        rollNumbers = lsdObj.sort(rollNumbers,
                                  rollNumbers[0].length());
        System.out.println(Arrays.toString(rollNumbers));
    }
}
