import java.awt.Graphics2D;

import model.Game;
import model.TronGame;
import view.DrawingInterface;
import view.ScreenManager;

public class Core {

	private ScreenManager screenManager;

	
	private DrawingInterface drawManager;
	
	private Game game;

	public static void main(String[] args) {

		new Core().run();
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
		initializeGame();
		game.initializePlayers();
		game.initializeScreenManager();
	}
	
	private void initializeGame() {
		game = new TronGame();
		game.setRunning(true);
	}

	

	public void gameLoop() {

		while (game.isRunning()) {
			game.updatePositions();

			game.updateGameStatus();

			game.updateHistory();

			reDraw();

			pause();
		}

		game.endGame();
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

}
