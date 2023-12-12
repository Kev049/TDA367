package org.group7.model.PowerUps;

import org.group7.model.IPowerUpHandler;
import org.group7.model.Piece;

public class BasePowerUp extends PowerUp {
    public BasePowerUp(IPowerUpHandler handler){
        super(handler, "Base");
    }

    @Override
    public void handleCollision(Piece piece){
        activateBasePowerUp(piece);
    }

    private void activateBasePowerUp(Piece piece){
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPiece(piece, pos);
        this.handler.pieceFromBaseToField(piece.getColor());
    }

}
