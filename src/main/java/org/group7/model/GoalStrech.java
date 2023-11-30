package org.group7.model;

import java.awt.*;

public class GoalStrech {

    private Tile[] tiles = new Tile[4];
    private Color color;

    public GoalStrech(Color color) {
        this.color = color;
        for (int i = 0; i < 4; i++) {
            this.tiles[i] = new Tile(i);
        }
    }

    public void addPiece(Entity e, int index) {
        this.tiles[index].insertEntity(e);
    }

    public void removePiece(int index){
        this.tiles[index].removeEntity();
    }

    public Color getColor(){
        return this.color;
    }

}
