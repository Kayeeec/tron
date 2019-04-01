package view;

import model.TronPlayer;

import java.awt.*;
import java.util.List;

public interface ScreenManager {

	Graphics2D getGraphics();

	void update();

	int getWidth();

	int getHeight();

	void restoreScreen();

	void setUp(List<TronPlayer> players);
}
