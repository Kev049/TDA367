package org.group7.model;

public abstract class GameState {
    protected Game game;

    public GameState(Game g) {
        this.game = g;
    }

    public abstract void move(Tile t);

    public abstract void roll();
}
