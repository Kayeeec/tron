package controller;

import enums.Direction;
import model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventHandler extends PlayerControlHandler implements KeyListener {
    private int up;
    private int down;
    private int left;
    private int right;

    public KeyEventHandler(Player player, int up, int down, int left, int right) {
        super(player);
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("key pressed "+keyCode);
        Direction currentDirection = player.getCurrentDirection();

        if (keyCode == up) {
            if (currentDirection != Direction.UP.getOpposite()) {
                player.setCurrentDirection(Direction.UP);
            }

        } else if (keyCode == down) {
            if (currentDirection != Direction.DOWN.getOpposite()) {
                player.setCurrentDirection(Direction.DOWN);
            }

        } else if (keyCode == left) {
            if (currentDirection != Direction.LEFT.getOpposite()) {
                player.setCurrentDirection(Direction.LEFT);
            }

        } else if (keyCode == right) {
            if (currentDirection != Direction.RIGHT.getOpposite()) {
                player.setCurrentDirection(Direction.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
