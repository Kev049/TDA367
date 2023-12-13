package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

import java.awt.*;

public interface IBasePowerUpHandler {
    void addPiece(Piece p, int index);
    void removePowerUpFromField(PowerUp powerUp);
    void pieceFromBaseToField(Color color);
}
