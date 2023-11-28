package org.group7.model;


import java.awt.*;
import java.util.HashMap;

public class Board {

    private Base[] bases;
    private Tile[] field;
    private Tile[][] goals;
    private HashMap<Color,Integer> playerStartTiles;
    private HashMap<Color, Tile[]> goalsHashMap;
    private HashMap<Color,Base> colorBaseMap;

    public Board() {
        this.bases = new Base[4];
        this.field = new Tile[40];
        this.goals = new Tile[4][4];
        this.goalsHashMap = new HashMap<>();       // tycker att detta kanske borde vara en egen klass så att den inte ärver onödiga funktione
        this.playerStartTiles = new HashMap<>();
        this.colorBaseMap = new HashMap<>();
        initBases();
        initStartTileIndices();
        initColorBaseMap();
        initGoals();
        initGoalsHashMap();
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

    private void initColorBaseMap() {
        this.colorBaseMap.put(Color.RED,bases[0]);
        this.colorBaseMap.put(Color.RED,bases[1]);
        this.colorBaseMap.put(Color.RED,bases[2]);
        this.colorBaseMap.put(Color.RED,bases[3]);
    }

    private void initGoals(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; i++){
                this.goals[i][j] = new Tile(j);
            }
        }


    }

    private void initGoalsHashMap(){
        this.goalsHashMap.put(Color.RED, goals[0]);
        this.goalsHashMap.put(Color.GREEN, goals[1]);
        this.goalsHashMap.put(Color.YELLOW, goals[2]);
        this.goalsHashMap.put(Color.BLUE, goals[3]);
    }

    public void addPieceToBase(Color baseColor, Piece p){
        Base b = this.colorBaseMap.get(baseColor);
        b.addPiece(p);
     }

    public Entity removePieceFromBase(Color baseColor) {
        Base b = this.colorBaseMap.get(baseColor);
        return b.removePiece();
    }

    public void addEntityToField(Entity e, int index) {
        Tile t = this.field[index];         //TODO kanske kan komma att ändras
        t.insertEntity(e);
    }

    /*          Antagligen onödigt komplicerat
    public void removeEntityFromField(Entity e) {
        for (int i = 0; i < field.length; i++) {
            if (e == field[i].getEntity()) {
                field[i].removeEntity();
            }
        }
    }*/

    public Entity removeEntityFromField(int index) {
        return field[index].removeEntity();
    }

    public void movePiece(int from, int offset) {
        Tile t = this.field[from];
        Entity e = t.removeEntity();
        Color c = this.field[from].getEntityColor();
        int tileIndex = playerStartTiles.get(c);
        int current = from;
        Tile[] goal = goalsHashMap.get(c);
        int goalIndex = 0;

        for (int i = 0; i < offset; i++){
            if ((current+1) == tileIndex){        //TODO handle when piece is already in goal
                for (int j = 0; j < (offset - i); j ++){
                    goalIndex += 1;
                }
            goal[goalIndex].insertEntity(e);
            }
            else {
                current += 1;
            }
        }
        this.field[current].insertEntity(e);
        // TODO handle collisions
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
