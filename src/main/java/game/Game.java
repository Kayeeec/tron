package game;


public interface Game {

	
	public void update();
	
	public void draw();
	
	public void init();

	public boolean isRunning();

	public void cleanup();

	public void endGame();

}
