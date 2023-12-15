package org.group7.model.game;

import org.group7.model.entities.piece.Piece;

import java.awt.Color;

/**
 * The GameState class is the superclass of the different states of the game.
 * It is used to represent the state of the game which can either be RollState or MoveState.
 */
public abstract class GameState {

    protected final Game game;

    /**
     * Constructor for GameState
     * @param g the game object
     */
    public GameState(Game g) {
        this.game = g;
    }

    /*
     * The method is used to move a piece.
     * @param piece the piece to be moved
     */
    public abstract void move(Piece piece);

    /*
     * The method is used to move a piece from the base to the field.
     * @param color the color of the piece to be moved
     */
    public abstract void pieceFromBaseToField(Color c);

    /*
     * The roll method is used to roll the dice.
     */
    public abstract void roll();

    /*
     * This method changes the state of the game to the opposite state.
     */
    public abstract void nextState(Game game);
}
