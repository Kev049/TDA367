package org.group7.model;


import java.awt.*;
import java.util.HashMap;

public class Board {

    private Base[] bases;
    private Tile[] field;
    private Tile[][] goal;
    private HashMap<Color,Integer> playerStartTiles;

    public Board() {
        this.bases = new Base[4];
        this.field = new Tile[40];         // Egen klass för mindre krångel?
        this.goal = new Tile[4][4];       // tycker att detta kanske borde vara en egen klass så att den inte ärver onödiga funktione
        this.playerStartTiles = new HashMap<>();
        initBases();
        initStartTileIndices();
        for (int i = 0; i < 40; i++) {
            this.field[i] = new Tile(i);
        }
    }

    private void initBases() {
        this.bases[0] = new Base(4, Color.RED);
        this.bases[1] = new Base(4, Color.GREEN);
        this.bases[2] = new Base(4, Color.YELLOW);
        this.bases[3] = new Base(4, Color.BLUE);
    }

    private void initStartTileIndices() {
        this.playerStartTiles.put(Color.RED,0);
        this.playerStartTiles.put(Color.GREEN,10);
        this.playerStartTiles.put(Color.YELLOW,20);
        this.playerStartTiles.put(Color.BLUE,30);
    }
    /*

    public void addEntityToBase(int playerNr, Entity e){
        this.bases[playerNr].addEntity(e);
     }

    public Entity removeEntityFromBase(int playerNr) {
        return this.bases[playerNr].removeEntity();
    }
    */
    public void addEntityToField(int playerNr, Entity e) {
        Tile t = this.field[this.playerStartTiles[playerNr]];
        t.insertEntity(e);
    }

    public void removeEntityFromField(Entity e) {
        for (int i = 0; i < field.length; i++) {
            if (e == field[i].getEntity()) {
                field[i].removeEntity();
            }
        }
    }

    public void movePiece(int from, int offset, Color color) {
        Entity e = this.field[from].removeEntity();
        Color c =
        for (int i = 0; i < offset; i++){
            if
        }
        // TODO handle collisions
        this.field[to].insertEntity(e);
    }

    public Base[] getBases(){
        return this.bases;
    }

    public Piece[] getPiecesFromBase(Color color){
        for (Base b: this.bases) {
            if (b.getColor() == color)
                    return b.getPieces();
        }
        return null;
    }




}
