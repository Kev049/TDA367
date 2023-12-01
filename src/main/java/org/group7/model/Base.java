package org.group7.model;

import java.awt.*;

public class Base {

    private int capacity;       //Går att ändra om man vill byta antal pjäser (istället för standard som är 4)
    private int pieceAmount;
    private Piece[] pieces;
    private Tile[] tiles;
    private Color color;

    public Base(int capacity, Color color) {
        this.capacity = capacity;
        this.pieceAmount = capacity;
        this.pieces = new Piece[capacity];
        this.color = color;
        this.tiles = new Tile[4];

        for (int i = 0; i < 4; i++) {
            this.tiles[i] = new Tile(i);
        }
        /*
        for (int i = 0; i < capacity; i++) {
            //this.pieces[capacity] = new Piece(this.colour); //TODO implement Player
            this.pieces[capacity] = PieceFactory.createPiece(this.colour);
        }
         */
    }

    public Piece removePiece() {
        if (this.pieceAmount > 0) {
            Piece p = this.pieces[--this.pieceAmount];
            this.pieces[this.pieceAmount] = new Piece(color);
            return p;
        }
        return null;
    }

    public void addPiece(Piece p) {
        for (Tile t : this.tiles){ //TODO Change to while loop, tempfix
            if (t.getEntity() == null){
                t.insertEntity(p);
                break;
            }
        }
    }

    public Piece[] getPieces(){
        return this.pieces;
    }

    public Color getColor(){
        return this.color;
    }

    public Tile[] getTiles(){return this.tiles;}
}
