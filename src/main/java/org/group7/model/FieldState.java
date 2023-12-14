package org.group7.model;

public class FieldState implements PieceState {

    private final Piece piece;

    public FieldState(Piece p) {
        this.piece = p;
    }

    public void nextState() {
        this.piece.setState(new GoalState(piece));
    }

    public void handleCollision(Piece p, IMoveHandler handler) {
        if (piece.getColor().equals(p.getColor())) {
            // Same Color, skip one tile
            handler.addPiece(p, (piece.getPos() + 1) % 40);// om en pjäs står på plats 40 och en annan pjäs
            // hamnar där så försöker den omedelbart att inserta på nästa plats.
            // då blir det index out of bounds error för att den försöker sätta pjäsen på index 40 istället för 39.

        } else {
            // Different color, send other to base and take its place
            int position = piece.getPos();
            handler.returnPieceToBase(piece);
            handler.addPiece(p, position);
        }
    }
}
