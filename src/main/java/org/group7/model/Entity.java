package org.group7.model;

public abstract class Entity{
    protected int pos;

    public Entity(int pos){
        this.pos = pos;
    }
    // Plan to extract some functionality from pieces and place here


    public int get_pos(){
        return this.pos;
    }
}
