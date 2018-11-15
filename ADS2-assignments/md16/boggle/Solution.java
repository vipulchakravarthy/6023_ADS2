import java.util.Arrays;
/**
 * Class for solution.
 */
public class Solution {

	/**
	 * Constructs the object.
	 */
	private Solution() {
		// empty constructor
	}

	/**
	 * Main method.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		String caseType = StdIn.readLine();
		switch (caseType) {
		case "Score":
			String dictionaryName = StdIn.readLine();
			In in = new In("/Files/" + dictionaryName);
			String[] dictionary = in.readAllStrings();
			String boardName = StdIn.readLine();
			BoggleBoard board = new BoggleBoard("/Files/" + boardName);
			BoggleSolver solver = new BoggleSolver(dictionary, board);
			int score = 0;
			for (String word : solver.getAllValidWords(board)) {
				score += solver.scoreOf(word);
			}
			StdOut.println("Score = " + score);
			break;

		default:
			try {
				dictionaryName = StdIn.readLine();
				in = new In("/Files/" + dictionaryName);
				dictionary = in.readAllStrings();
				board = null;
				solver = new BoggleSolver(dictionary, board);
				score = 0;
				for (String word : solver.getAllValidWords(board)) {
					score += solver.scoreOf(word);
				}
				StdOut.println("Score = " + score);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			break;
		}

	}
}
