package org.group7.model.entities.powerups.handlers;

import org.group7.model.entities.piece.Piece;
import org.group7.model.entities.powerups.PowerUp;

public interface ILaserPowerUpHandler {
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
     * The function removes entities from a field starting from a given position and iterates through
     * the field in a circular manner for 8 tiles.
     * 
     * @param pos The `pos` parameter represents the starting position from where entities will be
     * removed.
     */
    void removeEntitiesFromField(int pos);

    /**
     * The function removes a piece from its tile and adds it to the corresponding
     * base based on its color.
     * 
     * @param piece Is of type Piece, which represents a game piece.
     */
    void returnPieceToBase(Piece piece);
}
