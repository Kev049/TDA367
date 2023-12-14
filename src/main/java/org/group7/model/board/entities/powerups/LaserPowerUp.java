package org.group7.model.board.entities.powerups;

import org.group7.model.board.entities.powerups.handlers.ILaserPowerUpHandler;
import org.group7.model.board.entities.piece.Piece;

public class LaserPowerUp extends PowerUp {

    ILaserPowerUpHandler handler;

    public LaserPowerUp(ILaserPowerUpHandler handler) {
        super("Laser");
        this.handler = handler;
    }

    @Override
    public void handleCollision(Piece piece) {
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPiece(piece, pos);
        this.handler.removeFromField(pos);
    }
}

