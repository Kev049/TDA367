package org.group7.model;

public class RemoveEntityVisitor implements EntityVisitor{
    IMoveHandler handler;
    public RemoveEntityVisitor(IMoveHandler handler){
        this.handler = handler;
    }
    public void visit(Piece piece){
        this.handler.returnPieceToBase(piece);
    }
    public void visit(PowerUp powerUp){
        this.handler.removePowerUpFromField(powerUp);
    }
}
