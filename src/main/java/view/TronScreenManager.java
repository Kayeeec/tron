package view;

import controller.PlayerControlHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;

public class TronScreenManager extends BasicScreenManager implements ScreenManager {

	private static final Font font = new Font("Arial", Font.PLAIN, 20);

	public TronScreenManager() {

		super();
	}

	public void setUp(List<PlayerControlHandler> playerHandlers) {

		setFullScreen();
		Window w = vc.getFullScreenWindow();

		w.setFont(font);
		w.setBackground(Color.WHITE);
		w.setForeground(Color.RED);
		w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "null"));

		for (PlayerControlHandler ph : playerHandlers) {
			if (ph instanceof KeyListener) {
				w.addKeyListener((KeyListener) ph);
			}
			else if (ph instanceof MouseListener) {
				w.addMouseListener((MouseListener) ph);
			}
		}
	}

}
