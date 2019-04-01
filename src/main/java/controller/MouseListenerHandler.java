package controller;

import enums.Direction;
import model.Mouse;
import model.TronPlayer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MouseListenerHandler implements MouseListener {

	private final List<TronPlayer> players;

	public MouseListenerHandler(List<TronPlayer> players) {
		this.players = players;
	}

	private List<TronPlayer> getPlayersForEvent(MouseEvent e) {
		ArrayList<TronPlayer> result = new ArrayList<TronPlayer>();
		for (TronPlayer player: this.players) {
			if(player.controlledByEvent(e)){
				result.add(player);
			}
		}
		return result;
	}

	public void mouseClicked(MouseEvent e) {
		List<TronPlayer> players = getPlayersForEvent(e);
		for (TronPlayer player : players){
			Mouse controls = (Mouse) player.getControls();
			Direction currentDirection = player.getCurrentDirection();
            int button = e.getButton();
            if (button == controls.getLeft()){ //left
				Direction nextDirection = currentDirection.getLeft();
				player.setCurrentDirection(nextDirection);
				return;
			} else if (button == controls.getRight()) {
				Direction nextDirection = currentDirection.getRight();
				player.setCurrentDirection(nextDirection);
				return;
			}
		}
	}


	public void mousePressed(MouseEvent e) {}


	public void mouseReleased(MouseEvent e) {}


	public void mouseEntered(MouseEvent e) {}


	public void mouseExited(MouseEvent e) {}

}
