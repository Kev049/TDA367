package org.group7.model;

import java.util.List;
//import java.awt.Color; //Vi använder string sålänge

public class Player {
    private String colour;
    private List<Piece> pieces;

    public Player(String colour){
        this.colour = colour;
        initPieces();
    }
    public void initPieces(){
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

}
