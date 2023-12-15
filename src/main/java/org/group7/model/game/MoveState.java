package org.group7.model.game;

import org.group7.model.board.entities.piece.Piece;

import java.awt.*;

/*
 * This class is a subclass of GameState.
 * It is used to represent the state of the game when the player can move a piece.
 */
public class MoveState extends GameState {

    /*
     * Constructor for MoveState
     * @param g the game object
     */
    public MoveState(Game g) {
        super(g);
    }

    /*
     * The method is used to move a piece.
     * @param piece the piece to be moved
     */
    @Override
    public void move(Piece piece) {
        game.movePiece(piece);
    }

    /*
     * The method is used to move a piece from the base to the field.
     * @param color the color of the piece to be moved
     */
    @Override
    public void pieceFromBaseToField(Color color) {
        game.movePieceOutOfBase(color);
    }


    /*
     * This method should not be accessible in this state
     */
    @Override
    public void roll() {

    }

    /*
     * This method changes the state of the game to RollState
     */
    @Override
    public void nextState(Game game) {
        game.setState(new RollState(game));
    }
}
