package org.group7.model;

public interface IMoveHandler { //Borde kanske heta CollisionHandler trots allt?
    void addPiece(Piece p, int index);

    void returnPieceToBase(Piece piece);

}
