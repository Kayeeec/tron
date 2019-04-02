package view;

import java.awt.Graphics2D;
import java.util.List;

import controller.PlayerControlHandler;

public interface ScreenManager {

	Graphics2D getGraphics();

	void update();

	int getWidth();

	int getHeight();

	void restoreScreen();

	void setUp(List<PlayerControlHandler> playerHandlers);
}
