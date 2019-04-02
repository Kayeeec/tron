package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import model.Coordinate;
import model.TronPlayer;

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
