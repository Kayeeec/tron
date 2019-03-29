package view;

import java.awt.*;

public interface ScreenFeaturesInterface {
    DisplayMode[] getCompatibleDisplayModes();

    DisplayMode findFirstCompatibaleMode();

    DisplayMode getCurrentDM();

    Graphics2D getGraphics();

    int getWidth();

    int getHeight();
}
