package org.group7.model.PowerUps;

import org.group7.model.*;

public abstract class PowerUp extends Entity{
    private int pos;
    protected IPowerUpHandler handler;  //TODO PowerUpHandler är för stor
    private final String powerUpName;

    public PowerUp(IPowerUpHandler handler, String powerUpName) {
        this.handler = handler;
        this.powerUpName = powerUpName;
    }

    @Override
    public void accept(EntityVisitor visitor){
        visitor.visit(this);
    }
    @Override
    public abstract void handleCollision(Piece piece);

    public String getPowerUpName(){
        return powerUpName;
    }
}

