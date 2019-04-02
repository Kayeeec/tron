package view;

import model.TronPlayer;
import model.Coordinate;

import java.awt.*;
import java.util.List;

public class TronDrawingManager implements DrawingManager {

	private final List<TronPlayer> players;

	public TronDrawingManager(List<TronPlayer> players) {

		this.players = players;
	}

	public void draw(Graphics2D g, int screenWidth, int screenHeight) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenWidth, screenHeight);

		for (TronPlayer player : players) {
			for (Coordinate coordinate : player.getPath()) {
				g.setColor(player.getColor());
				g.fillRect(coordinate.getX(), coordinate.getY(), 10, 10);
			}
		}
	}
}
