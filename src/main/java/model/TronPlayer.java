package model;

import enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TronPlayer extends Player {

	private TwoDimensionalCoordinates coordinate;

	private final List<TwoDimensionalCoordinates> path = new ArrayList<TwoDimensionalCoordinates>();

	public TronPlayer(TwoDimensionalCoordinates coordinate, Direction direction, Color color) {
		super(color, direction);

		this.coordinate = coordinate;
	}

	public int getCentreX() {

		return this.coordinate.getX();
	}

	public void setCentreX(int centreX) {

		this.coordinate.setX(centreX);
	}

	public int getCentreY() {

		return this.coordinate.getY();
	}

	public void setCentreY(int centreY) {

		this.coordinate.setY(centreY);
	}

	public void appendPath(int x, int y) {

		this.path.add(new TwoDimensionalCoordinates(x, y));
	}

	public List<TwoDimensionalCoordinates> getPath() {

		return Collections.unmodifiableList(this.path);
	}

	private TwoDimensionalCoordinates getCoordinateAt(int index) {

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
			return other.getColor() == null;
		}
		else return getColor().equals(other.getColor());
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
