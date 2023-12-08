package org.group7.model;

public class BasePowerUp extends PowerUp{
    public BasePowerUp(IMoveHandler handler){
        super(handler);
    }

    @Override
    public void handleCollision(Piece piece) {
        extractBasePiece(piece);
    }

    public void extractBasePiece(Piece piece){
        this.handler.extractPieceFromBase(piece.getColor());
    }
}
