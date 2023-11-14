package org.group7.model;

import java.util.List;
//import java.awt.Color; //Vi använder string sålänge

public class Player {

    private String colour;
    private List<Piece> pieces;

    public Player (String colour){
        this.colour = colour;
        pieces.add(new Piece(colour));      //Kan kanske göras finare på 1-2 rader
        pieces.add(new Piece(colour));
        pieces.add(new Piece(colour));
        pieces.add(new Piece(colour));
    }

public String get_colour(){
    return this.colour;
}

public List<Piece> get_pieces(){
    return this.pieces;
}

}
