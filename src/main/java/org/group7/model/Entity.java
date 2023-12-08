package org.group7.model;

import java.awt.Color;
//deprecated
public abstract class Entity{
private int pos;

    public Entity(int pos){
        this.pos = pos;
    }
    // Plan to extract some functionality from pieces and place here

    public int getPos(){
        return this.pos;
    }

    public void setPos(int newPos) { this.pos = pos; }

    public abstract void handleCollision(Piece p); //TODO kolla om detta är ok, ska det vara Piece?


}
