package org.group7.model.game;

import org.group7.controller.observe.Observable;
import org.group7.controller.observe.Observer;
import org.group7.controller.observe.StringObservable;
import org.group7.controller.observe.StringObserver;
import org.group7.model.board.Board;
import org.group7.model.entities.piece.Piece;
import org.group7.model.utilities.Dice;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The Game class is the root of the application that is responsible for the game logic.
 * It contains the board, the dice, the game-state, turn number and the current player.
 */
public class Game implements StringObservable, Observable, Observer {

    private final Dice dice;
    private final Board board;
    private GameState gameState;
    private final Color[] colorArray;
    private int currentColorIndex;
    private int lastDiceRollResult;
    private int turnNumber;
    private final int PIECE_PER_PLAYER = 4;
    private final int AMOUNT_OF_COLORS = 4;
    private final int AMOUNT_OF_PLAYERS = 4;
    private final Set<StringObserver> stringObservers;
    private final Set<Observer> observers;
    private final HashMap<Color, Integer> finishedPieces;

    /**
     * Constructor for Game
     */
    public Game() {
        this.dice = new Dice();
        this.colorArray = new Color[AMOUNT_OF_COLORS];
        initColors();
        this.board = new Board(this.colorArray);
        this.board.addGoalObserver(this);
        this.turnNumber = 0;
        this.lastDiceRollResult = 0;
        this.gameState = new RollState(this);
        this.stringObservers = new HashSet<>();
        this.observers = new HashSet<>();
        this.finishedPieces = new HashMap<>();
        initFinishedPieces();
    }

    /**
     * Initializes the colors of the players
     */
    public void initColors() {
        this.colorArray[0] = Color.RED;
        this.colorArray[1] = Color.GREEN;
        this.colorArray[2] = Color.BLUE;
        this.colorArray[3] = Color.YELLOW;
        currentColorIndex = 0;
    }

    /**
     * Initializes the finished pieces map (how many pieces each player has finished)
     */
    private void initFinishedPieces() {
        for (int i = 0; i < PIECE_PER_PLAYER; i++) {
            this.finishedPieces.put(this.colorArray[i], 0);
        }
    }

    /**
     * Rolls the dice and sets the lastDiceRollResult to the result of the roll.
     * The method also checks if there are any moves available, and if not, the turn is finished.
     */
    protected void rollDice() {
        this.lastDiceRollResult = dice.roll();
        if (noMovesAvailable()) {
            finishRoll();
        } else {
            setState(new MoveState(this));
        }
    }

    /**
     * Rolls the dice and returns the result of the roll.
     * @return the result of the roll
     */
    public int roll() {
        notifyObservers();
        gameState.roll();
        return this.lastDiceRollResult;
    }

    /**
     * Checks if the move is valid and if it is, moves the piece.
     * @param piece the piece to be moved
     * @return true if the move is valid, false otherwise
     */
    protected boolean validateMove(Piece piece) {
        return (colorArray[currentColorIndex].equals(piece.getColor()));
    }

    /**
     * Moves the piece depending on the game-state.
     * @param piece the piece to be moved
     */
    public void move(Piece piece) {
        gameState.move(piece);
    }

    /**
     * Moves the piece to the field if the move is valid.
     * @param piece the piece to be moved
     */
    protected void movePiece(Piece piece) {
        if (this.validateMove(piece)) {
            this.board.movePiece(piece, this.lastDiceRollResult);
            notifyObservers();
            finishMove();
        }
    }

    private void finishMove() {
        gameState.nextState(this);
        if (!hasRolledSix()) {
            nextPlayer();
        } else {
            increaseTurnNumber();
        }
    }

    /*
     * Calls the helper methods after rolling the dice.
     */
    private void finishRoll() {
        increaseTurnNumber();
        tryForPowerupSpawn();
        nextPlayer();
    }

    protected boolean validateBaseMove(Color color) {
        return ((colorArray[currentColorIndex].equals(color) && !(board.getBaseFromColor(color).isEmpty()))
                && (this.lastDiceRollResult == 1 || this.lastDiceRollResult == 6));
    }

    /*
     * Moves a piece from the base to the field depending on the game state.
     * @param color the color of the piece
     */
    public void moveBasePiece(Color color) {
        this.gameState.pieceFromBaseToField(color);
    }

    /*
     * Moves a piece from the base to the field if the move is valid.
     * @param color the color of the base
     */
    public void movePieceOutOfBase(Color color) {
        if (this.validateBaseMove(color)) {
            board.pieceFromBaseToField(color, lastDiceRollResult);
            notifyObservers();
            finishMove();
        }
    }

    /*
     * Spawns power-ups on the board and updates all observers.
     */
    private void spawnPowerups() {
        this.board.spawnPowerUps();
        notifyObservers();
    }

    /*
     * Changes the current player to the next player and notifies observers.
     */
    protected void nextPlayer() {
        this.currentColorIndex = (this.currentColorIndex + 1) % AMOUNT_OF_PLAYERS;
        String playerColor = this.colorArray[this.currentColorIndex].toString();
        notifyObservers(playerColor);
    }

    /*
     * Checks if there are any moves available for the current player.
     * @return true if there are no moves available, false otherwise
     */
    public boolean noMovesAvailable() {
        return (baseMovePossible() && noPiecesLeft());
    }

    /*
     * Checks if the player has any pieces left.
     * @return true if the player has no pieces left, false otherwise
     */
    private boolean noPiecesLeft() {
        Color c = colorArray[currentColorIndex];
        int pieceAmount = board.getPieceAmount(c);
        return ((this.finishedPieces.get(c) + pieceAmount) == PIECE_PER_PLAYER);
    }

    /*
     * Checks if the player can move a piece from their base to the field.
     * @return true if the player can't move a piece from their base to the field, false otherwise
     */
    private boolean baseMovePossible() {
        return (this.lastDiceRollResult != 1 && this.lastDiceRollResult != 6);
    }

    /*
     * Checks if the player just rolled a six.
     * @return true if the player just rolled a six, false otherwise.
     */
    private boolean hasRolledSix(){
        return this.lastDiceRollResult == 6;
    }

    /*
     * Increases the turn number by one.
     */
    public void increaseTurnNumber(){
        this.turnNumber++;
    }

    /*
     * Checks if power-ups should spawn and spawns it if it should.
     */
    public void tryForPowerupSpawn() {
        int turnsBetweenPowerupSpan = 6;
        if (turnNumber % turnsBetweenPowerupSpan == 0) {
            spawnPowerups();
        }
    }

    /*
     * The generic update method for Game that checks if a player has won the game.
     */
    @Override
    public void update() {
        Color c = colorArray[currentColorIndex];
        int increasedFinishedPieces = this.finishedPieces.get(c) + 1;
        this.finishedPieces.replace(c, increasedFinishedPieces);
        if (increasedFinishedPieces == PIECE_PER_PLAYER) {
            System.out.println(currentColorIndex + "won!");
        }
    }

    /*
     * Adds a StringObserver to the list of observers.
     * @param stringObserver the StringObserver to be added
     */
    @Override
    public void addObserver(StringObserver stringObserver) {
        stringObservers.add(stringObserver);
    }

    /*
     * Adds an Observer to the list of observers.
     * @param observer the Observer to be added
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /*
     * Notifies all observers of who the current player is (through its color).
     * @param playerColor the color of the current player
     */
    @Override
    public void notifyObservers(String playerColor) {
        for (StringObserver o : this.stringObservers) {
            o.update(playerColor);
        }
    }

    /*
     * Notifies all observers.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }


    /*
     * Sets the game-state to the given game-state.
     * @param gamestate the game-state to be set
     */
    protected void setState(GameState gamestate) {
        this.gameState = gamestate;
    }

    public Board getBoard(){
        return this.board;
    }
}


