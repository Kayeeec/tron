package model;

public class Coordinate implements Cloneable {

	private int X;
	private int Y;

	public Coordinate(int x, int y) {

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

	public boolean equals(Coordinate another) {

		return this.X == another.getX() && this.Y == another.getY();
	}

	public Coordinate clone() {

		return new Coordinate(X, Y);
	}

}
