package enums;

public enum Direction {

	UP, DOWN, LEFT, RIGHT;

	private Direction opposite;
	private Direction left;
	private Direction right;

	static {
		UP.opposite = DOWN;
		UP.left = LEFT;
		UP.right = RIGHT;

		DOWN.opposite = UP;
		DOWN.left = RIGHT;
		DOWN.right = LEFT;

		LEFT.opposite = RIGHT;
		LEFT.left = DOWN;
		LEFT.right = UP;

		RIGHT.opposite = LEFT;
		RIGHT.left = UP;
		RIGHT.right = DOWN;
	}

	public Direction getOpposite() {

		return opposite;
	}

	public Direction getLeft() {

		return left;
	}

	public Direction getRight() {

		return right;
	}
}
