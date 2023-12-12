package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

import java.awt.*;

public interface IMoveHandler { //Borde kanske heta CollisionHandler trots allt?
    //public void movePiece(Piece piece, int offset);
    void addPieceToField(Piece p, int index);
    void returnPieceToBase(Piece piece);
    PowerUp removePowerUpFromField(PowerUp powerUp);
    void removeFromField(int pos);
    void pieceFromBaseToField(Color color);
    void yeetPieceFromGoal(Piece p); //TODO move to seperate handler
}
