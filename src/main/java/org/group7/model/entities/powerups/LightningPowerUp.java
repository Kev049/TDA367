package org.group7.model.entities.powerups;

import org.group7.model.entities.powerups.handlers.ILightningPowerUpHandler;
import org.group7.model.entities.piece.Piece;

/**
 * The LightningPowerUp class is a specific power-up class, 
 * which extends the PowerUp class, that implements the functiontality
 * required to have a "lightning" power-up.
 */
public class LightningPowerUp extends PowerUp {

    ILightningPowerUpHandler handler;

    /**
     * The BasePowerUp constructor takes an ILightningPowerUpHandler as a parameter.
     * @param handler The parameter "handler" is of type "ILightningPowerUpHandler".
     */
    public LightningPowerUp(ILightningPowerUpHandler handler) {
        super("Lightning");
        this.handler = handler;
    }

    /**
     * The handleCollision function takes in the colliding piece as a parameter
     * and uses the handler to "execute" the effects of the lightning power-up.
     * @param piece The parameter "piece" is of type "Piece".
     */
    @Override
    public void handleCollision(Piece piece) {
        int fieldTileAmount = 40;
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPiece(piece, (pos + 2) % fieldTileAmount);
    }
}
