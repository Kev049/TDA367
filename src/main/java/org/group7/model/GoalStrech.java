package org.group7.model;

import java.awt.*;

public class GoalStrech {

    private Tile[] tiles;

    public GoalStrech() {
        for (int i = 0; i < 5; i++) {
            this.tiles[i] = new Tile();
        }
    }

    public void addPiece(Piece p) {
        if (this.pieceAmount < this.capacity) {
            this.pieces[this.pieceAmount++] = p;
        }
    }

    public Piece[] getPieces(){
        return this.pieces;
    }

    public Color getColor(){
        return this.color;
    }
}
