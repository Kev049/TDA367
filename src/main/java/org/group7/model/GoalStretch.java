package org.group7.model;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GoalStretch {

    private final int capacity = 4;
    private Tile[] tiles = new Tile[capacity];
    private Color color;
    private int finishedPieces;

    public GoalStretch(Color color) {
        this.color = color;
        initTiles();
        this.finishedPieces = 0;
    }

    private void initTiles(){
        for (int i = 0; i < capacity; i++) {
            this.tiles[i] = new Tile(i);
        }
    }

    public void addPiece(Piece p) {
        this.tiles[finishedPieces].insertPiece(p); //TODO change to actual index not dummy checker
        this.finishedPieces += 1;
        checkIfFull(); //TODO use observer pattern here to tell Game that a player has won
    }

    public void removePiece(int index){ //har ändrat removeEntity så har kanske pajat denna, removeEntity returnade en entity innan
        this.tiles[index].removePiece();
    }

    public Color getColor(){
        return this.color;
    }

    public Tile[] getTiles(){
        return this.tiles;
    }

    private Boolean checkIfFull(){

        return finishedPieces == capacity;
    }

}
