package org.group7.model.entities.piece;

import org.group7.model.board.IMoveHandler;

/**
 * The GoalState class represents the state of a piece when it has reached the goal stretch.
 * It implements the PieceState interface.
 * 
 * @see PieceState
 */
public class GoalState implements PieceState {
    private final Piece piece;

    /**
     * The constructor for the GoalState class. It takes in a piece as a parameter.
     * 
     * @param p The parameter "p" is of type "Piece". It represents the piece that has collided with
     * the piece in the GoalState.
     */
    public GoalState(Piece p) {
        this.piece = p;
    }

    /**
     * The nextState function changes the state of the piece to FieldState.
     */
    @Override
    public void nextState() {
        this.piece.setState(new FieldState(piece));
    }

    /**
     * The handleCollision function calls the addPiece method of the handler object,
     * passing in the piece's position + 1 modulo the number of outer tiles.
     * @param p The parameter "p" is of type "Piece". It represents the piece that
     * the collision has occured with.
     * @param handler The parameter "handler" is of type "IMoveHandler". 
     */
    @Override
    public void handleCollision(Piece p, IMoveHandler handler) {
        handler.addPiece(p, (piece.getPos() + 1) % 40);
    }   //TODO functionality to insert a piece in the correct direction, -1 for a "bounce"
}
