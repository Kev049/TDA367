package org.group7.model.PowerUps;

import org.group7.model.IMoveHandler;
import org.group7.model.Piece;

public class LightningPowerUp extends PowerUp {

    public LightningPowerUp(IMoveHandler handler) {
        super(handler, "Lightning");
    }

    @Override
    public void handleCollision(Piece piece) {
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPieceToField(piece, pos + 2);
    }
}
