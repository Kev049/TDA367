package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

import java.awt.*;

public interface IPowerUpHandler {  //TODO för stort interface, klasser ärver oanvänd funktionalitet, förlorar poängen med interfaces

    void addPiece(Piece p, int index);
    void removePowerUpFromField(PowerUp powerUp);
    void removeFromField(int pos);
    void returnPieceToBase(Piece piece);
    void pieceFromBaseToField(Color color);


}
