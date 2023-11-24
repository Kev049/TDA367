package org.group7.model;


import java.awt.*;

public class Board {

    private Base[] bases;
    private Tile[] field;
    private Tile[][] goal;
    private int[] playerStartTiles;

    public Board() {
        this.bases = new Base[4];
        this.field = new Tile[40];         // Egen klass för mindre krångel?
        this.goal = new Tile[4][4];       // tycker att detta kanske borde vara en egen klass så att den inte ärver onödiga funktione
        this.playerStartTiles = new int[] {0, 10, 20, 30};
        this.bases[0] = new Base(4, Color.RED);
        this.bases[1] = new Base(4, Color.GREEN);
        this.bases[2] = new Base(4, Color.YELLOW);
        this.bases[3] = new Base(4, Color.BLUE);
        for (int i = 0; i < 40; i++) {
            this.field[i] = new Tile(i);
        }
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

    public void movePiece(int from, int to) {
        Entity e = this.field[from].removeEntity();
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
