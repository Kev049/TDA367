package org.group7.model.PowerUps;

import org.group7.model.*;

public abstract class PowerUp extends Entity{
    private int pos;
    private final String powerUpName;

    public PowerUp(String powerUpName) {
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

