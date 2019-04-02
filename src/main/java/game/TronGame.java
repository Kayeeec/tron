package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import controller.KeyEventHandler;
import controller.MouseEventHandler;
import controller.PlayerControlHandler;
import enums.Direction;
import model.Coordinate;
import model.TronPlayer;
import view.TronDrawingManager;
import view.TronScreenManager;

public class TronGame extends BasicGame {

	private List<TronPlayer> players;
	private List<PlayerControlHandler> playerHandlers;
	private static final int MOVE_AMT = 5;

	public TronGame() {

		super();
	}

	public void initializePlayers() {

		players = new LinkedList<>();
		playerHandlers = new LinkedList<>();

		TronPlayer player1 = new TronPlayer(new Coordinate(40, 40), Direction.RIGHT, Color.RED);
		TronPlayer player2 = new TronPlayer(new Coordinate(600, 440), Direction.DOWN, Color.YELLOW);

		players.add(player1);
		players.add(player2);

		MouseEventHandler playerHandler1 = new MouseEventHandler(player1, MouseEvent.BUTTON1, MouseEvent.BUTTON3);
		KeyEventHandler playerHandler2 = new KeyEventHandler(player2, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
				KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

		playerHandlers.add(playerHandler1);
		playerHandlers.add(playerHandler2);

	}

	@Override
	public void initializePresentation() {

		screenManager = new TronScreenManager();
		drawingManager = new TronDrawingManager(players);
		screenManager.setUp(playerHandlers);
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
			player.appendPath(player.getPosition().clone());
		}
	}

	@Override
	public void endGame() {

		System.exit(0);

	}

	@Override
	public void draw() {

		Graphics2D g = screenManager.getGraphics();
		drawingManager.draw(g, screenManager.getWidth(), screenManager.getHeight());
		g.dispose();
		screenManager.update();

	}

	@Override
	public void restoreScreen() {

		screenManager.restoreScreen();
	}

}
