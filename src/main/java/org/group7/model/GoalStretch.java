package org.group7.model;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GoalStretch {

    private Tile[] tiles = new Tile[4];
    private Color color;

    public GoalStretch(Color color) {
        this.color = color;
        initTiles();
    }

    private void initTiles(){
        for (int i = 0; i < 4; i++) {
            this.tiles[i] = new Tile(i);
        }
    }

    public void addPiece(Piece p, int index) {
        this.tiles[index].insertEntity(p);
    }

    public void removePiece(int index){ //har ändrat removeEntity så har kanske pajat denna, removeEntity returnade en entity innan
        this.tiles[index].removeEntity();
    }

    public Color getColor(){
        return this.color;
    }

    public Tile[] getTiles(){
        return this.tiles;
    }

}
