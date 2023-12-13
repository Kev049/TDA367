package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

public class RemoveEntityVisitor implements EntityVisitor{
    private final ILaserPowerUpHandler handler;
    public RemoveEntityVisitor(ILaserPowerUpHandler handler){
        this.handler = handler;
    }
    public void visit(Piece piece){
        this.handler.returnPieceToBase(piece);
    }
    public void visit(PowerUp powerUp){
        this.handler.removePowerUpFromField(powerUp);
    }
}
