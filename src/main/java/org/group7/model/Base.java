package org.group7.model;

import java.awt.*;

public class Base {

    private int capacity;       //Går att ändra om man vill byta antal pjäser (istället för standard som är 4)
    private int pieceAmount;
    private Piece[] pieces;
    private Color color;

    public Base(int capacity, Color color) {
        this.capacity = capacity;
        this.pieceAmount = capacity;
        this.pieces = new Piece[capacity];
        this.color = color;
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
            this.pieces[this.pieceAmount] = null;
            return p;
        }
        return null;
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
