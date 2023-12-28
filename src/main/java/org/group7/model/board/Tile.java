package org.group7.model.board;

import org.group7.model.entities.piece.Piece;
import org.group7.model.entities.powerups.PowerUp;
import org.group7.model.entities.Entity;

/**
 * The Tile class represents a tile on the board.
 */
public class Tile implements IInsertable {
    private Entity entity;
    private final int index;
    private final int OUT_OF_BOUNDS = -1;

    /**
     * The constructor of the Tile class.
     * 
     * @param index The parameter "index" represents the index of the tile.
     */
    public Tile(int index) {
        this.entity = null;
        this.index = index;
    }

    /**
     * Places a piece on the tile, handling collisions if necessary.
     * 
     * @param p The parameter "p" is of type "Piece", which represents a game piece.
     */
    public void insertPiece(Piece p) {
        if (this.entity != null) {     // if collision
            this.entity.handleCollision(p);
        } else {                    // else
            this.entity = p;
            this.entity.setPos(index);
        }
    }

    /**
     * The function placed a power-up on the tile if the tile doesn't already contain a power-up/piece.
     * 
     * @param powerUp The `powerUp` parameter is the power-up to be inserted.
     */
    public void insertPowerUp(PowerUp powerUp) {
        if (this.entity == null) {
            this.entity = powerUp;
            this.entity.setPos(index);
        }
    }

    /**
     * The function checks if there is no entity on the tile.
     *
     * @return The method returns a boolean value. It returns true if there isn't an entity
     * on this tile (i.e "entity" variable is null).
     */
    public boolean isEmpty() {
        return this.entity == null;
    }

    /**
     * The function returns the entity on the tile.
     * 
     * @return The method is returning an object of type Entity.
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * The function sets the position of the entity to out of bounds and then sets the
     * entity variable to null.
     */
    public void removeEntity() {
        this.entity.setPos(OUT_OF_BOUNDS);
        this.entity = null;
    }

    /**
     * The function returns the index of the tile.
     * 
     * @return The method is returning an int representing the variable "index".
     */
    public int getIndex() {
        return this.index;
    }



}
