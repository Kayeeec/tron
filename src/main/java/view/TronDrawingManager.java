package view;

import model.ScreenDimensions;
import model.TronPlayer;
import model.Coordinate;

import java.awt.*;
import java.util.List;

public class TronDrawingManager implements DrawingManager {

	private final List<TronPlayer> players;
	private final ScreenDimensions screenDimensions;

	public TronDrawingManager(ScreenDimensions screenDimensions, List<TronPlayer> players) {

		this.screenDimensions = screenDimensions;
		this.players = players;
	}

	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenDimensions.getWidth(), screenDimensions.getHeight());

		for (TronPlayer player : players) {
			for (Coordinate coordinate : player.getPath()) {
				g.setColor(player.getColor());
				g.fillRect(coordinate.getX(), coordinate.getY(), 10, 10);
			}
		}
	}
}
