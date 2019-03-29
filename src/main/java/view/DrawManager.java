package view;

import model.Coordinates;
import model.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DrawManager implements DrawingInterface {

    private ScreenFeaturesInterface screenManager;
    private List<Player> players;

    public DrawManager(ScreenFeaturesInterface screenManager, List<Player> players) {
        this.screenManager = screenManager;
        this.players = players;
    }

    public void draw(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());

        for (Player player : players) {
            for (Coordinates coordinate : player.getPath()) {
                g.setColor(player.getColor());
                g.fillRect(coordinate.getX(), coordinate.getY(), 10, 10);
            }
        }
    }
}
