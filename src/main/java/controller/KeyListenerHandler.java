package controller;

import enums.Direction;
import model.Keys;
import model.TronPlayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class KeyListenerHandler implements KeyListener {

	private final List<TronPlayer> players;

	public KeyListenerHandler(List<TronPlayer> players) {

		this.players = players;
	}

	public void keyPressed(KeyEvent event) {

		for (TronPlayer player : players) {
			if (player.controlledByEvent(event)) {
				Keys playerKeys = (Keys) player.getControls();
				
				if (event.getKeyCode() == playerKeys.getUp()) {
					if (player.getCurrentDirection() != Direction.UP.getOpposite()) {
						player.setCurrentDirection(Direction.UP);
					}
					return;
				}
				else if (event.getKeyCode() == playerKeys.getDown()) {
					if (player.getCurrentDirection() != Direction.DOWN.getOpposite()) {
						player.setCurrentDirection(Direction.DOWN);
					}
					return;
				}
				else if (event.getKeyCode() == playerKeys.getRight()) {
					if (player.getCurrentDirection() != Direction.RIGHT.getOpposite()) {
						player.setCurrentDirection(Direction.RIGHT);
					}
					return;
				}
				else if (event.getKeyCode() == playerKeys.getLeft()) {
					if (player.getCurrentDirection() != Direction.LEFT.getOpposite()) {
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
