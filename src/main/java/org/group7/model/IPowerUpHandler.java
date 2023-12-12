package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

import java.awt.*;

public interface IPowerUpHandler {

    void addPiece(Piece p, int index);
    PowerUp removePowerUpFromField(PowerUp powerUp);
    void removeFromField(int pos);
    void returnPieceToBase(Piece piece);
    void pieceFromBaseToField(Color color);


}
