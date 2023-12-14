package org.group7.model;

public interface PieceState {
    void handleCollision(Piece p, IMoveHandler handler);
    void nextState();
}
