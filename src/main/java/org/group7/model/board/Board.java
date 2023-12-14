package org.group7.model.board;


import org.group7.controller.observe.Observer;
import org.group7.model.PowerUpGenerator;
import org.group7.model.board.entities.EntityVisitor;
import org.group7.model.board.entities.RemoveFromFieldVisitor;
import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.PowerUp;
import org.group7.model.board.entities.powerups.handlers.IBasePowerUpHandler;
import org.group7.model.board.entities.powerups.handlers.ILaserPowerUpHandler;
import org.group7.model.board.entities.powerups.handlers.ILightningPowerUpHandler;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Board implements IMoveHandler, PieceExtractor, IBasePowerUpHandler, ILightningPowerUpHandler, ILaserPowerUpHandler {
    private final Base[] bases;
    private final Tile[] field;
    private final GoalStretch[] goalStretches;
    private final Color[] colors;
    private final HashMap<Color, Integer> playerStartTiles;
    private final HashMap<Color, GoalStretch> goalStretchesHashMap;
    private final HashMap<Color, Base> colorBaseMap;
    private final EntityVisitor visitor;
    private final int fieldTileAmount = 40;
    private final int playerAmount = 4;
    private final PowerUpGenerator powerUpGenerator;

    public Board() {
        this.bases = new Base[playerAmount];
        this.field = new Tile[fieldTileAmount];                  //Kan man göra så att denna lista automatiskt loopar runt eller måste man ha mod40 varje gång man vill gå runt den?
        this.goalStretches = new GoalStretch[playerAmount];
        this.colors = new Color[4];
        this.goalStretchesHashMap = new HashMap<>();       // tycker att detta kanske borde vara en egen klass så att den inte ärver onödiga funktione
        this.playerStartTiles = new HashMap<>();
        this.colorBaseMap = new HashMap<>();
        this.visitor = new RemoveFromFieldVisitor(this);
        this.powerUpGenerator = new PowerUpGenerator(this, this, this);
        initColors();
        initBases();
        initStartTileIndices();
        initColorBaseMap();
        initGoals();
        initGoalStretchesHashMap();
        initTiles();
    }

    private void initTiles() {
        for (int i = 0; i < fieldTileAmount; i++) {
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
        for (Color c : this.colors) {
            this.bases[i] = new Base(4, c, this);
            i++;
        }
    }

    private void initStartTileIndices() {
        for (int i = 0; i < playerAmount; i++) {
            this.playerStartTiles.put(this.colors[i], i * 10);
        }
    }

    private void initColorBaseMap() { //ska dennas for loop istället utgå från sådant som redan gjorts i initBases?
        for (int i = 0; i < playerAmount; i++) {
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

    private void initGoalStretchesHashMap() {
        for (int i = 0; i < 4; i++) {
            this.goalStretchesHashMap.put(this.colors[i], goalStretches[i]);
        }
    }

    public void addGoalObserver(Observer o) {
        for (GoalStretch gs : this.goalStretches) {
            gs.addObserver(o);
        }
    }

    @Override
    public void addPiece(Piece p, int index) {
        this.field[index].insertPiece(p);
    }

    private void addPieceToBase(Piece p) {   //Private ksk??
        Color color = p.getColor();
        Base b = this.colorBaseMap.get(color);
        b.addPiece(p);
    }

    public void addPieceToGoalStretch(Piece p, int steps) {
        GoalStretch goalStretch = this.goalStretchesHashMap.get(p.getColor());
        p.setHandler(goalStretch);
        p.toggleState();
        goalStretch.addPiece(p, steps);
    }

    public void returnPieceToBase(Piece p) {
        int index = p.getPos();
        field[index].removeEntity();
        addPieceToBase(p);
    }

    public void removeEntitiesFromField(int pos) {
        for (int i = 1; i < 9; i++) {
            int index = (pos + i) % 40;
            if (!field[index].isEmpty()) {
                field[index].getEntity().accept(visitor);
            }
        }
    }

    public void removePowerUpFromField(PowerUp powerUp) {
        this.field[powerUp.getPos()].removeEntity();
    }

    public Piece extractPieceFromBase(Color baseColor) {
        Base b = this.colorBaseMap.get(baseColor);
        return b.removePiece();
    }


    public void pieceFromBaseToField(Color c, int diceRoll) {
        Piece p = extractPieceFromBase(c);
        if (p != null) {        // Skyddar mot tom bas, kanske finns något snyggare, exempelvis att base inte är "tryckbar" då den är tom
            addPiece(p, playerStartTiles.get(p.getColor()) + diceRoll - 1);
        }
    }

    public void pieceFromGoalStretchToField(Piece p) {
        Color c = p.getColor();
        int tileIndex = playerStartTiles.get(c);
        tileIndex = ((((tileIndex - 1) % 40) + 40) % 40); // Positive modulo
        field[tileIndex].insertPiece(p);
        p.setHandler(this);
        p.toggleState();
    }

    private boolean completedLap(int prevPos, int nextPos, int start) { //Verkar fungera, testa? allt behövs kanske inte
        if (prevPos < nextPos) { //if next pos is larger than pos, which will not happen if nextPos is >40
            return (prevPos < start && nextPos >= start);       //TODO Add explanation perhaps, currently hard to read
        } else {
            return (prevPos < start || nextPos >= start);
        }
    }

    public void movePieceInGoalStretch(Piece piece, int steps) {
        Color c = piece.getColor();
        GoalStretch goalStretch = goalStretchesHashMap.get(c);
        goalStretch.goalStretchMove(piece, steps);
    }

    public void movePiece(Piece piece, int diceRoll) {  // Just nu finns movePiece och insertPiece, går det att slå ihop?
        int from = piece.getPos();
        Color c = piece.getColor();
        int tileIndex = playerStartTiles.get(c);
        int to = (from + diceRoll) % 40;
        if (piece.isAtGoalStretch()) {        //TODO Refactor this if/else statement
            movePieceInGoalStretch(piece, diceRoll);
        } else {
            this.field[from].removeEntity();
            if (completedLap(from, to, tileIndex)) {    // completed a lap, so should enter goalStretch
                int stepsLeft = (to - tileIndex);
                addPieceToGoalStretch(piece, stepsLeft);
            } else {                                    // still on first lap
                this.field[to].insertPiece(piece);
            }
        }
    }

    public void spawnPowerUps() {
        Random rand = new Random();
        powerUpGenerator.initPowerUps();
        List<PowerUp> powerUps = powerUpGenerator.getPowerUps();
        for(PowerUp powerUp : powerUps){
            this.field[rand.nextInt(fieldTileAmount)].insertPowerUp(powerUp);
        }
    }

    //Getters

    public List<Base> getBases() {
        return Arrays.asList(this.bases);
    }

    public Base getBaseFromColor(Color color) {
        return this.colorBaseMap.get(color);
    }

    public Tile[] getFieldTiles() {
        return this.field;
    }

    public List<Tile> getGoalTiles() {
        List<Tile> goalTiles = new ArrayList<>(16);
        for (GoalStretch goal : goalStretches) {
            goalTiles.addAll(Arrays.asList(goal.getTiles()));
        }
        return goalTiles;
    }

    public int getPieceAmount(Color c) {
        return colorBaseMap.get(c).getPieceAmount();
    }

}
