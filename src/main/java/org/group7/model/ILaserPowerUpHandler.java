package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

public interface ILaserPowerUpHandler{
    void addPiece(Piece p, int index);
    void removePowerUpFromField(PowerUp powerUp);
    void removeFromField(int pos);
    void returnPieceToBase(Piece piece);
}
