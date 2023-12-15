package org.group7.model.entities.powerups.handlers;

import org.group7.model.entities.piece.Piece;
import org.group7.model.entities.powerups.PowerUp;

import java.awt.*;

public interface IBasePowerUpHandler {
    /**
     * Inserts a given piece at a specified index in the field.
     * 
     * @param p The parameter "p" is of type Piece, which represents a game piece that can be added to
     * the field.
     * @param index The index parameter represents the position in the field array where the piece
     * should be added.
     */
    void addPiece(Piece p, int index);

    /**
     * Removes a power-up entity from a specific position on the field.
     * 
     * @param powerUp The powerUp parameter is an instance of the PowerUp class.
     */
    void removePowerUpFromField(PowerUp powerUp);

    /**
     * The function moves a piece from a specific base (based on the color) to the field
     * using the int i (which should be 1, or customisable based on the dice roll).
     * 
     * @param color The color of the player's pieces (e.g., Color.RED or Color.BLUE).
     * @param i Represents the number rolled on a dice or can be hard-coded to 1. It is used to
     * determine the destination field for the piece being moved from the base.
     */    
    void pieceFromBaseToField(Color color, int i);
}
