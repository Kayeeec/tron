package model;

import enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TronPlayer extends Player {

	private TwoDimensionalCoordinates position;

	private final List<TwoDimensionalCoordinates> path = new ArrayList<TwoDimensionalCoordinates>();

	public TronPlayer(TwoDimensionalCoordinates coordinate, Direction direction, Color color) {

		super(color, direction);
		this.position = coordinate;
	}

	public TwoDimensionalCoordinates getPosition() {

		return this.position;
	}

	public int getCentreX() {

		return this.position.getX();
	}

	public void setCentreX(int centreX) {

		this.position.setX(centreX);
	}

	public int getCentreY() {

		return this.position.getY();
	}

	public void setCentreY(int centreY) {

		this.position.setY(centreY);
	}

	public void appendPath(TwoDimensionalCoordinates coordinate) {

		this.path.add(coordinate);
	}

	public List<TwoDimensionalCoordinates> getPath() {

		return Collections.unmodifiableList(this.path);
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
		else
			return getColor().equals(other.getColor());
	}

	public boolean isInCollisionWith(TronPlayer playerB) {

		for (TwoDimensionalCoordinates coord : playerB.getPath()) {
			if (coord.equals(this.getPosition())) {
				return true;
			}
		}

		return false;
	}

	public void updatePosition(int moveAmt, int maxHeight, int maxWidth) {

		switch (this.getCurrentDirection()) {
		case UP:
			if (this.position.getY() > 0) {
				this.position.setY(this.position.getY() - moveAmt);
			}
			else {
				this.position.setY(maxHeight);
			}
			break;
		case RIGHT:
			this.position.setX((this.position.getX() + moveAmt) % maxWidth);
			break;
		case DOWN:
			this.position.setY((this.position.getY() + moveAmt) % maxHeight);
			break;
		case LEFT:
			if (this.position.getX() > 0) {
				this.position.setX(this.position.getX() - moveAmt);
			}
			else {
				this.position.setX(maxWidth);
			}
			break;
		}

	}
}