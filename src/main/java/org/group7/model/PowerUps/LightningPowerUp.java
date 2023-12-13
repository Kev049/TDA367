package org.group7.model.PowerUps;

import org.group7.model.IBasePowerUpHandler;
import org.group7.model.ILightningPowerUpHandler;
import org.group7.model.IPowerUpHandler;
import org.group7.model.Piece;

public class LightningPowerUp extends PowerUp {

    ILightningPowerUpHandler handler;
    public LightningPowerUp(ILightningPowerUpHandler handler) {
        super("Lightning");
        this.handler = handler;

    }

    @Override
    public void handleCollision(Piece piece) {
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPiece(piece, pos + 2);
    }
}
