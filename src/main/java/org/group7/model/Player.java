package org.group7.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player{
    private List<Piece> pieces;
    private Color color;

    public Player(Color color){
        this.color = color;
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
