package model;

import enums.Direction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

	private int centreX;
	private int centreY;
	private Direction currentDirection;

	private Keys keys;

	private final Color color;

	List<Coordinates> path = new ArrayList<Coordinates>();

	public Player(int X, int Y, Direction direction, Color color, Keys keys) {

		this.centreX = X;
		this.centreY = Y;
		this.currentDirection = direction;
		this.color = color;
		this.keys = keys;
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

		this.path.add(new Coordinates(x, y));
	}

	public List<Coordinates> getPath() {

		return Collections.unmodifiableList(this.path);
	}

	public Color getColor() {

		return color;
	}

	public Coordinates getCoordinateAt(int index) {

		return this.path.get(index);
	}

	public Keys getKeys() {

		return keys;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Player other = (Player) obj;
		if (color == null) {
			if (other.color != null) return false;
		}
		else if (!color.equals(other.color)) return false;
		return true;
	}

	public boolean isInCollisionWith(Player playerB) {

		if (!this.equals(playerB)) {
			for (int i = 0; i < this.getPath().size(); i++) {
				Coordinates coordA = this.getCoordinateAt(i);
				Coordinates coordB = playerB.getCoordinateAt(i);
				if (isColliding(playerB, coordA, coordB)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isColliding(Player playerB, Coordinates coordA, Coordinates coordB) {

		return ((this.getCentreX() == coordA.getX()) && (this.getCentreY() == coordA.getY()))
				|| ((playerB.getCentreX() == coordB.getX()) && (playerB.getCentreY() == coordB.getY()))
				|| ((this.getCentreX() == coordB.getX()) && (this.getCentreY() == coordB.getY()))
				|| ((playerB.getCentreX() == coordA.getX()) && (playerB.getCentreY() == coordA.getY()));
	}

}
