package view;

import controller.PlayerControlHandler;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class TronScreenManager extends BasicScreenManager implements ScreenManager {

	private static final Font FONT = new Font("Arial", Font.PLAIN, 20);

	public TronScreenManager() {

		super();
	}

	public void setUp(List<PlayerControlHandler> playerHandlers) {
		Window w = getVc().getFullScreenWindow();
		getWindow(w);

		for (PlayerControlHandler ph : playerHandlers) {
			if (ph instanceof KeyListener) {
				w.addKeyListener((KeyListener) ph);
			}
			else if (ph instanceof MouseListener) {
				w.addMouseListener((MouseListener) ph);
			}
		}
	}

	private void getWindow(Window w) {
		setFullScreen();
		w.setFont(FONT);
		w.setBackground(Color.WHITE);
		w.setForeground(Color.RED);
		w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "null"));
	}

}
