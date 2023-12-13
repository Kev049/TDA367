package org.group7.model.PowerUps;

import org.group7.model.IBasePowerUpHandler;
import org.group7.model.IPowerUpHandler;
import org.group7.model.Piece;

public class BasePowerUp extends PowerUp {
    IBasePowerUpHandler handler;
    public BasePowerUp(IBasePowerUpHandler handler){
        super("Base");
        this.handler = handler;
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
