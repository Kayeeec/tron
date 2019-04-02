package view;

import controller.PlayerControlHandler;
import model.ScreenDimensions;

import java.awt.*;
import java.util.List;

public interface ScreenManager {

	Graphics2D getGraphics();

	void update();

	ScreenDimensions getScreenDimensions();

	void restoreScreen();

	void setUp(List<PlayerControlHandler> playerHandlers);
}
