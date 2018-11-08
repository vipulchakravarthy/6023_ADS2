import java.awt.Color;
import java.lang.Math;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	private int width;
	private int height;
	public SeamCarver(Picture pic) {
		this.picture = pic;
		width = picture.width();
		height = picture.height();
	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return this.picture.width();
	}

	// height of current picture
	public int height() {
		return this.picture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if(x == 0 || y == 0 || y == (picture.height() - 1) || x == (picture.width() - 1)) {
			return 1000.0;
		}
		double xCoordinate = 0.0;
		double yCoordinate = 0.0;
		Color object = picture.get(x,y);
		Color leftObj = picture.get(x, y - 1);
		Color rightObj = picture.get(x, y + 1);
		double xRed = Math.abs((leftObj.getRed() - rightObj.getRed()));
		double xGreen = Math.abs((leftObj.getGreen() - rightObj.getGreen()));
		double xBlue = Math.abs((leftObj.getBlue() - rightObj.getBlue()));
		xCoordinate = Math.pow(xRed, 2) + Math.pow(xBlue, 2) + Math.pow(xGreen, 2);
		Color topObj = picture.get(x - 1, y);
		Color bottomObj = picture.get(x + 1, y);
		double yRed = Math.abs((topObj.getRed() - bottomObj.getRed()));
		double yGreen = Math.abs((topObj.getGreen() - bottomObj.getGreen()));
		double yBlue = Math.abs((topObj.getBlue() - bottomObj.getBlue()));
		yCoordinate = Math.pow(yRed, 2) + Math.pow(yBlue, 2) + Math.pow(yGreen, 2);
		double sum = Math.sqrt((xCoordinate + yCoordinate));
		return sum;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		int[][] edgeTo = new int[height][width];
		double[][] distTo = new double[height][width];
		reset(distTo);
		int[] indices = new int[height];
        if(width == 1) {
        	return indices;
        }
		for(int i = 0; i < width; i++) {
			distTo[0][i] = 1000.0;
		}
		for (int i = 0; i < height - 1; i++) {
			for(int j = 0; j < width; j++) {
				relaxV(i, j, edgeTo, distTo);
			}
		}
        double minDist = Double.MAX_VALUE;
        int minCol = 0;
        for (int col = 0; col < width; col++) {
            if (minDist > distTo[height - 1][col]) {
                minDist = distTo[height - 1][col];
                minCol = col;
            }
        }
        for (int row = height -1, col = minCol; row >= 0; row--) {
            indices[row] = col;
            col -= edgeTo[row][col];
        }
        int temp = indices[0];
        indices[0] = temp + 1;
        return indices;
    }
	private void reset(double[][] distTo) {
		for(int i = 0; i < distTo.length; i++) {
			for(int j = 0; j < distTo[i].length; j++) {
				distTo[i][j] = Double.MAX_VALUE;
			}
		}
	}
	private void relaxV(int row, int col, int[][] edgeTo, double[][] distTo) {
		int nextRow = row + 1;
        for (int i = -1; i <= 1; i++) {
            int nextCol = col + i;
            if (nextCol < 0 || nextCol >= width) {
            	continue;
            }
            if (distTo[nextRow][nextCol] > distTo[row][col] + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
    	}
	}
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}
