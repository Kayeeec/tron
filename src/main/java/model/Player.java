package model;

import enums.Direction;

import java.awt.*;

public abstract class Player {
    private final Color color;
    private Direction currentDirection;

    public Player(Color color, Direction currentDirection) {
        this.color = color;
        this.currentDirection = currentDirection;
    }

    public Color getColor() {

        return color;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
