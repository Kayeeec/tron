package view;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import controller.PlayerControlHandler;

public class TronScreenManager extends BasicScreenManager implements ScreenManager {

	private static final Font FONT = new Font("Arial", Font.PLAIN, 20);

	public TronScreenManager() {

		super();
	}

	@Override
	public void setUp(List<PlayerControlHandler> playerHandlers) {

		super.setUp(playerHandlers);

		Window w = getVc().getFullScreenWindow();

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
