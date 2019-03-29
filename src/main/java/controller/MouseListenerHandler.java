package controller;

import enums.Direction;
import model.Mouse;
import model.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MouseListenerHandler implements MouseListener {

	private List<Player> players;

	private static Map<Direction, Direction> left = new HashMap<>();
	private static Map<Direction, Direction> right = new HashMap<>();

	public MouseListenerHandler(List<Player> players) {
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

	private List<Player> getPlayersForEvent(MouseEvent e) {
		ArrayList<Player> result = new ArrayList<>();
		for (Player player: this.players) {
			if(player.controlledByEvent(e)){
				result.add(player);
			}
		}
		return result;
	}

	public void mouseClicked(MouseEvent e) {
		List<Player> players = getPlayersForEvent(e);
		for (Player player : players){
			Mouse controls = (Mouse) player.getControls();
			Direction currentDirection = player.getCurrentDirection();
			if (e.equals(controls.getLeft())){ //left
				Direction nextDirection = left.get(currentDirection);
				player.setCurrentDirection(nextDirection);
				return;
			} else if (e.equals(controls.getRight())) {
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
