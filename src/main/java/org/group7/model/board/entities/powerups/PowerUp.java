package org.group7.model.board.entities.powerups;

import org.group7.model.board.entities.EntityVisitor;
import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.Entity;

/**
 * The PowerUp class is an abstract class that extends the Entity class.
 * It acts as the parent class for all the power-ups.
 */
public abstract class PowerUp extends Entity {

    private final String powerUpName;

    /**
     * The PowerUp constructor takes in the power-up name as a parameter.
     * @param powerUpName The parameter "powerUpName" is of type "String".
     */
    public PowerUp(String powerUpName) {
        this.powerUpName = powerUpName;
    }

    /**
     * The accept function takes in an EntityVisitor as a parameter and calls the visit function of the visitor.
     * @param visitor The parameter "visitor" is of type "EntityVisitor".
     */
    @Override
    public void accept(EntityVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * The handleCollision function takes in the colliding piece as a parameter
     * and the implementation should use the handler to "execute" the power-up.
     * @param piece The parameter "piece" is of type "Piece".
     */
    @Override
    public abstract void handleCollision(Piece piece);

    /**
     * The getPowerUpName function returns the name of the power-up.
     * @return The return value is of type "String".
     */
    public String getPowerUpName(){
        return powerUpName;
    }
}

