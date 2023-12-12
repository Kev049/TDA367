package org.group7.model;

public class GoalState implements PieceState {
    private final Piece piece;

    public GoalState(Piece p) {
        this.piece = p;
    }
    public void handleCollision(Piece p, IMoveHandler handler) {
        handler.addPiece(p, piece.getPos() + 1);
    }   //TODO functionality to insert a piece in the correct direction, -1 for a "bounce"
}
