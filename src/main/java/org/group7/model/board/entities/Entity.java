package org.group7.model.board.entities;

import org.group7.model.board.entities.piece.Piece;

/**
 * The Entity class is an abstract class that acts as the parent class for all
 * entities on the board (pieces and power-ups).
 */
public abstract class Entity {
    private int pos;

    /**
     * The accept function takes in an EntityVisitor as a parameter and calls the visit function of the visitor.
     * 
     * @param visitor The parameter "visitor" is of type "EntityVisitor".
     */
    public abstract void accept(EntityVisitor visitor);

    /**
     * The handleCollision function takes in the colliding piece as a parameter
     * and should appropritely handle the collision depending on its type which can be
     * - Two pieces of the same color.
     * - Two pieces of different colors.
     * - A piece and a power-up.
     * 
     * @param p The parameter "p" is of type "Piece".
     */
    public abstract void handleCollision(Piece p);

    /**
     * The getPos function returns the position of the entity.
     * 
     * @return The return value is of type "int".
     */
    public int getPos() {
        return pos;
    }

    /**
     * The setPos function takes in a position as a parameter and sets the position
     * of the entity.
     * 
     * @param pos The parameter "pos" is of type "int".
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

}
