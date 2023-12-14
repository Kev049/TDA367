package org.group7.model.board.entities.piece;

import org.group7.model.board.IMoveHandler;

public interface PieceState {
    void handleCollision(Piece p, IMoveHandler handler);
    void nextState();
}
