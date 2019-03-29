package view;

import model.Player;

import java.awt.*;
import java.util.List;

public interface ScreenManagerInterface {
    Graphics2D getGraphics();

    void update();

    int getWidth();

    int getHeight();

    void restoreScreen();

    void setUp(List<Player> players);
}
