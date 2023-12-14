package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

public interface ILightningPowerUpHandler {
    void addPiece(Piece p, int index);
    void removePowerUpFromField(PowerUp powerUp);
    void movePiece(Piece piece, int diceRoll);
}
