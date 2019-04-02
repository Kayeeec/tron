package view;

import model.TronPlayer;
import model.Coordinate;

import java.awt.*;
import java.util.List;

public class TronDrawingManager implements DrawingManager {

	private final ScreenManager screenManager;
	private final List<TronPlayer> players;

	public TronDrawingManager(ScreenManager screenManager, List<TronPlayer> players) {

		this.screenManager = screenManager;
		this.players = players;
	}

	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());

		for (TronPlayer player : players) {
			for (Coordinate coordinate : player.getPath()) {
				g.setColor(player.getColor());
				g.fillRect(coordinate.getX(), coordinate.getY(), 10, 10);
			}
		}
	}
}
