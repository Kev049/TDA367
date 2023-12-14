package org.group7.model.board.entities.powerups;

import org.group7.model.board.entities.EntityVisitor;
import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.Entity;

public abstract class PowerUp extends Entity {

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

