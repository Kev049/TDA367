package org.group7.model.entities.powerups;

import org.group7.model.entities.powerups.handlers.IBasePowerUpHandler;
import org.group7.model.entities.piece.Piece;

/**
 * The BasePowerUp class is a specific power-up class, 
 * which extends the PowerUp class, that implements the functiontality
 * required to have a "base" power-up.
 */
public class BasePowerUp extends PowerUp {

    IBasePowerUpHandler handler;

    /**
     * The BasePowerUp constructor takes an IBasePowerUpHandler as a parameter.
     * @param handler The parameter "handler" is of type "IBasePowerUpHandler".
     */
    public BasePowerUp(IBasePowerUpHandler handler) {
        super("Base");
        this.handler = handler;
    }

    /**
     * The handleCollision function takes in the colliding piece as a parameter
     * and uses the handler to "execute" the effects of the base power-up.
     * @param piece The parameter "piece" is of type "Piece".
     */
    @Override
    public void handleCollision(Piece piece) {
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPiece(piece, pos);
        this.handler.pieceFromBaseToField(piece.getColor(), 1);
    }
}
