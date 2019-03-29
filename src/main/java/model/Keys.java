package model;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Keys extends Controls {

	private final int up;
	private final int down;
	private final int left;
	private final int right;

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
		return up == other.up;
	}

	@Override
	public boolean hasInputEvent(InputEvent event) {
		if( event instanceof KeyEvent){
			int keyCode = ((KeyEvent) event).getKeyCode();
			return keyCode == this.down
					|| keyCode == this.up
					|| keyCode == this.left
					|| keyCode == this.right;
		}
		return false;
	}
}
