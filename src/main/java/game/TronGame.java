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
import model.TronPlayer;
import model.TwoDimensionalCoordinates;
import view.TronDrawingManager;
import view.TronScreenManager;

public class TronGame extends BasicGame {

	private List<TronPlayer> players;

	private static final int MOVE_AMT = 5;

	public TronGame() {

	}

	public void initializePlayers() {

		players = new LinkedList<TronPlayer>();
		players.add(new TronPlayer(new TwoDimensionalCoordinates(40, 40), Direction.RIGHT, Color.RED,
				new Keys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT)));
		players.add(new TronPlayer(new TwoDimensionalCoordinates(600, 440), Direction.DOWN, Color.YELLOW,
				new Mouse(MouseEvent.BUTTON1, MouseEvent.BUTTON3)));
		players.add(new TronPlayer(new TwoDimensionalCoordinates(400, 540), Direction.LEFT, Color.BLUE,
				new Keys(KeyEvent.VK_U, KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K)));
	}

	@Override
	public void initializePresentation() {

		screenManager = new TronScreenManager();
		drawManager = new TronDrawingManager(screenManager, players);
		screenManager.setUp(players);

	}

	@Override
	public void updatePositions() {

		updateTronPlayerPositions();
	}

	private void updateTronPlayerPositions() {

		for (TronPlayer player : players) {
			player.updatePosition(MOVE_AMT, screenManager.getHeight(), screenManager.getWidth());
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
