package org.group7.model.entities;

import org.group7.model.entities.piece.Piece;
import org.group7.model.entities.powerups.PowerUp;
import org.group7.model.entities.powerups.handlers.ILaserPowerUpHandler;

public class RemoveFromFieldVisitor implements EntityVisitor {
    private final ILaserPowerUpHandler handler;

    /**
     * The constructor of the RemoveFromFieldVisitor class takes in a power-up handler as a parameter.
     * @param handler The parameter "handler" is of type "ILaserPowerUpHandler".
     */
    public RemoveFromFieldVisitor(ILaserPowerUpHandler handler) {
        this.handler = handler;
    }

    /**
     * The visit function takes in a piece as a parameter and returns it to the base.
     * @param piece The parameter "piece" is of type "Piece".
     */
    public void visit(Piece piece) {
        this.handler.returnPieceToBase(piece);
    }

    /**
     * The visit function takes in a power-up as a parameter and removes it from the field.
     * @param powerUp The parameter "powerUp" is of type "PowerUp".
     */
    public void visit(PowerUp powerUp) {
        this.handler.removePowerUpFromField(powerUp);
    }
}
