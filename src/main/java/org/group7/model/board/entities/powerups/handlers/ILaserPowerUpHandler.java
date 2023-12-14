package org.group7.model.board.entities.powerups.handlers;

import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.PowerUp;

public interface ILaserPowerUpHandler {
    void addPiece(Piece p, int index);

    void removePowerUpFromField(PowerUp powerUp);

    void removeFromField(int pos);

    void returnPieceToBase(Piece piece);
}
