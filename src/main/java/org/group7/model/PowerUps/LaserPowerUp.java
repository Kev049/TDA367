package org.group7.model.PowerUps;

import org.group7.model.ILaserPowerUpHandler;
import org.group7.model.Piece;

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

