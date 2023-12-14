package org.group7.model.game;

import org.group7.model.board.entities.piece.Piece;

import java.awt.Color;

public abstract class GameState {

    protected final Game game;

    public GameState(Game g) {
        this.game = g;
    }

    public abstract void move(Piece piece);

    public abstract void pieceFromBaseToField(Color c);

    public abstract void roll();

    public abstract void nextState(Game game);
}
