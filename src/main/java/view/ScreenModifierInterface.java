package view;

import java.awt.*;

public interface ScreenModifierInterface {
    void setFullScreen(DisplayMode dm);

    void update();

    void restoreScreen();
}
