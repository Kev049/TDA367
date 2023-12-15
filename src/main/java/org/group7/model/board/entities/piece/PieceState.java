package org.group7.model.board.entities.piece;

import org.group7.model.board.IMoveHandler;

/**
 * The PieceState interface represents whether the piece is on the field
 * or on the piece's goal stretch.
 */
public interface PieceState {

    /**
     * The handleCollision function shold use the handler object to place the piece
     * on the appropriate tile and handle any potential collisions.
     * @param p The parameter "p" is of type "Piece". It represents the piece that
     * the collision has occured with.
     * @param handler The parameter "handler" is of type "IMoveHandler". 
     */
    void handleCollision(Piece p, IMoveHandler handler);
    
    /**
     * The nextState function should change the state of the piece to the opposite state
     * so that the piece is placed either on the field or on the goal-stretch.
     */
    void nextState();
}
