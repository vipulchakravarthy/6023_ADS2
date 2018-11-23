// import java.util.LinkedList;
// import java.util.Arrays;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
public class MoveToFront {
    private static final int lgR = 8;
    // time complexity is O(N R)
    // public static void encode() {
    //  LinkedList<Integer> ascii = new LinkedList<Integer>();
    //  final int R = 256;
    //  for(int i = 0; i < R; i++) {
    //      ascii.add(i);
    //  }
    //  while(!BinaryStdIn.isEmpty()) {
    //      int ch = BinaryStdIn.readChar();
    //      int index = ascii.indexOf(ch);
    //      BinaryStdOut.write(index, lgR);
    //      ascii.remove(index);
    //      ascii.add(0, ch);
    //  }
    //  BinaryStdOut.close();
    // }

    public static void encode() {
        char[] ascii =  new char[256];

        for (int i = 0; i < 256; i++) {
            ascii[i] = (char)i;
        }

        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            char temp = ch;
            char temp2 = ch;
            for (int i = 0; i < 256; i++) {
                if (ch == ascii[i]) {
                    BinaryStdOut.write(i, lgR);
                    ascii[i] = temp;
                    break;
                }
                temp2 = ascii[i];
                ascii[i] = temp;
                temp = temp2;
            }
            // BinaryStdOut.write(ascii[0], lgR);
        }
        BinaryStdOut.close();
    }
    // time complexity is O(N R)
    // public static void decode() {
    //  LinkedList<Integer> ascii = new LinkedList<Integer>();
    //  final int R = 256;
    //  for(int i = 0; i < R; i++) {
    //      ascii.add(i);
    //  }
    //  while(!BinaryStdIn.isEmpty()) {
    //      int index = BinaryStdIn.readChar();
    //      int  ch = ascii.get(index);
    //      BinaryStdOut.write(ch, lgR);
    //      ascii.remove(index);
    //      ascii.add(0, ch);
    //  }
    //  BinaryStdOut.close();
    // }

    public static void decode() {
        char[] ascii =  new char[256];
        for (int i = 0; i < 256; i++) {
            ascii[i] = (char)i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            char temp = ascii[ch];
            char temp2 = ascii[ch];
            BinaryStdOut.write(ascii[ch], lgR);
            for (int i = 0; i < 256; i++) {
                if (ascii[ch] == ascii[i]) {
                    ascii[i] = temp2;
                    break;
                }
                temp2 = ascii[i];
                ascii[i] = temp;
                temp = temp2;
            }
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args.length == 0) return;
        if ("-".equals(args[0])) {
            encode();
        } else if ("+".equals(args[0])) {
            decode();
        }
    }
}
