package org.group7.model;

public class FieldState implements PieceState {

    private Piece piece;

    public FieldState(Piece p) {
        this.piece = p;
    }
    public void handleCollision(Piece p, IMoveHandler handler) {
        if (piece.getColor().equals(p.getColor())) {
            // Same Color, skip one tile
            handler.addPiece(p, piece.getPos() + 1);
        } else {
            // Different color, send other to base and take its place
            int position = piece.getPos();
            handler.returnPieceToBase(piece);
            handler.addPiece(p, position);
        }
    }
}