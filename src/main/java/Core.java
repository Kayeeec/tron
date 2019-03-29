import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import model.Direction;
import model.Keys;
import model.Player;
import view.DrawManager;
import view.DrawingInterface;
import view.ScreenManager;

public class Core {

	private boolean running;

	private ScreenManager screenManager;

	private List<Player> players;
	private DrawingInterface drawManager;

	public static void main(String[] args) {

		new Core().run();
	}

	public void stop() {

		running = false;
	}

	public void run() {

		try {
			init();
			gameLoop();
		} finally {
			screenManager.restoreScreen();
		}
	}

	public void init() {
		initializePlayers();
		screenManager = new ScreenManager();
		drawManager = new DrawManager(screenManager, players);
		screenManager.setUp(players);
		running = true;
	}

	private void initializePlayers() {
		players = new LinkedList<Player>();
		players.add(new Player(40, 40, Direction.RIGHT, Color.RED,
				new Keys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT)));
		players.add(new Player(600, 440, Direction.DOWN, Color.YELLOW,
				new Keys(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D)));
	}

	public void gameLoop() {

		while (running) {
			updatePositions();

			updateGameStatus();

			updateHistory();

			reDraw();

			pause();
		}

		endGame();
	}

	private void reDraw() {

		Graphics2D g = screenManager.getGraphics();
		drawManager.draw(g);
		g.dispose();
		screenManager.update();
	}

	private void pause() {

		try {
			Thread.sleep(20);
		} catch (Exception ex) {
		}
	}

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

	private void updateHistory() {

		updateTronPlayersHistory();
	}

	private void updateTronPlayersHistory() {

		for (Player player : players) {
			player.appendPath(player.getCentreX(), player.getCentreY());
		}
	}

	private boolean shouldGameEnd() {

		return hasPlayersInCollision();
	}

	private void endGame() {

		System.exit(0);
	}

	private void updateGameStatus() {

		if (shouldGameEnd()) {
			running = false;
		}
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

}
