import java.awt.Color;
import java.lang.Math;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	private int width;
	private int height;
    private int[][] colors;
	public SeamCarver(Picture pic) {
		this.picture = pic;
		width = picture.width();
		height = picture.height();
		colors = new int[height][width];
	    for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                colors[row][col] = picture.get(col, row).getRGB();
            }
	    }
	}
    public Picture picture() {
        // current picture
        Picture picture = new Picture(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                picture.set(col, row, new Color(colors[row][col]));
            }
        }
        return picture;
    }
	// current picture
	// public Picture picture() {
	// 	return picture;
	// }
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
        int[][] edgeTo = new int[height][width];
        double[][] distTo = new double[height][width];
        reset(distTo);
        for (int row = 0; row < height; row++) {
            distTo[row][0] = 1000;
        }
        for (int col = 0; col < width - 1; col++) {
            for (int row = 0; row < height; row++) {
                relaxH(row, col, edgeTo, distTo);
            }
        }
        double minDist = Double.MAX_VALUE;
        int minRow = 0;
        for (int row = 0; row < height; row++) {
            if (minDist > distTo[row][width - 1]) {
                minDist = distTo[row][width - 1];
                minRow = row;
            }
        }
        int[] indices = new int[width];
        for (int col = width - 1, row = minRow; col >= 0; col--) {
            indices[col] = row;
            row -= edgeTo[row][col];
        }
        return indices;
    }
    private void relaxH(int row, int col, int[][] edgeTo, double[][] distTo) {
        int nextCol = col + 1;
        for (int i = -1; i <= 1; i++) {
            int nextRow = row + i;
            if (nextRow < 0 || nextRow >= height) continue;
            if(i == 0) {
            	if(distTo[nextRow][nextCol] >= distTo[row][col]  + energy(nextCol, nextRow)) {
	                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
	                edgeTo[nextRow][nextCol] = i;
            	}
            }
            if (distTo[nextRow][nextCol] > distTo[row][col]  + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
	/**
	 *this method is to find the vertical seam.
	 *first of all find the shortest path from top to.
	 *bottom.
	 * .
	 *
	 * @return sequence of indices for vertical seam.
	 */
	public int[] findVerticalSeam() {
		double[][] energy = new double[height][width];
		int[][] edgeTo = new int[height][width];
		double[][] distTo = new double[height][width];
		reset(distTo);
		int[] indices = new int[height];
		if(width == 1 || height == 1) {
			return indices;
		}
		for(int i = 0; i < width; i++) {
			distTo[0][i] = 1000.0;
		}
		// this is for relaxation.
		for (int i = 0; i < height - 1; i++) {
			for(int j = 0; j < width; j++) {
				relaxV(i, j, edgeTo, distTo);
			}
		}
		// calculating from last row's coloumn
        double minDist = Double.MAX_VALUE;
        int minCol = 0;
        for (int col = 0; col < width; col++) {
            if (minDist > distTo[height - 1][col]) {
                minDist = distTo[height - 1][col];
                minCol = col;
            }
        }
        //indices values of shortest path.
        for (int row = height -1, col = minCol; row >= 0; row--) {
            indices[row] = col;
            col -= edgeTo[row][col];
        }
        indices[0] = indices[1];
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
            if(i == 0) {
            	if(distTo[nextRow][nextCol] >= distTo[row][col] + energy(nextCol, nextRow)) {
            	distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            	}
            }
            if (distTo[nextRow][nextCol] > distTo[row][col] + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
    	}
	}
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
	for(int col = 0; col < width; col++) {
		for(int row = seam[col]; row < height - 1; row++) {
			colors[row][col] = colors[row + 1][col];
		}
	}
	height--;
	}
	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
	for(int row = 0; row < height; row++) {
		for(int col = seam[row]; col < width - 1; col++) {
			colors[row][col] = colors[row][col + 1];
		}
	}
	width--;
	}
}
