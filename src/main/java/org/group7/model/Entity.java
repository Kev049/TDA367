package org.group7.model;

public abstract class Entity{
    private int pos;
    public Entity(){

    }
    public abstract void accept(EntityVisitor visitor);
    public abstract void handleCollision(Piece p);
    public int getPos() {
        return pos;
    }

    public void setPos(int pos){
        this.pos = pos;
    }

}
