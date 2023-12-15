package org.group7.model.board;


import org.group7.controller.observe.Observer;
import org.group7.model.entities.powerups.PowerUpGenerator;
import org.group7.model.entities.EntityVisitor;
import org.group7.model.entities.RemoveFromFieldVisitor;
import org.group7.model.entities.piece.Piece;
import org.group7.model.entities.powerups.PowerUp;
import org.group7.model.entities.powerups.handlers.IBasePowerUpHandler;
import org.group7.model.entities.powerups.handlers.ILaserPowerUpHandler;
import org.group7.model.entities.powerups.handlers.ILightningPowerUpHandler;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * The Board class represents the game board.
 * */
public class Board implements IMoveHandler, PieceExtractor, IBasePowerUpHandler, ILightningPowerUpHandler, ILaserPowerUpHandler {
    private final Base[] bases;
    private final Tile[] field;
    private final GoalStretch[] goalStretches;
    private final Color[] colors;
    private final HashMap<Color, Integer> playerStartTiles;
    private final HashMap<Color, GoalStretch> goalStretchesHashMap;
    private final HashMap<Color, Base> colorBaseMap;
    private final EntityVisitor visitor;
    private final int FIELD_TILE_AMOUNT = 40;
    private final int GOAL_TILE_AMOUNT = 16;
    private final int playerAmount;
    private final PowerUpGenerator powerUpGenerator;

    /**
     * Constructor for Board class. Initializes the board by calling several init methods.
     * */    
    public Board(Color[] colors) {
        this.colors = colors;
        this.playerAmount = colors.length;
        this.bases = new Base[playerAmount];
        this.field = new Tile[FIELD_TILE_AMOUNT];
        this.goalStretches = new GoalStretch[playerAmount];
        this.goalStretchesHashMap = new HashMap<>();
        this.playerStartTiles = new HashMap<>();
        this.colorBaseMap = new HashMap<>();
        this.visitor = new RemoveFromFieldVisitor(this);
        this.powerUpGenerator = new PowerUpGenerator(this, this, this);
        initComponents();
    }

    private void initComponents() {
        initColors();
        initBases();
        initStartTileIndices();
        initColorBaseMap();
        initGoals();
        initGoalStretchesHashMap();
        initTiles();
        spawnPowerUps();
    }

    /**
     * Initializes an array of Tile objects (spaces) for the board.
     */
    private void initTiles() {
        for (int i = 0; i < FIELD_TILE_AMOUNT; i++) {
            this.field[i] = new Tile(i);
        }
    }

    /**
     * Initializes an array of colors with four specific values (for each player).
     */
    private void initColors() {
        this.colors[0] = Color.RED;
        this.colors[1] = Color.GREEN;
        this.colors[2] = Color.BLUE;
        this.colors[3] = Color.YELLOW;
    }

    /**
     * Initializes an array of Base objects with a fixed size of 4, assigning each Base a color.
     */
    private void initBases() {
        int baseCapacity = 4;
        int i = 0;
        for (Color c : this.colors) {
            this.bases[i] = new Base(baseCapacity, c, this);
            i++;
        }
    }

    /**
     * Initializes the start tile indices for each player based on their assigned color.
     */
    private void initStartTileIndices() {
        for (int i = 0; i < playerAmount; i++) {
            this.playerStartTiles.put(this.colors[i], i * 10);
        }
    }

    /**
     * Initializes a color base map by mapping each color to its corresponding base.
     */
    private void initColorBaseMap() {
        for (int i = 0; i < playerAmount; i++) {
            this.colorBaseMap.put(this.colors[i], bases[i]);
        }
    }

    /**
     * Initializes goal stretches for each color in the colors array.
     */
    private void initGoals() {
        int i = 0;
        for (Color c : this.colors) {
            this.goalStretches[i] = new GoalStretch(c, this);
            i++;
        }
    }

    /**
     * Initializes a HashMap with color keys and corresponding goal stretch values.
     */
    private void initGoalStretchesHashMap() {
        for (int i = 0; i < 4; i++) {
            this.goalStretchesHashMap.put(this.colors[i], goalStretches[i]);
        }
    }

    /**
     * The function adds an observer to each goal stretch in the goalStretches list.
     * 
     * @param o The parameter "o" is an instance of the Observer class.
     */
    public void addGoalObserver(Observer o) {
        for (GoalStretch gs : this.goalStretches) {
            Goal g = (Goal)gs.getGoal();
            g.addObserver(o);
        }
    }

    /**
     * Inserts a given piece at a specified index in the field.
     * 
     * @param p The parameter "p" is of type Piece, which represents a game piece that can be added to
     * the field.
     * @param index The index parameter represents the position in the field array where the piece
     * should be added.
     */
    @Override
    public void addPiece(Piece p, int index) {
        this.field[index].insertPiece(p);
    }

    /**
     * The function adds a piece to the corresponding base based on its color.
     * 
     * @param p The parameter "p" is of type Piece, which represents a game piece.
     */
    private void addPieceToBase(Piece p) {
        Color color = p.getColor();
        Base b = this.colorBaseMap.get(color);
        b.addPiece(p);
    }

    /**
     * The function adds a piece to the "correct" goal stretch (based on its color).
     * 
     * @param p The parameter "p" is of type Piece, which represents a game piece.
     * @param steps The parameter "steps" is of type int, which represents the number of steps the
     * piece should take.
     */
    public void addPieceToGoalStretch(Piece p, int steps) {
        GoalStretch goalStretch = this.goalStretchesHashMap.get(p.getColor());
        p.setHandler(goalStretch);
        p.toggleState();
        goalStretch.addPiece(p, steps);
    }


    /**
     * The function removes a piece from its tile and adds it to the corresponding
     * base based on its color.
     * 
     * @param p The parameter "p" is of type Piece, which represents a game piece.
     */
    public void returnPieceToBase(Piece p) {
        int index = p.getPos();
        field[index].removeEntity();
        addPieceToBase(p);
    }

    
    /**
     * The function removes all entities from a field starting from a given position and iterates through
     * the field in a circular manner for 8 tiles.
     * 
     * @param pos The `pos` parameter represents the starting position from where entities will be
     * removed.
     * @param amountTiles The`amountTiles` parameter represents the number of tiles to iterate through.
     */
    public void removeEntitiesFromField(int pos, int amountTiles) {
        for (int i = 1; i < amountTiles + 1; i++) {
            int index = (pos + i) % FIELD_TILE_AMOUNT;
            if (!field[index].isEmpty()) {
                field[index].getEntity().accept(visitor);
            }
        }
    }

    
    /**
     * Removes a power-up entity from a specific position on the field.
     * 
     * @param powerUp The powerUp parameter is an instance of the PowerUp class.
     */
    public void removePowerUpFromField(PowerUp powerUp) {
        this.field[powerUp.getPos()].removeEntity();
    }

    /**
     * Removes and returns a piece from its base.
     * 
     * @param baseColor represents the color of the base from which we want to extract a piece.
     * @return The method is returning a Piece object.
     */
    public Piece extractPieceFromBase(Color baseColor) {
        Base b = this.colorBaseMap.get(baseColor);
        return b.removePiece();
    }

    
    /**
     * The function moves a piece from a specific base (based on the color) to the field
     * using the dice roll.
     * 
     * @param c The color of the player's pieces (e.g., Color.RED or Color.BLUE).
     * @param diceRoll The diceRoll parameter represents the number rolled on a die. It is used to
     * determine the destination field for the piece being moved from the base.
     */
    public void pieceFromBaseToField(Color c, int diceRoll) {
        Piece p = extractPieceFromBase(c);
        if (p != null) { //Protects against empty bases
            addPiece(p, playerStartTiles.get(p.getColor()) + diceRoll - 1);
        }
    }


    /**
     * The function takes a piece and moves it from the goal stretch to the field, updating its
     * position and state.
     * 
     * @param p The parameter "p" is of type "Piece".
     */
    public void pieceFromGoalStretchToField(Piece p) {
        Color c = p.getColor();
        int tileIndex = playerStartTiles.get(c);
        tileIndex = ((((tileIndex - 1) % FIELD_TILE_AMOUNT) + FIELD_TILE_AMOUNT) % FIELD_TILE_AMOUNT); // Positive modulo
        field[tileIndex].insertPiece(p);
        p.setHandler(this);
        p.toggleState();
    }

    /**
     * The function checks if a lap has been completed based on the previous and next positions of a
     * player and the starting position.
     * 
     * @param prevPos The previous position of the selected piece.
     * @param nextPos The next position of the selected piece.
     * @param start Represents the starting tile for the player who owns the piece.
     * @return The method returns a boolean value representing whether the piece has completed a lap of the board.
     */
    private boolean completedLap(int prevPos, int nextPos, int start) {
        if (prevPos < nextPos) { //if next pos is larger than pos, which will not happen if nextPos is >40
            return (prevPos < start && nextPos >= start);
        } else {
            return (prevPos < start || nextPos >= start);
        }
    }

    /**
     * The function moves a piece in the goal stretch based on the number of steps.
     * 
     * @param piece The parameter "piece" is of type Piece, which represents a game piece.
     * @param steps The parameter "steps" is of type int, which represents the number of steps the
     * piece should take.
     */
    public void movePieceInGoalStretch(Piece piece, int steps) {
        Color c = piece.getColor();
        GoalStretch goalStretch = goalStretchesHashMap.get(c);
        goalStretch.goalStretchMove(piece, steps);
    }

    /**
     * The function moves a piece based on the dice roll.
     * 
     * @param piece The parameter "piece" is of type Piece, which represents a game piece.
     * @param diceRoll The parameter "diceRoll" is of type int, which represents the number rolled
     * on a die.
     */
    public void movePiece(Piece piece, int diceRoll) {
        int from = piece.getPos();
        Color c = piece.getColor();
        int tileIndex = playerStartTiles.get(c);
        int to = (from + diceRoll) % FIELD_TILE_AMOUNT;
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


    /**
     * The function spawns power-ups randomly on the game field.
     */
    public void spawnPowerUps() {
        Random rand = new Random();
        for(PowerUp powerUp : getGeneratedPowerUps()){
            this.field[rand.nextInt(FIELD_TILE_AMOUNT)].insertPowerUp(powerUp);
        }
    }

    /**
     * The function generates power-ups and returns them in a list.
     * 
     * @return The function returns a list of power-ups.
     */
    private List<PowerUp> getGeneratedPowerUps(){
        return powerUpGenerator.generatePowerUps();
        //return powerUpGenerator.getPowerUps();
    }

    //Getters


    /**
     * The function returns a list of Base objects.
     * 
     * @return The method is returning a List of objects of type Base.
     */
    public List<Base> getBases() {
        return Arrays.asList(this.bases);
    }

    /**
     * The function returns the base associated with a given color.
     * 
     * @param color The color parameter is used to identify the appropriate base from the colorBaseMap.
     * @return The method is returning a Base object.
     */
    public Base getBaseFromColor(Color color) {
        return this.colorBaseMap.get(color);
    }

    /**
     * The function returns an array of Tile objects representing the field.
     * 
     * @return The method is returning an array of Tile objects.
     */
    public Tile[] getFieldTiles() {
        return this.field;
    }


    /**
     * The function returns a list of goal tiles by iterating through goal stretches and adding their
     * tiles to the list.
     * 
     * @return The method is returning a List of Tile objects.
     */
    public List<IInsertable> getGoalTiles() {
        List<IInsertable> goalTiles = new ArrayList<>(GOAL_TILE_AMOUNT);
        for (GoalStretch goal : goalStretches) {
            goalTiles.addAll(Arrays.asList(goal.getTiles()));
        }
        return goalTiles;
    }

    /**
     * The function returns the number of pieces of a specific color.
     * 
     * @param c The parameter "c" is of type "Color" and is used to identify which pieces to consider.
     * @return The method is returning the amount of pieces for a given color.
     */
    public int getPieceAmount(Color c) {
        return colorBaseMap.get(c).getPieceAmount();
    }

}
