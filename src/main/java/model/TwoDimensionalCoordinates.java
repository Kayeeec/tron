package model;

public class TwoDimensionalCoordinates extends Coordinates {

	private int X;
	private int Y;

	public TwoDimensionalCoordinates(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public int getX() {

		return X;
	}

	public void setX(int x) {

		X = x;
	}

	public int getY() {

		return Y;
	}

	public void setY(int y) {

		Y = y;
	}

}
