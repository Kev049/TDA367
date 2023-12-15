package org.group7.model.board;

import org.group7.model.entities.piece.Piece;

/**
 * The PieceExtractor interface represents an object that can extract a piece from the goal stretch
 * and move it back to the field.
 */
public interface PieceExtractor {

    /**
     * The function takes a piece and moves it from the goal stretch to the field, updating its
     * position and state.
     * 
     * @param p The parameter "p" is of type "Piece".
     */
    void pieceFromGoalStretchToField(Piece p);
}
