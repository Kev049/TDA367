package org.group7.model;

import java.awt.Color;

public class Tile implements IInsertable {

    private final int index;
    private Piece piece;

    public Tile(int index) {
        this.piece = null;
        this.index = index;
    }

    public Piece getPiece() {
        return piece;
    }

    public void insertPiece(Piece p) {
        if(this.piece != null){     // if collision
            this.piece.handleCollision(p);
        } else {                    // else
            this.piece = p;
            this.piece.setPos(index);
        }
    }

    /* TODO när vi implementerat powerups
    public void insertEntity(PowerUp p) {
        if (this.entity != null){
            //this.entity.handleCollision(p);
        }
        this.entity = p;
    }*/

    public void removePiece() {
        this.piece.setPos(-1);
        this.piece = null;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public Color getPieceColor() {
        return this.piece.getColor();
    }
}
