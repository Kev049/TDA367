package org.group7.model.board.entities.powerups.handlers;

import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.PowerUp;

public interface ILightningPowerUpHandler {
    void addPiece(Piece p, int index);
    void removePowerUpFromField(PowerUp powerUp);
    void movePiece(Piece piece, int diceRoll);
}
