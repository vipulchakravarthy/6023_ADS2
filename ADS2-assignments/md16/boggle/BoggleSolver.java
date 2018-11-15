import java.util.ArrayList;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TrieST<Integer> trie;
	ArrayList<String> list;
	private int rows;
	private int cols;
	public BoggleSolver(String[] dictionary, BoggleBoard board) {
		trie = new TrieST<Integer>();
		int[] scores = {0,0,0,1,1,2,3,5,11};
		rows = board.rows();
		cols = board.cols();
		list = new ArrayList<String>();
		for(int i = 0; i < dictionary.length; i++) {
			if(dictionary[i].length() > 8){
				trie.put(dictionary[i], 11);
			} else {
				trie.put(dictionary[i], scores[dictionary[i].length()]);
			}
		}
	}

	public boolean isValid(String word) {
		// Queue<String> queue = trie.keysWithPrefix(word);
		if(!trie.hasPrefix(word)) {
			return false;
		}
		return true;
	}
	public String check(char ch) {
		if(ch == 'Q') {
			return "QU";
		}
		return ch + "";
	}
	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public ArrayList<String> getAllValidWords(BoggleBoard board) {
		boolean[][] visited;
		for(int i = 0; i< board.rows(); i++) {
			for(int j = 0; j < board.cols(); j++) {
				visited = new boolean[board.rows()][board.cols()];
				dfs(board, i, j, check(board.getLetter(i, j)), visited);
			}
			visited = new boolean[board.rows()][board.cols()];
		}
		return list;
	}
	public boolean index(int i , int j) {
		if(i < 0 || i >= rows || j < 0 || j >= cols) {
			return false;
		}
		return true;
	}
	private void dfs(BoggleBoard board, int row, int col, String word, boolean[][] visited) {
	if (row < 0 || col < 0 || row >= rows || col >= cols){
		return;
	}
		visited[row][col] = true;
		if(trie.contains(word) && (!list.contains(word))) {
			list.add(word);
		}
		if(index(row+1, col + 1) && !visited[row+1][col + 1] && isValid(word)) {
			dfs(board, row+ 1, col + 1, word + check(board.getLetter(row+1, col + 1)), visited);
			visited[row + 1][col + 1] = false;
		}
		if(index(row - 1, col - 1) && !visited[row-1][col - 1] && isValid(word)) {
			dfs(board, row- 1, col - 1, word + check(board.getLetter(row-1, col - 1)), visited);
			visited[row - 1][col - 1] = false;
		}
		if( index(row - 1, col + 1) && !visited[row-1][col + 1] && isValid(word)) {
			dfs(board, row - 1, col + 1, word + check(board.getLetter(row-1, col + 1)), visited);
			visited[row - 1][col + 1] = false;
		}
		if( index(row+1, col - 1) && !visited[row +1][col - 1] && isValid(word)) {
			dfs(board, row+ 1, col - 1, word + check(board.getLetter(row+1, col - 1)), visited);
			visited[row + 1][col - 1] = false;
		}
		if( index(row-1, col) && !visited[row-1][col] && isValid(word)) {
			dfs(board, row -1, col, word + check(board.getLetter(row - 1, col)), visited);
			visited[row - 1][col] = false;
		}
		if( index(row+1, col) && !visited[row+1][col] && isValid(word)) {
			dfs(board, row+ 1, col, word + check(board.getLetter(row+1, col)), visited);
			visited[row + 1][col] = false;
		}
		if( index(row, col + 1) && !visited[row][col + 1] && isValid(word)) {
			dfs(board, row, col + 1, word + check(board.getLetter(row, col + 1)), visited);
			visited[row][col + 1] = false;
		}
		if(index(row, col - 1) && !visited[row][col - 1] && isValid(word)) {
			dfs(board, row, col - 1, word + check(board.getLetter(row, col - 1)), visited);
			visited[row][col - 1] = false;
		}
	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		return trie.get(word);
	}
}
