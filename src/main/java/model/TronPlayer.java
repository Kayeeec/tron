package model;

import enums.Direction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TronPlayer extends Player {

	private int centreX;
	private int centreY;
	private Direction currentDirection;

	List<TwoDimensionalCoordinates> path = new ArrayList<TwoDimensionalCoordinates>();

	public TronPlayer(int X, int Y, Direction direction, Color color, Controls controls) {
		super(color, controls);

		this.centreX = X;
		this.centreY = Y;
		this.currentDirection = direction;
	}

	public int getCentreX() {

		return centreX;
	}

	public void setCentreX(int centreX) {

		this.centreX = centreX;
	}

	public int getCentreY() {

		return centreY;
	}

	public void setCentreY(int centreY) {

		this.centreY = centreY;
	}

	public Direction getCurrentDirection() {

		return currentDirection;
	}

	public void setCurrentDirection(Direction currentDirection) {

		this.currentDirection = currentDirection;
	}

	public void appendPath(int x, int y) {

		this.path.add(new TwoDimensionalCoordinates(x, y));
	}

	public List<TwoDimensionalCoordinates> getPath() {

		return Collections.unmodifiableList(this.path);
	}

	public TwoDimensionalCoordinates getCoordinateAt(int index) {

		return this.path.get(index);
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TronPlayer other = (TronPlayer) obj;
		if (getColor() == null) {
			if (other.getColor() != null) return false;
		}
		else if (!getColor().equals(other.getColor())) return false;
		return true;
	}

	public boolean isInCollisionWith(TronPlayer playerB) {

		if (!this.equals(playerB)) {
			for (int i = 0; i < this.getPath().size(); i++) {
				TwoDimensionalCoordinates coordA = this.getCoordinateAt(i);
				TwoDimensionalCoordinates coordB = playerB.getCoordinateAt(i);
				if (isColliding(playerB, coordA, coordB)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isColliding(TronPlayer playerB, TwoDimensionalCoordinates coordA, TwoDimensionalCoordinates coordB) {

		return ((this.getCentreX() == coordA.getX()) && (this.getCentreY() == coordA.getY()))
				|| ((playerB.getCentreX() == coordB.getX()) && (playerB.getCentreY() == coordB.getY()))
				|| ((this.getCentreX() == coordB.getX()) && (this.getCentreY() == coordB.getY()))
				|| ((playerB.getCentreX() == coordA.getX()) && (playerB.getCentreY() == coordA.getY()));
	}

}
