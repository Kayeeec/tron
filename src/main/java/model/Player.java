package model;

import java.awt.*;
import java.awt.event.InputEvent;

public abstract class Player {
    private final Color color;
    private Controls controls;

    public Player(Color color, Controls controls) {
        this.color = color;
        this.controls = controls;
    }

    public Color getColor() {

        return color;
    }

    public Controls getControls() {
        return controls;
    }

    public void setControls(Controls controls) {
        this.controls = controls;
    }

    public boolean controlledByEvent(InputEvent event){
        return this.controls.hasInputEvent(event);
    }
}
