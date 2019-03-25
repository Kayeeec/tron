import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.util.LinkedList;
import java.util.List;

import java.awt.event.KeyEvent;

public class yourclass extends Core {

	List<Player> players;
	private static int moveAmount = 5;

	public void init() {

		super.init();

		players = new LinkedList<Player>();
		players.add(new Player(40, 40, Direction.RIGHT, Color.RED,
				new Keys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT)));
		players.add(new Player(600, 440, Direction.DOWN, Color.YELLOW,
				new Keys(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D)));

		Window w = screenManager.getFullScreenWindow();
		w.addKeyListener(new KeyListenerHandler(players));
		w.addMouseListener(new MouseListenerHandler());
		w.addMouseMotionListener(new MouseMotionListenerHandler());
	}

	public static void main(String[] args) {

		new yourclass().run();
	}

	public void draw(Graphics2D g) {

		updatePathAndPositions();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());

		for (Player player : players) {
			for (Coordinates coordinate : player.getPath()) {
				g.setColor(player.getColor());
				g.fillRect(coordinate.getX(), coordinate.getY(), 10, 10);
			}
		}
	}

	private void updatePathAndPositions() {

		for (Player player : players) {
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

		exitOnCollision();

		for (Player player : players) {
			player.appendPath(player.getCentreX(), player.getCentreY());
		}
	}
	
	
	

	private void exitOnCollision() {

		if (hasPlayersInCollision()) {
			System.exit(0);
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
