package org.group7.model.board.entities.powerups;

import org.group7.model.board.entities.powerups.handlers.ILightningPowerUpHandler;
import org.group7.model.board.entities.piece.Piece;

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
        this.handler.addPiece(piece, (pos + 2) % 40);
    }
}
