package org.group7.model.game;

import org.group7.model.board.entities.piece.Piece;

import java.awt.*;

/**
 * The RollState class is a subclass of GameState.
 * It is used to represent the state of the game when the player can roll the dice.
 */
public class RollState extends GameState {

    /*
     * Constructor for RollState
     * @param g the game object
     */
    public RollState(Game g) {
        super(g);
    }

    /*
     * This method should not be accessible in this state
     */
    @Override
    public void move(Piece piece) {

    }

    /*
     * This method should not be accessible in this state
     */
    @Override
    public void pieceFromBaseToField(Color c) {

    }

    /*
     * The roll method is used to roll the dice.
     */
    @Override
    public void roll() {
        game.rollDice();
    }

    /*
     * This method changes the state of the game to MoveState
     */
    @Override
    public void nextState(Game game) {
        game.setState(new MoveState(game));
    }
}
