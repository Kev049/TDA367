package org.group7.model.entities.piece;

import org.group7.model.board.IMoveHandler;

/**
 * The FieldState class represents the state of a piece when it is on the field.
 * It implements the PieceState interface.
 * 
 * @see PieceState
 */
public class FieldState implements PieceState {

    private final Piece piece;

    /**
     * The constructor for the FieldState class. It takes in a piece as a parameter.
     * 
     * @param p The parameter "p" is of type "Piece".
     */
    public FieldState(Piece p) {
        this.piece = p;
    }

    /**
     * The nextState function changes the state of the piece to GoalState.
     */
    public void nextState() {
        this.piece.setState(new GoalState(piece));
    }

    /**
     * The handleCollision function calls the addPiece method of the handler object,
     * handlnig collisions with both pieces of the same color and pieces of different colors.
     * @param p The parameter "p" is of type "Piece". It represents the piece that
     * the collision has occured with.
     * @param handler The parameter "handler" is of type "IMoveHandler". 
     */
    public void handleCollision(Piece p, IMoveHandler handler) {
        if (piece.getColor().equals(p.getColor())) { // Same Color, skip one tile
            handler.addPiece(p, (piece.getPos() + 1) % 40);

        } else { // Different color, send other to its base and take its place
            int position = piece.getPos();
            handler.returnPieceToBase(piece);
            handler.addPiece(p, position);
        }
    }
}
