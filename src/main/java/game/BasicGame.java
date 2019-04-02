package game;

import view.DrawingManager;
import view.ScreenManager;

public abstract class BasicGame implements Game {
	protected ScreenManager screenManager;
	protected DrawingManager drawingManager;

	private boolean running;

	public boolean isRunning() {

		return running;
	}

	public void setRunning(boolean running) {

		this.running = running;
	}
	
	public void update() {
		updatePositions();
		
		if (shouldGameEnd()) {
			endGame();
		}
		
		updateHistory();
	}
	
	public void init() {
		initializePlayers();
		initializePresentation();
		setRunning(true);
	}

	public abstract void updatePositions();

	public abstract boolean shouldGameEnd();

	public abstract void updateHistory();

	public abstract void endGame();

	public abstract void initializePlayers();

	public abstract void initializePresentation();

	public abstract void draw();

	public void cleanup() {
		restoreScreen();
	}
	
	public abstract void restoreScreen();

}
