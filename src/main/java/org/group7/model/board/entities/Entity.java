package org.group7.model.board.entities;

import org.group7.model.board.entities.piece.Piece;

public abstract class Entity {
    private int pos;

    public abstract void accept(EntityVisitor visitor);

    public abstract void handleCollision(Piece p);

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

}
