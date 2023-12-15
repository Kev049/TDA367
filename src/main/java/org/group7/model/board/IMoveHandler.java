package org.group7.model.board;

import org.group7.model.board.entities.piece.Piece;

public interface IMoveHandler {
    /**
     * The addPiece function adds a piece to a specific index in 
     * either the goal stretch or the board, updating the piece's position.
     * 
     * @param p The parameter "p" is of type Piece, which represents a game piece that can be added to
     * the game board.
     * @param index The `index` parameter represents the position where the `Piece` object `p` should
     * be added.
     */
    void addPiece(Piece p, int index);

    /**
     * The function removes a piece from its tile and adds it to the corresponding
     * base based on its color.
     * 
     * @param piece Is of type Piece, which represents a game piece.
     */
    void returnPieceToBase(Piece piece);

}
