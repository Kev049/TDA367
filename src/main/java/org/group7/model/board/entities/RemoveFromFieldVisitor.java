package org.group7.model.board.entities;

import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.PowerUp;
import org.group7.model.board.entities.powerups.handlers.ILaserPowerUpHandler;

public class RemoveFromFieldVisitor implements EntityVisitor {
    private final ILaserPowerUpHandler handler;

    public RemoveFromFieldVisitor(ILaserPowerUpHandler handler) {
        this.handler = handler;
    }

    public void visit(Piece piece) {
        this.handler.returnPieceToBase(piece);
    }

    public void visit(PowerUp powerUp) {
        this.handler.removePowerUpFromField(powerUp);
    }
}
