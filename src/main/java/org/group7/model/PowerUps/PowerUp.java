package org.group7.model.PowerUps;

import org.group7.model.*;

public abstract class PowerUp implements IEntity { //borde väl vara en abstrakt klass?
    private int pos;
    protected IPowerUpHandler handler;  //TODO PowerUpHandler är för stor
    private final String powerUpName;

    public PowerUp(IPowerUpHandler handler, String powerUpName) {
        this.handler = handler;
        this.powerUpName = powerUpName;
    }
    public void accept(EntityVisitor visitor){
        visitor.visit(this);
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

