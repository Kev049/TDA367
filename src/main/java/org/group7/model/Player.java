package org.group7.model;

import java.awt.Color;
import java.util.List;

public class Player{
    private List<Piece> pieces; //TODO tas bort, ifrågasätt Player-klasses existens?
    private final Color color;

    public Player(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public List<Piece> getPieces(){
        return this.pieces;
    } //TODO onödig

    public Piece choosePiece(){ //väljer automatiskt piece 0,
        return pieces.getFirst();
    } //TODO onödig

}
