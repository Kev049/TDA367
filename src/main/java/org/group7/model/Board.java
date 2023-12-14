package org.group7.model;


import org.group7.controllers.Observer;
import org.group7.model.PowerUps.BasePowerUp;
import org.group7.model.PowerUps.LaserPowerUp;
import org.group7.model.PowerUps.LightningPowerUp;
import org.group7.model.PowerUps.PowerUp;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Board implements IMoveHandler, PieceExtractor, IBasePowerUpHandler, ILightningPowerUpHandler, ILaserPowerUpHandler{
    private final Base[] bases;
    private final Tile[] field;
    private final GoalStretch[] goalStretches;
    private final Color[] colors;
    private final HashMap<Color,Integer> playerStartTiles;
    private final HashMap<Color, GoalStretch> goalsHashMap;
    private final HashMap<Color,Base> colorBaseMap;
    private final EntityVisitor visitor;

    public Board() {
        this.bases = new Base[4];
        this.field = new Tile[40];                  //Kan man göra så att denna lista automatiskt loopar runt eller måste man ha mod40 varje gång man vill gå runt den?
        this.goalStretches = new GoalStretch[4];
        this.colors = new Color[4];
        this.goalsHashMap = new HashMap<>();       // tycker att detta kanske borde vara en egen klass så att den inte ärver onödiga funktione
        this.playerStartTiles = new HashMap<>();
        this.colorBaseMap = new HashMap<>();
        this.visitor = new RemoveEntityVisitor(this);
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
            this.goalStretches[i] = new GoalStretch(c, this);
            i++;
        }
    }

    public void addGoalObserver(Observer o) {
        for (GoalStretch gs: this.goalStretches) {
            gs.addObserver(o);
        }
    }

    private void initGoalsHashMap(){
        for (int i = 0; i < 4; i++){
            this.goalsHashMap.put(this.colors[i], goalStretches[i]);
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

    public void removeFromField(int pos){
        for(int i = 1; i < 9; i++){
            int index = (pos + i) % 40;
            if(!field[index].isEmpty()){
                field[index].getEntity().accept(visitor);
            }
        }
    }

    public Piece extractPieceFromBase(Color baseColor) {
        Base b = this.colorBaseMap.get(baseColor);
        return b.removePiece();
    }

    public void removePowerUpFromField(PowerUp powerUp){
        this.field[powerUp.getPos()].removeEntity();
    }

    public void pieceFromBaseToField(Color c, int diceRoll){
        Piece p = extractPieceFromBase(c);
        if (p != null) {        // Skyddar mot tom bas, kanske finns något snyggare, exempelvis att base inte är "tryckbar" då den är tom
            addPiece(p,playerStartTiles.get(p.getColor()) + diceRoll - 1);
        }
    }

    @Override
    public void addPiece(Piece p, int index) {
        //int tileIndex = playerStartTiles.get(p.getColor());
        //int from = p.getPos();
        //if (completedLap(from, index, tileIndex)) {    // completed a lap, so should enter goalStretch
        //    int stepsLeft = (index - tileIndex);
        //    addPieceToGoalStretch(p, stepsLeft);
        //} else {                                    // still on first lap
        //    this.field[index].insertPiece(p);
        //}
        //insertsPieceAtCorrectPos(0, p);
        Tile t = this.field[index];         //TODO kanske kan komma att ändras
        t.insertPiece(p);
    }

    public void yeetPieceFromGoal(Piece p){
        Color c = p.getColor();
        int tileIndex = playerStartTiles.get(c);
        Tile t;
        if(tileIndex == 0) {
           t = field[39];
        } else {  t = field[tileIndex - 1];}
        t.insertPiece(p);
        p.setHandler(this);
        p.enableFieldState();
    }

    public void addPieceToGoalStretch(Piece p, int steps) {
        GoalStretch goalStretch = this.goalsHashMap.get(p.getColor());
        p.setHandler(goalStretch);
        p.enableGoalState();
        goalStretch.addPiece(p, steps);
    }

    public void removeEntityFromGoalStretch(Color goalColor, int index){  //TODO denna eller yeet?
        GoalStretch goalStretch = this.goalsHashMap.get(goalColor);
        goalStretch.removeEntity(index);
    }

    private boolean completedLap(int prevPos, int nextPos, int start) { //Verkar fungera, testa? allt behövs kanske inte
        if (prevPos < nextPos) { //if next pos is larger than pos, which will not happen if nextPos is >40
            return (prevPos < start && nextPos >= start);       //TODO Add explanation perhaps, currently hard to read
        } else {
            return (prevPos < start || nextPos >= start);
        }
    }

    public void movePieceInGoalStretch(Piece piece, int steps){
        Color c = piece.getColor();
        GoalStretch goalStretch = goalsHashMap.get(c);
        goalStretch.goalStretchMove(piece, steps);
    }

    public void movePiece(Piece piece, int diceRoll){  // Just nu finns movePiece och insertPiece, går det att slå ihop?
        int from = piece.getPos();
        Color c = piece.getColor();
        int tileIndex = playerStartTiles.get(c);
        int to = (from + diceRoll) % 40;
        if (piece.isAtGoalStretch()) {        //TODO Refactor this if/else statement
            movePieceInGoalStretch(piece, diceRoll);
        }
        else{
            this.field[from].removeEntity();
            if (completedLap(from, to, tileIndex)) {    // completed a lap, so should enter goalStretch
                int stepsLeft = (to - tileIndex);
                //System.out.println(stepsLeft);
                addPieceToGoalStretch(piece, stepsLeft);
            } else {                                    // still on first lap
                this.field[to].insertPiece(piece);
            }
        }
    }

    public void spawnPowerUps(){
        Random rand = new Random();
        LightningPowerUp lightningPowerUp = EntityFactory.createLightningPowerUp(this);
        BasePowerUp basePowerUp = EntityFactory.createBasePowerUp(this);
        LaserPowerUp laserPowerUp = EntityFactory.createLaserPowerUp(this);
        this.field[rand.nextInt(40)].insertPowerUp(lightningPowerUp);
        this.field[rand.nextInt(40)].insertPowerUp(basePowerUp);
        this.field[rand.nextInt(40)].insertPowerUp(laserPowerUp);
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

    public Tile[] getFieldTiles(){
        return this.field;
    }
    public List<Tile> getGoalTiles(){
        List<Tile> goalTiles = new ArrayList<>(16);
        for (GoalStretch goal : goalStretches) {
            goalTiles.addAll(Arrays.asList(goal.getTiles()));
        }
        return goalTiles;
    }

    public int getPieceAmount(Color c){
        return colorBaseMap.get(c).getPieceAmount();
    }

}
