package org.group7.model.board;

import org.group7.model.board.entities.piece.Piece;

public interface IMoveHandler { //Borde kanske heta CollisionHandler trots allt?
    void addPiece(Piece p, int index);

    void returnPieceToBase(Piece piece);

}
