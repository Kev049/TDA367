package org.group7.model;

public class BasePowerUp extends PowerUp{
    public BasePowerUp(IMoveHandler handler){
        super(handler, "Base");
    }

    @Override
    public void handleCollision(Piece piece) {
        this.handler.activateBasePowerUp(piece);
        this.handler.addPieceToField(piece, this.getPos());
    }

}
