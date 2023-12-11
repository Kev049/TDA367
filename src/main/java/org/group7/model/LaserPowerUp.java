package org.group7.model;

public class LaserPowerUp extends PowerUp{

    public LaserPowerUp(IMoveHandler handler) {
        super(handler, "Laser");
    }

    @Override
    public void handleCollision(Piece piece) {
        int pos = this.getPos();
        this.handler.removePowerUpFromField(this);
        this.handler.addPieceToField(piece, pos);
        this.handler.removeFromField(pos);
    }
}

