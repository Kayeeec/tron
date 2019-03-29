package view;

import model.TronPlayer;
import model.TwoDimensionalCoordinates;

import java.awt.*;
import java.util.List;

public class DrawManager implements DrawingInterface {

	private final ScreenManagerInterface screenManager;
	private final List<TronPlayer> players;

	public DrawManager(ScreenManagerInterface screenManager, List<TronPlayer> players) {

		this.screenManager = screenManager;
		this.players = players;
	}

	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());

		for (TronPlayer player : players) {
			for (TwoDimensionalCoordinates coordinate : player.getPath()) {
				g.setColor(player.getColor());
				g.fillRect(coordinate.getX(), coordinate.getY(), 10, 10);
			}
		}
	}
}
