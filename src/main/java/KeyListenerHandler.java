import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class KeyListenerHandler implements KeyListener {

	private List<Player> players;

	public KeyListenerHandler(List<Player> players) {

		this.players = players;
	}

	public void keyPressed(KeyEvent e) {

		for (Player player : players) {
			if (e.getKeyCode() == player.getKeys().getUp()) {
				if (player.getCurrentDirection() != Direction.DOWN) {
					player.setCurrentDirection(Direction.UP);
				}
			}
			else if (e.getKeyCode() == player.getKeys().getDown()) {
				if (player.getCurrentDirection() != Direction.UP) {
					player.setCurrentDirection(Direction.DOWN);
				}
			}
			else if (e.getKeyCode() == player.getKeys().getRight()) {
				if (player.getCurrentDirection() != Direction.LEFT) {
					player.setCurrentDirection(Direction.RIGHT);
				}
			}
			else if (e.getKeyCode() == player.getKeys().getLeft()) {
				if (player.getCurrentDirection() != Direction.RIGHT) {
					player.setCurrentDirection(Direction.LEFT);
				}
			}

		}

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}
