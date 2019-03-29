package view;

import java.awt.DisplayMode;
import java.awt.Graphics2D;

public interface ScreenFeaturesInterface {
    DisplayMode[] getCompatibleDisplayModes();

    DisplayMode findFirstCompatibaleMode();

    DisplayMode getCurrentDM();

    Graphics2D getGraphics();

    int getWidth();

    int getHeight();
}
