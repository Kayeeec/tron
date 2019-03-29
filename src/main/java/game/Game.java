package game;

import view.DrawingInterface;
import view.ScreenManagerInterface;

public abstract class Game {
	protected ScreenManagerInterface screenManager;
	protected DrawingInterface drawManager;

	private boolean running;

	public boolean isRunning() {

		return running;
	}

	public void setRunning(boolean running) {

		this.running = running;
	}

	public abstract void updatePositions();

	public abstract boolean shouldGameEnd();

	public abstract void updateHistory();

	public abstract void endGame();

	public abstract void initializePlayers();

	public abstract void initializePresentation();

	public abstract void reDraw();

	public abstract void restoreScreen();

}
