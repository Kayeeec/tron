package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import enums.Direction;
import view.DrawManager;
import view.DrawingInterface;
import view.ScreenManager;
import view.ScreenManagerInterface;

public class TronGame extends Game {

	private List<Player> players;

	private ScreenManagerInterface screenManager;
	private DrawingInterface drawManager;

	public TronGame() {

	}

	public void initializePlayers() {

		players = new LinkedList<Player>();
		players.add(new Player(40, 40, Direction.RIGHT, Color.RED,
				new Keys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT)));
		players.add(new Player(600, 440, Direction.DOWN, Color.YELLOW,
				new Keys(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D)));
//		players.add(new Player(400, 540, Direction.LEFT, Color.BLUE,
//				new Keys(KeyEvent.VK_U, KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K)));
	}

	@Override
	public void initializePresentation() {

		screenManager = new ScreenManager();
		drawManager = new DrawManager(screenManager, players);
		screenManager.setUp(players);

	}

	@Override
	public void updatePositions() {

		updateTronPlayerPositions();
	}

	private void updateTronPlayerPositions() {

		for (Player player : players) {
			int moveAmount = 5;
			switch (player.getCurrentDirection()) {
			case UP:
				if (player.getCentreY() > 0) {
					player.setCentreY(player.getCentreY() - moveAmount);
				}
				else {
					player.setCentreY(screenManager.getHeight());
				}
				break;
			case RIGHT:
				if (player.getCentreX() < screenManager.getWidth()) {
					player.setCentreX(player.getCentreX() + moveAmount);
				}
				else {
					player.setCentreX(0);
				}
				break;
			case DOWN:
				if (player.getCentreY() < screenManager.getHeight()) {
					player.setCentreY(player.getCentreY() + moveAmount);
				}
				else {
					player.setCentreY(0);
				}
				break;
			case LEFT:
				if (player.getCentreX() > 0) {
					player.setCentreX(player.getCentreX() - moveAmount);
				}
				else {
					player.setCentreX(screenManager.getWidth());
				}
				break;
			}

		}

	}

	@Override
	public void updateGameStatus() {

		if (shouldGameEnd()) {
			this.setRunning(false);
		}
	}

	private boolean shouldGameEnd() {

		return hasPlayersInCollision();
	}

	private boolean hasPlayersInCollision() {

		for (Player playerA : players) {
			for (Player playerB : players) {
				if (playerA.isInCollisionWith(playerB)) {
					return true;

				}
			}

		}
		return false;
	}

	@Override
	public void updateHistory() {

		updateTronPlayersHistory();

	}

	private void updateTronPlayersHistory() {

		for (Player player : players) {
			player.appendPath(player.getCentreX(), player.getCentreY());
		}
	}

	@Override
	public void endGame() {

		System.exit(0);

	}

	@Override
	public void reDraw() {

		Graphics2D g = screenManager.getGraphics();
		drawManager.draw(g);
		g.dispose();
		screenManager.update();

	}

	@Override
	public void restoreScreen() {

		screenManager.restoreScreen();
	}

}
