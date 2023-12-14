package org.group7.model.board.entities;

import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.PowerUp;

public interface EntityVisitor {
    void visit(PowerUp powerUp);

    void visit(Piece piece);
}
