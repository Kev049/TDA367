package org.group7.model;

public class BasePowerUp extends PowerUp{
    public BasePowerUp(IMoveHandler handler){
        super(handler, "Base");
    }

    @Override
    public void handleCollision(Piece piece){
        activateBasePowerUp(piece);
    }

    private void activateBasePowerUp(Piece piece){
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPieceToField(piece, pos);
        this.handler.pieceFromBaseToField(piece.getColor());
    }

}
