package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import enums.Direction;
import model.Keys;
import model.Mouse;
import model.Player;
import model.TronPlayer;
import view.DrawManager;
import view.DrawingInterface;
import view.ScreenManager;
import view.ScreenManagerInterface;

public class TronGame extends Game {

	private List<TronPlayer> players;
	private ScreenManagerInterface screenManager;
	private DrawingInterface drawManager;

	public TronGame() {

	}

	public void initializePlayers() {

		players = new LinkedList<TronPlayer>();
		players.add(new TronPlayer(40, 40, Direction.RIGHT, Color.RED,
				new Keys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT)));
		players.add(new TronPlayer(600, 440, Direction.DOWN, Color.YELLOW, new Mouse(MouseEvent.BUTTON1, MouseEvent.BUTTON3)));
		players.add(new TronPlayer(400, 540, Direction.LEFT, Color.BLUE,
				new Keys(KeyEvent.VK_U, KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K)));
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

		for (TronPlayer player : players) {
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

	public boolean shouldGameEnd() {
		return hasPlayersInCollision();
	}

	private boolean hasPlayersInCollision() {

		for (TronPlayer playerA : players) {
			for (TronPlayer playerB : players) {
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

		for (TronPlayer player : players) {
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
