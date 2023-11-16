package org.group7.model;

import java.util.ArrayList;
import java.util.List;

public class Player{
    private List<Piece> pieces;
    private String colour;

    public Player(String colour){
        this.colour = colour;
        initPieces();
    }
    public void initPieces(){
        this.pieces = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(colour));      //Kan kanske göras finare
        }
    }

    public String getColour(){
        return this.colour;
    }

    public List<Piece> getPieces(){
        return this.pieces;
    }

    public Piece choosePiece(){ //väljer automatiskt piece 0,
        return pieces.get(0);
    }

}
