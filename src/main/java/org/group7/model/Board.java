package org.group7.model;


import org.group7.controllers.Observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Board implements Observer, ICollisionHandler {

    private Base[] bases;
    private Tile[] field;
    private GoalStrech[] goals;

    private Color[] colors;
    private HashMap<Color,Integer> playerStartTiles;
    private HashMap<Color, GoalStrech> goalsHashMap;
    private HashMap<Color,Base> colorBaseMap;
    private HashMap<Color, Piece[]> piecesHashMap;

    public Board() {
        this.bases = new Base[4];
        this.field = new Tile[40];                  //Kan man göra så att denna lista automatiskt loopar runt eller måste man ha mod40 varje gång man vill gå runt den?
        this.goals = new GoalStrech[4];
        this.colors = new Color[4];
        this.goalsHashMap = new HashMap<>();       // tycker att detta kanske borde vara en egen klass så att den inte ärver onödiga funktione
        this.playerStartTiles = new HashMap<>();
        this.colorBaseMap = new HashMap<>();
        this.piecesHashMap = new HashMap<>();
        initColors();
        initPieces();
        initBases();
        initStartTileIndices();
        initColorBaseMap();
        initGoals();
        initGoalsHashMap();
        for (int i = 0; i < 40; i++) {
            this.field[i] = new Tile(i);
        }
    }

    private void initColors() {
        this.colors[0] = Color.RED;
        this.colors[1] = Color.GREEN;
        this.colors[2] = Color.YELLOW;
        this.colors[3] = Color.BLUE;
    }

    private void initPieces() {
        for(Color c : this.colors) {
            Piece[] piecesArray = new Piece[4];
            for (int i = 0; i < 4; i++) {
                piecesArray[i] = PieceFactory.createPiece(c);
            }
            piecesHashMap.put(c, piecesArray);
        }
    }

    private void initBases() {
        int i = 0;
        for (Color c : this.colors){
            this.bases[i] = new Base(4, c);
            i++;
        }

    }

    private void initStartTileIndices() {
        for (int i = 0; i < 4; i++) {
            this.playerStartTiles.put(this.colors[i], i*10);
        }
    }

    private void initColorBaseMap() {
        for (int i = 0; i < 4; i++) {
            this.colorBaseMap.put(this.colors[i], bases[i]);
        }
    }

    private void initGoals() {
        int i = 0;
        for (Color c : this.colors) {
            this.goals[i] = new GoalStrech(c);
            i++;
        }
    }
    

    private void initGoalsHashMap(){
        for (int i = 0; i < 4; i++){
            this.goalsHashMap.put(this.colors[i], goals[i]);
        }
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

    public void addEntityToGoalStretch(Color goalColor, Piece p) {
        GoalStrech goalStrech = this.goalsHashMap.get(goalColor);
        goalStrech.addPiece(p, 0);
    }

    public void removeEntityFromGoalStretch(Color goalColor, int index)  {
        GoalStrech goalStrech = this.goalsHashMap.get(goalColor);
        goalStrech.removePiece(index);
    }

    public void movePiece(int from, int offset) {
        Tile t = this.field[from];
        Entity e = t.removeEntity();
        Color c = this.field[from].getEntityColor();
        int tileIndex = playerStartTiles.get(c);
        int current = from;
        GoalStrech goalStretch = goalsHashMap.get(c);
        int goalIndex = 0;

        for (int i = 0; i < offset; i++){
            if ((current+1) == tileIndex){        //TODO handle when piece is already in goal
                for (int j = 0; j < (offset - i); j ++){
                    goalIndex += 1;
                }
            goalStretch.addPiece(e, goalIndex);
            }
            else {
                current += 1;
            }
        }
        this.field[current].insertEntity(e);
        // TODO handle collisions
    }


    public Entity nextPiece(Piece piece) {
        int startPos = piece.get_pos();
        for (int i = 0; i < 40; i++){
            Entity e = this.field[i+startPos].getEntity();
            if (e instanceof Piece){
                return e;
            }
        }
        return null;
    }

    //Getters

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

    public ArrayList<Piece> getAllPieces(){
        ArrayList<Piece> pieceList = new ArrayList<Piece>();
        for (Color c : colors) {
            for (Piece piece : this.piecesHashMap.get(c)) {
                pieceList.add(piece);
            }
        }
        return pieceList;
    }

    public ArrayList<Tile> getAllTiles() {
        int result = field.length + goals.length + bases.length + 1; //+1 är för målet
        //field + goals
        ArrayList<Tile> allTiles = new ArrayList<Tile>(result);
        allTiles.addAll(field);
        Collections.addAll(allTiles)
        //allTiles[0] = Tile1;


        return allTiles;
    }

    //--------------------------------------------------------

    @Override
    public void update() {

    }

    @Override
    public void update(int index){
        handleCollision(index);
    }

    public void handleCollision(int index){
        Entity entity = this.field[index].getEntity();
        if (entity instanceof Piece){
            this.field[index].removeEntity();
        }
        else if (entity instanceof PowerUp){
            //TODO Code to handle collision with powerup
        }

    }

    @Override
    public void movePiece(Piece piece) {
        //TODO implement
    }

}
