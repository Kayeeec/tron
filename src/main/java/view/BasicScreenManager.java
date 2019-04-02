package view;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public abstract class BasicScreenManager implements ScreenManager {

	private final GraphicsDevice vc;

	private static final DisplayMode[] DISPLAY_MODES = {
			new DisplayMode(1680, 1050, 32, 0),
			new DisplayMode(800, 600, 32, 0),
			new DisplayMode(800, 600, 24, 0),
			new DisplayMode(800, 600, 16, 0),
			new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0),
			new DisplayMode(640, 480, 16, 0),
	};

	public BasicScreenManager() {

		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice();
	}

	public GraphicsDevice getVc() {

		return vc;
	}

	public int getWidth() {

		Window w = vc.getFullScreenWindow();
		if (w != null) {
			return w.getWidth();
		}

		return 0;

	}

	public int getHeight() {

		Window w = vc.getFullScreenWindow();
		if (w != null) {
			return w.getHeight();
		}

		return 0;

	}

	public void restoreScreen() {

		Window w = vc.getFullScreenWindow();
		if (w != null) {
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}

	private DisplayMode findFirstCompatibleMode() {

		DisplayMode[] goodModes = vc.getDisplayModes();
		for (DisplayMode mode : DISPLAY_MODES) {
			for (DisplayMode goodMode : goodModes) {
				if (displayModesMatch(mode, goodMode)) {
					return mode;
				}
			}
		}
		return null;
	}

	public void setFullScreen() {

		DisplayMode dm = findFirstCompatibleMode();

		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		vc.setFullScreenWindow(f);

		if (dm != null && vc.isDisplayChangeSupported()) {
			try {
				vc.setDisplayMode(dm);
			} catch (Exception ignored) {
			}
			f.createBufferStrategy(2);
		}
	}

	public Graphics2D getGraphics() {

		Window w = vc.getFullScreenWindow();
		if (w != null) {
			BufferStrategy bs = w.getBufferStrategy();
			return (Graphics2D) bs.getDrawGraphics();
		}

		return null;
	}

	private boolean displayModesMatch(DisplayMode m1, DisplayMode m2) {

		if (m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()) {
			return false;
		}
		if (m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& m1.getBitDepth() != m2.getBitDepth()) {
			return false;
		}
		return m1.getRefreshRate() == DisplayMode.REFRESH_RATE_UNKNOWN
				|| m2.getRefreshRate() == DisplayMode.REFRESH_RATE_UNKNOWN
				|| m1.getRefreshRate() == m2.getRefreshRate();
	}

	public void update() {

		Window w = vc.getFullScreenWindow();
		if (w != null) {
			BufferStrategy bs = w.getBufferStrategy();
			if (!bs.contentsLost()) {
				bs.show();
			}
		}
	}

}
