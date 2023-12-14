package org.group7.model.board.entities;

import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.PowerUp;
import org.group7.model.board.entities.powerups.handlers.ILaserPowerUpHandler;
import org.group7.model.board.entities.powerups.EntityVisitor;

public class RemoveEntityVisitor implements EntityVisitor {
    private final ILaserPowerUpHandler handler;

    public RemoveEntityVisitor(ILaserPowerUpHandler handler) {
        this.handler = handler;
    }

    public void visit(Piece piece) {
        this.handler.returnPieceToBase(piece);
    }

    public void visit(PowerUp powerUp) {
        this.handler.removePowerUpFromField(powerUp);
    }
}
