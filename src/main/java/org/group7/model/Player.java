package org.group7.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player{
    private List<Piece> pieces;
    private Color color;

    public Player(Color color, Piece[] pieces){
        this.color = color;
        initPieces();
    }
    public void initPieces(){
        this.pieces = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(color));      //Kan kanske göras finare
        }
    }

    public Color getColor(){
        return this.color;
    }

    public List<Piece> getPieces(){
        return this.pieces;
    }

    public Piece choosePiece(){ //väljer automatiskt piece 0,
        return pieces.get(0);
    }

}
