package org.group7.model;

public class GoalState implements PieceState {
    private Piece piece;

    public GoalState(Piece p) {
        this.piece = p;
    }
    public void handleCollision(Piece p, IMoveHandler handler) {
        handler.addPiece(p, piece.getPos() + 1);
    }
}
