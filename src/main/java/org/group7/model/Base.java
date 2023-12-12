package org.group7.model;

import java.awt.Color;

public class Base {

    private final int capacity;       //Går att ändra om man vill byta antal pjäser (istället för standard som är 4)
    private int pieceAmount;
    private final Piece[] pieces;
    private final Color color;

    public Base(int capacity, Color color, IMoveHandler handler) {
        this.capacity = capacity;
        this.pieceAmount = capacity;
        this.pieces = new Piece[capacity];
        this.color = color;
        initPieces(handler);
    }

    private void initPieces(IMoveHandler handler) { //Kan nog delas upp till 2 metoder
        for (int i = 0; i < 4; i++) {
            Piece piece = new Piece(this.color, handler);
            this.pieces[i] = piece;
        }
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
        if (this.pieceAmount < capacity) {
            pieces[pieceAmount++] = p;
        }
    }

    public Piece[] getPieces(){
        return this.pieces;
    }

    public boolean isEmpty() {
        return (this.pieceAmount == 0);
    }

    public Color getColor(){
        return this.color;
    }

}
