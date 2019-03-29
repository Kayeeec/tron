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

	private static final Map<Direction, Direction> left = new HashMap<Direction, Direction>();
	private static final Map<Direction, Direction> right = new HashMap<Direction, Direction>();

	public MouseListenerHandler(List<TronPlayer> players) {
		this.players = players;

		left.put(Direction.DOWN, Direction.RIGHT);
		left.put(Direction.UP, Direction.LEFT);
		left.put(Direction.LEFT, Direction.DOWN);
		left.put(Direction.RIGHT, Direction.UP);

		right.put(Direction.DOWN, Direction.LEFT);
		right.put(Direction.UP, Direction.RIGHT);
		right.put(Direction.LEFT, Direction.UP);
		right.put(Direction.RIGHT, Direction.DOWN);

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
				Direction nextDirection = left.get(currentDirection);
				player.setCurrentDirection(nextDirection);
				return;
			} else if (button == controls.getRight()) {
				Direction nextDirection = right.get(currentDirection);
				player.setCurrentDirection(nextDirection);
				return;
			}
		}
	}



	public void mousePressed(MouseEvent e) {

		// TODO Auto-generated method stub

	}


	public void mouseReleased(MouseEvent e) {

		// TODO Auto-generated method stub

	}


	public void mouseEntered(MouseEvent e) {

		// TODO Auto-generated method stub

	}


	public void mouseExited(MouseEvent e) {

		// TODO Auto-generated method stub

	}

}
