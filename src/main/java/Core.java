import game.Game;
import game.TronGame;

public class Core {

	private Game game;
	
	public Core(Game game) {
		this.game = game;
	}

	public static void main(String[] args) {

		new Core(new TronGame()).run();
	}

	private void run() {

		try {
			game.init();
			gameLoop();
		} finally {
			game.cleanup();
		}
	}

	private void gameLoop() {

		while (game.isRunning()) {
			game.update();

			game.draw();

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
