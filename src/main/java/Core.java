import controller.KeyListenerHandler;
import controller.MouseListenerHandler;
import controller.MouseMotionListenerHandler;
import model.Direction;
import model.Keys;
import model.Player;
import view.DrawManager;
import view.DrawingInterface;
import view.ScreenManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Core {

	private static final DisplayMode modes[] = {
			// new DisplayMode(1920,1080,32,0),
			new DisplayMode(1680, 1050, 32, 0),
			// new DisplayMode(1280,1024,32,0),
			new DisplayMode(800, 600, 32, 0), new DisplayMode(800, 600, 24, 0), new DisplayMode(800, 600, 16, 0),
			new DisplayMode(640, 480, 32, 0), new DisplayMode(640, 480, 24, 0), new DisplayMode(640, 480, 16, 0), };

	private boolean running;

	protected ScreenManager screenManager;

	List<Player> players;
	private static int moveAmount = 5;
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

		screenManager = new ScreenManager();
		DisplayMode dm = screenManager.findFirstCompatibaleMode(modes);
		screenManager.setFullScreen(dm);
		Window w = screenManager.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.WHITE);
		w.setForeground(Color.RED);
		w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "null"));
		running = true;


		players = new LinkedList<Player>();
		players.add(new Player(40, 40, Direction.RIGHT, Color.RED,
				new Keys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT)));
		players.add(new Player(600, 440, Direction.DOWN, Color.YELLOW,
				new Keys(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D)));

		drawManager = new DrawManager(screenManager, players);

		w = screenManager.getFullScreenWindow();
		w.addKeyListener(new KeyListenerHandler(players));
		w.addMouseListener(new MouseListenerHandler());
		w.addMouseMotionListener(new MouseMotionListenerHandler());
	}

	public void gameLoop() {

		long startTime = System.currentTimeMillis();
		long cumTime = startTime;

		while (running) {
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			update(timePassed);
			Graphics2D g = screenManager.getGraphics();
			updatePathAndPositions();
			drawManager.draw(g);
			g.dispose();
			screenManager.update();

			try {
				Thread.sleep(20);
			} catch (Exception ex) {
			}
		}
	}

	public void update(long timePassed) {

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
