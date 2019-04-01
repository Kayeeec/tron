package controller;

import enums.Direction;
import model.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventHandler extends PlayerControlHandler implements MouseListener {
    private int left;
    private int right;

    public MouseEventHandler(Player player, int left, int right) {
        super(player);
        this.left = left;
        this.right = right;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
        if (button == left){
            Direction currentDirection = player.getCurrentDirection();
            player.setCurrentDirection(currentDirection.getLeft());
        }
        else if (button == right){
            Direction currentDirection = player.getCurrentDirection();
            player.setCurrentDirection(currentDirection.getRight());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
