package org.group7.model.board;

import org.group7.model.entities.Entity;
import org.group7.model.entities.piece.Piece;

/**
 * The IInsertable interface represents an object that can insert and remove
 * a piece into/from a tile or goal.
 */
public interface IInsertable {
    /**
     * The insertPiece() function is used to add a piece to a tile or goal.
     */
    void insertPiece(Piece p);

    /**
     * The removeEntity() function is used to remove an entity from a tile or goal.
     */
    void removeEntity();

    Entity getEntity();

    boolean isEmpty();
}
