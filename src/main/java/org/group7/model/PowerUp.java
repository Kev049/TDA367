package org.group7.model;

public abstract class PowerUp implements IEntity { //borde väl vara en abstrakt klass?
    private int pos;
    protected IMoveHandler handler;
    private final String powerUpName;

    public PowerUp(IMoveHandler handler, String powerUpName) {
        this.handler = handler;
        this.powerUpName = powerUpName;
    }

    public abstract void handleCollision(Piece piece);

    public int getPos() {
        return pos;
    }
    public String getPowerUpName(){
        return powerUpName;
    }

    public void setPos(int pos){
        this.pos = pos;
    }
}

