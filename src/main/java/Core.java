import game.Game;
import game.TronGame;

public class Core {

	private Game game;

	public static void main(String[] args) {

		new Core().run();
	}

	private void run() {

		try {
			init();
			gameLoop();
		} finally {
			game.restoreScreen();
		}
	}

	private void init() {

		game = new TronGame();
		game.initializePlayers();
		game.initializePresentation();
		game.setRunning(true);
	}

	private void gameLoop() {

		while (game.isRunning()) {
			game.updatePositions();

			if (game.shouldGameEnd()) {
				game.setRunning(false);
			}

			game.updateHistory();

			game.reDraw();

			pause();
		}

		game.endGame();
	}

	private void pause() {

		try {
			Thread.sleep(20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
