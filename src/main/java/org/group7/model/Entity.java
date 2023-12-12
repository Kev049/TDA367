package org.group7.model;

public abstract class Entity{ //TODO: Ta bort denna klassen
    private int pos;

    public Entity(int pos){
        this.pos = pos;
    }
    // Plan to extract some functionality from pieces and place here

    public int getPos(){
        return this.pos;
    }

    public void setPos(int newPos) { this.pos = newPos; }

    //public void accept(EntityVisitor visitor) {
    //   visitor.visit(this);
    //}

}
