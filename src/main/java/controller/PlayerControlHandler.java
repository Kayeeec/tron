package controller;

import model.Player;

public abstract class PlayerControlHandler {

    protected Player player;

    public PlayerControlHandler(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
