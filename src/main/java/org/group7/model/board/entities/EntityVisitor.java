package org.group7.model.board.entities;

import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.PowerUp;

public interface EntityVisitor {

    /**
     * The visit function takes in a power-up as a parameter and removes it from the field.
     * @param powerUp The parameter "powerUp" is of type "PowerUp".
     */
    void visit(PowerUp powerUp);

    /**
     * The visit function takes in a piece as a parameter and returns it to the base.
     * @param piece The parameter "piece" is of type "Piece".
     */
    void visit(Piece piece);
}
