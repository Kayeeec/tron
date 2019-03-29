package model;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Mouse extends Controls {
    private int left;
    private int right;

    public Mouse(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouse mouse = (Mouse) o;
        return left == mouse.left &&
                right == mouse.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public boolean hasInputEvent(InputEvent event) {
        if( event instanceof MouseEvent) {
            int button = ((MouseEvent) event).getButton();
            return button == left || button == right;
        }
        return false;
    }
}
