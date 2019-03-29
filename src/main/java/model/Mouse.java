package model;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Mouse extends Controls {
    private MouseEvent left;
    private MouseEvent right;

    public Mouse(MouseEvent left, MouseEvent right) {
        this.left = left;
        this.right = right;
    }

    public MouseEvent getLeft() {
        return left;
    }

    public void setLeft(MouseEvent left) {
        this.left = left;
    }

    public MouseEvent getRight() {
        return right;
    }

    public void setRight(MouseEvent right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouse mouse = (Mouse) o;
        return left.equals(mouse.left) &&
                right.equals(mouse.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public boolean hasInputEvent(InputEvent event) {
        if( event instanceof MouseEvent) {
            return event.equals(left) || event.equals(right);
        }
        return false;
    }
}
