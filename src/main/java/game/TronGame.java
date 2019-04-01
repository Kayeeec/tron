package game;

import controller.KeyEventHandler;
import controller.MouseEventHandler;
import controller.PlayerControlHandler;
import enums.Direction;
import model.TronPlayer;
import model.TwoDimensionalCoordinates;
import view.TronDrawingManager;
import view.TronScreenManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class TronGame extends BasicGame {

	private List<TronPlayer> players;
	private List<PlayerControlHandler> playerHandlers;

	public TronGame() {

	}

	public void initializePlayers() {

		players = new LinkedList<TronPlayer>();
		playerHandlers = new LinkedList<PlayerControlHandler>();

		TronPlayer player1 = new TronPlayer(new TwoDimensionalCoordinates(40, 40), Direction.RIGHT, Color.RED);
		TronPlayer player2 = new TronPlayer(new TwoDimensionalCoordinates(600, 440), Direction.DOWN, Color.YELLOW);

		players.add(player1);
		players.add(player2);

		MouseEventHandler playerHandler1 = new MouseEventHandler(player1, MouseEvent.BUTTON1, MouseEvent.BUTTON3);
		KeyEventHandler playerHandler2 = new KeyEventHandler(player2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

		playerHandlers.add(playerHandler1);
		playerHandlers.add(playerHandler2);

	}

	@Override
	public void initializePresentation() {
		screenManager = new TronScreenManager();
		drawManager = new TronDrawingManager(screenManager, players);
		screenManager.setUp(playerHandlers);

	}

	@Override
	public void updatePositions() {

		updateTronPlayerPositions();
	}

	private void updateTronPlayerPositions() {

		for (PlayerControlHandler playerHandler : playerHandlers) {
			TronPlayer player = (TronPlayer) playerHandler.getPlayer();
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
	public void draw() {

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
