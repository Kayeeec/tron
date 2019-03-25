package model;

public class Keys {

	private int up;
	private int down;
	private int left;
	private int right;

	public Keys(int up, int down, int left, int right) {

		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public int getUp() {

		return up;
	}
	
	public int getDown() {

		return down;
	}

	public int getLeft() {

		return left;
	}

	public int getRight() {

		return right;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + down;
		result = prime * result + left;
		result = prime * result + right;
		result = prime * result + up;
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Keys other = (Keys) obj;
		if (down != other.down) return false;
		if (left != other.left) return false;
		if (right != other.right) return false;
		if (up != other.up) return false;
		return true;
	}

}
