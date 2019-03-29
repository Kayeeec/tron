package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import enums.Direction;
import model.Keys;
import model.Player;

public class KeyListenerHandler implements KeyListener {

	private List<Player> players;

	public KeyListenerHandler(List<Player> players) {

		this.players = players;
	}

	public void keyPressed(KeyEvent event) {

		for (Player player : players) {
			if (player.controlledByEvent(event)) {
				Keys playerKeys = (Keys) player.getControls();
				
				if (event.getKeyCode() == playerKeys.getUp()) {
					if (player.getCurrentDirection() != Direction.DOWN) {
						player.setCurrentDirection(Direction.UP);
					}
					return;
				}
				else if (event.getKeyCode() == playerKeys.getDown()) {
					if (player.getCurrentDirection() != Direction.UP) {
						player.setCurrentDirection(Direction.DOWN);
					}
					return;
				}
				else if (event.getKeyCode() == playerKeys.getRight()) {
					if (player.getCurrentDirection() != Direction.LEFT) {
						player.setCurrentDirection(Direction.RIGHT);
					}
					return;
				}
				else if (event.getKeyCode() == playerKeys.getLeft()) {
					if (player.getCurrentDirection() != Direction.RIGHT) {
						player.setCurrentDirection(Direction.LEFT);
					}
					return;
				}
			}
		}

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}
