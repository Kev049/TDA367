package org.group7.model.entities.powerups;

import org.group7.model.entities.powerups.handlers.ILaserPowerUpHandler;
import org.group7.model.entities.piece.Piece;

/**
 * The LaserPowerUp class is a specific power-up class, 
 * which extends the PowerUp class, that implements the functiontality
 * required to have a "laser" power-up.
 */
public class LaserPowerUp extends PowerUp {

    ILaserPowerUpHandler handler;

    /**
     * The BasePowerUp constructor takes an ILaserPowerUpHandler as a parameter.
     * @param handler The parameter "handler" is of type "ILaserPowerUpHandler".
     */
    public LaserPowerUp(ILaserPowerUpHandler handler) {
        super("Laser");
        this.handler = handler;
    }

    /**
     * The handleCollision function takes in the colliding piece as a parameter
     * and uses the handler to "execute" the effects of the laser power-up.
     * @param piece The parameter "piece" is of type "Piece".
     */
    @Override
    public void handleCollision(Piece piece) {
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPiece(piece, pos);
        this.handler.removeEntitiesFromField(pos);
    }
}

