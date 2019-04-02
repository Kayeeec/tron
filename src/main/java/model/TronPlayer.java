package model;

import enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TronPlayer extends Player {

	private Coordinate position;

	private final List<Coordinate> path = new ArrayList<Coordinate>();

	public TronPlayer(Coordinate coordinate, Direction direction, Color color) {

		super(color, direction);
		this.position = coordinate;
	}

	public Coordinate getPosition() {

		return this.position;
	}

	public void appendPath(Coordinate coordinate) {

		this.path.add(coordinate);
	}

	public List<Coordinate> getPath() {

		return Collections.unmodifiableList(this.path);
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TronPlayer other = (TronPlayer) obj;
		if (path == null) {
			if (other.path != null) return false;
		}
		else if (!path.equals(other.path)) return false;
		if (position == null) {
			if (other.position != null) return false;
		}
		else if (!position.equals(other.position)) return false;
		return true;
	}

	public boolean isInCollisionWith(TronPlayer playerB) {

		for (Coordinate coord : playerB.getPath()) {
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