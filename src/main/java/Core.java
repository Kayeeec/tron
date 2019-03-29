import model.Game;
import model.TronGame;

public class Core {

	private Game game;

	public static void main(String[] args) {

		new Core().run();
	}

	public void run() {

		try {
			init();
			gameLoop();
		} finally {
			game.restoreScreen();
		}
	}

	public void init() {

		initializeGame();
		game.initializePlayers();
		game.initializePresentation();
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

			game.reDraw();

			pause();
		}

		game.endGame();
	}

	private void pause() {

		try {
			Thread.sleep(20);
		} catch (Exception ex) {
		}
	}

}
