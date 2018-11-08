import java.awt.Color;
import java.lang.Math;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	public SeamCarver(Picture pic) {
		this.picture = pic;

	}
	// current picture
	public Picture picture() {
		return null;
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
		double xRed = (leftObj.getRed() - rightObj.getRed()) ^ 2;
		double xGreen = (leftObj.getGreen() - rightObj.getGreen()) ^ 2;
		double xBlue = (leftObj.getBlue() - rightObj.getBlue()) ^ 2;
		xCoordinate = xRed + xBlue + xGreen;
		Color topObj = picture.get(x - 1, y);
		Color bottomObj = picture.get(x + 1, y);
		double yRed = (topObj.getRed() - bottomObj.getRed()) ^ 2;
		double yGreen = (topObj.getGreen() - bottomObj.getGreen()) ^ 2;
		double yBlue = (topObj.getBlue() - bottomObj.getBlue()) ^ 2;
		yCoordinate = yRed + yBlue + yGreen;
		double sum = Math.sqrt((xCoordinate * xCoordinate) + (yCoordinate * yCoordinate));
		return sum;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}
