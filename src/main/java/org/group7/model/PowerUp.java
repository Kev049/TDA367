package org.group7.model;
S
public abstract class PowerUp implements IEntity { //borde väl vara en abstrakt klass?
    private int pos;
    protected IMoveHandler handler;

    public PowerUp(IMoveHandler handler) {
        this.handler = handler;
    }

    public abstract void handleCollision(Piece piece);

    public int getPos() {
        return pos;
    }

    public void setPos(int pos){
        this.pos = pos;
    }
}

