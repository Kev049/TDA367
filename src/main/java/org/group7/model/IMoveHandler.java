package org.group7.model;

import java.awt.*;

public interface IMoveHandler { //Borde kanske heta CollisionHandler trots allt?
    //public void movePiece(Piece piece, int offset);
    void addPieceToField(Piece p, int index);
    void returnPieceToBase(Piece piece);
    void removePowerUpFromField(PowerUp powerUp);
    void removeFromField(int pos);
    void pieceFromBaseToField(Color color);
    void yeetPieceFromGoal(Piece p); //TODO move to seperate handler
}
