package org.group7.model;

public class BasePowerUp extends PowerUp{
    public BasePowerUp(IMoveHandler handler){
        super(handler, "Base");
    }

    @Override
    public void handleCollision(Piece piece) {
        pieceFromBaseToField(piece);
    }

    public void pieceFromBaseToField(Piece piece){
        this.handler.pieceFromBaseToField(piece.getColor());
    }
}
