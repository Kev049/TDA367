package org.group7.model;


import org.group7.controllers.Observer;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board implements IMoveHandler {
    private Base[] bases;
    private Tile[] field;
    private GoalStretch[] goals;
    private Color[] colors;
    private HashMap<Color,Integer> playerStartTiles;
    private HashMap<Color, GoalStretch> goalsHashMap;
    private HashMap<Color,Base> colorBaseMap;
    private HashMap<Color, Piece[]> piecesHashMap;

    public Board() {
        this.bases = new Base[4];
        this.field = new Tile[40];                  //Kan man göra så att denna lista automatiskt loopar runt eller måste man ha mod40 varje gång man vill gå runt den?
        this.goals = new GoalStretch[4];
        this.colors = new Color[4];
        this.goalsHashMap = new HashMap<>();       // tycker att detta kanske borde vara en egen klass så att den inte ärver onödiga funktione
        this.playerStartTiles = new HashMap<>();
        this.colorBaseMap = new HashMap<>();
        this.piecesHashMap = new HashMap<>();
        initColors();
        initBases();
        initStartTileIndices();
        initColorBaseMap();
        initGoals();
        initGoalsHashMap();
        initTiles();
    }

    private void initTiles() {
        for (int i = 0; i < 40; i++){
            this.field[i] = new Tile(i);
        }
    }

    private void initColors() {
        this.colors[0] = Color.RED;
        this.colors[1] = Color.GREEN;
        this.colors[2] = Color.BLUE;
        this.colors[3] = Color.YELLOW;
    }

    private void initBases() {
        int i = 0;
        for (Color c : this.colors){
            this.bases[i] = new Base(4, c, this);
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
            this.goals[i] = new GoalStretch(c, this);
            i++;
        }
    }

    public void addGoalObserver(Observer o) {
        for (GoalStretch gs: this.goals) {
            gs.addObserver(o);
        }
    }

    private void initGoalsHashMap(){
        for (int i = 0; i < 4; i++){
            this.goalsHashMap.put(this.colors[i], goals[i]);
        }
    }


    private void addPieceToBase(Piece p){   //Private ksk??
        Color color = p.getColor();
        Base b = this.colorBaseMap.get(color);
        b.addPiece(p);
     }

    public void returnPieceToBase(Piece p) {
        int index = p.getPos();
        field[index].removeEntity();
        addPieceToBase(p);
    }

    public Piece extractPieceFromBase(Color baseColor) {
        Base b = this.colorBaseMap.get(baseColor);
        return b.removePiece();
    }

    public void activateBasePowerUp(Piece piece){
        this.field[piece.getPos()].removeEntity();
        pieceFromBaseToField(piece.getColor());
    }

    public void pieceFromBaseToField(Color c){
        Piece p = extractPieceFromBase(c);
        if (p != null) {
            addPieceToField(p, playerStartTiles.get(p.getColor()));
        }
    }

    public void addPieceToField(Piece p, int index) {
        Tile t = this.field[index];         //TODO kanske kan komma att ändras
        t.insertPiece(p);
    }

    public void yeetPieceFromGoal(Piece p){
        Color c = p.getColor();
        int tileIndex = playerStartTiles.get(c);
        Tile t = field[tileIndex];
        t.insertPiece(p);
    }

    /*          Antagligen onödigt komplicerat
    public void removeEntityFromField(Entity e) {
        for (int i = 0; i < field.length; i++) {
            if (e == field[i].getEntity()) {
                field[i].removeEntity();
            }
        }
    }*/

    /*
    public Entity removeEntityFromField(int index) {
        return field[index].removeEntity();
    }

     */



    public void addPieceToGoalStretch(Piece p, int steps) { //Color behövs inte explicit då player har den??
        GoalStretch goalStretch = this.goalsHashMap.get(p.getColor());
        goalStretch.addPiece(p, steps);
    }

    public void movePieceInGoalStretch(Piece piece, int steps){
        Color c = piece.getColor();
        GoalStretch goalStretch = goalsHashMap.get(c);
        goalStretch.goalStretchMove(piece, steps);
    }

    public void removeEntityFromGoalStretch(Color goalColor, int index)  {
        GoalStretch goalStretch = this.goalsHashMap.get(goalColor);
        goalStretch.removePiece(index);
    }

    private boolean completedLap(int from, int to, int start) { //Verkar fungera, testa? allt behövs kanske inte
        if (from < to) {
            return (from < start && to >= start);       //TODO Add explanation perhaps, currently hard to read
        } else {
            return (from < start || to >= start);
        }
    }

    public void movePiece(Piece piece, int offset) {  // Just nu finns movePiece och insertPiece, går det att slå ihop?
        int from = piece.getPos();
        Color c = piece.getColor();
        int tileIndex = playerStartTiles.get(c);
        int to = (from + offset) % 40;
        int stepsLeft = (to - tileIndex);
        if (piece.isAtGoalStretch()){        //TODO Refactor this if/else statement
            movePieceInGoalStretch(piece, stepsLeft);
        }
        else {
            this.field[from].removeEntity();
            if (completedLap(from, to, tileIndex)) {    // completed a lap, so should enter goal
                addPieceToGoalStretch(piece, stepsLeft);
            } else {                                    // still on first lap
                this.field[to].insertPiece(piece);
            }
        }

        //Unused below
        /*
        int current = from;
        GoalStretch goalStretch = goalsHashMap.get(c);
        int goalIndex = 0; //TODO should decide how far into goal it moves?

        for (int i = 0; i < offset; i++){
            if ((current+1) == tileIndex){        //TODO handle when piece is already in goal
                for (int j = 0; j < (offset - i); j ++){
                    goalIndex += 1;
                }
                goalStretch.addPiece(piece);    //TODO add
            }
            else {
                current += 1;
            }
        }*/
        //this.field[from + offset].insertPiece(piece);
    }

    /*
    public Piece nextPiece(Tile tile) { //TODO Fix this nasty method
        int startPos = tile.getIndex();
        for (int i = 0; i < 40; i++){
            Piece p = this.field[i+startPos].getPiece();
            if (p != null){
                return p;
            }
        }
        return null;
    }
    */


    public void spawnPowerUp(){
        BasePowerUp basePowerUp = new BasePowerUp(this);
        this.field[1].insertPowerUp(basePowerUp);
    }

    //Getters

    public List<Base> getBases(){
        return Arrays.asList(this.bases);
    }

    public Base getBaseFromColor(Color color) {
        return this.colorBaseMap.get(color);
    }

    public Piece[] getPiecesFromBase(Color color){
        for (Base b: this.bases) {
            if (b.getColor() == color)
                    return b.getPieces();
        }
        return null;
    }

    public List<Piece> getAllPieces(){
        ArrayList<Piece> pieceList = new ArrayList<>();
        for(Base base : bases){
            pieceList.addAll(Arrays.asList(base.getPieces()));
        }
        return pieceList;
    }


    public Tile[] getFieldTiles(){
        return this.field;
    }
    public List<Tile> getGoalTiles(){
        List<Tile> goalTiles = new ArrayList<>(16);
        for (GoalStretch goal : goals) {
            goalTiles.addAll(Arrays.asList(goal.getTiles()));
        }
        return goalTiles;
    }

    //--------------------------------------------------------

}
