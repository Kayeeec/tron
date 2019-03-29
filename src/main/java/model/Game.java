package model;

public abstract class Game {

	private boolean running;

	public boolean isRunning() {

		return running;
	}

	public void setRunning(boolean running) {

		this.running = running;
	}
	
	public abstract void updatePositions();

	public abstract void updateGameStatus();

	public abstract void updateHistory();

	public abstract void endGame();
	public abstract void initializePlayers();
	public abstract void initializeScreenManager();
	
}
