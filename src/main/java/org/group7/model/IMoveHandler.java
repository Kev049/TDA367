package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

import java.awt.*;

public interface IMoveHandler { //Borde kanske heta CollisionHandler trots allt?
    //public void movePiece(Piece piece, int offset);
    void addPiece(Piece p, int index);
    void returnPieceToBase(Piece piece);

}
