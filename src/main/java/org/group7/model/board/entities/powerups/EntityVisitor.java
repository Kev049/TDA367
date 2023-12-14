package org.group7.model.board.entities.powerups;

import org.group7.model.board.entities.piece.Piece;

public interface EntityVisitor {
    void visit(PowerUp powerUp);

    void visit(Piece piece);
}
