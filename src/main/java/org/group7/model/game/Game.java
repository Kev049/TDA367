package org.group7.model.game;

import org.group7.controller.observe.Observable;
import org.group7.controller.observe.Observer;
import org.group7.controller.observe.StringObservable;
import org.group7.controller.observe.StringObserver;
import org.group7.model.board.Board;
import org.group7.model.board.entities.piece.Piece;
import org.group7.model.utilities.Dice;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Game implements StringObservable, Observable, Observer {   //TODO ta bort onödiga metoder

    private final Dice dice;
    private final Board board;
    private GameState gameState;
    private final Color[] colorArray;
    private int currentColor;
    private int lastDiceRollResult;
    private int turnNumber;
    private final int piecePerPlayer = 4;
    private final Set<StringObserver> stringObservers;
    private final Set<Observer> observers;
    private final HashMap<Color, Integer> finishedPieces;

    public Game(Board board) { //TODO Game should create the board, not Main
        this.dice = Dice.getInstance();
        this.board = board;
        this.board.addGoalObserver(this);
        this.colorArray = new Color[4];
        initColors();
        this.turnNumber = 0;
        this.lastDiceRollResult = 0;
        this.gameState = new RollState(this); //TODO this should come from the constructor to avoid dependency
        this.stringObservers = new HashSet<>();
        this.observers = new HashSet<>();
        this.finishedPieces = new HashMap<>();
        initFinishedPieces();
    }

    public void initColors() {
        this.colorArray[0] = Color.RED;
        this.colorArray[1] = Color.GREEN;
        this.colorArray[2] = Color.BLUE;
        this.colorArray[3] = Color.YELLOW;
        currentColor = 0;
    }

    private void initFinishedPieces() {
        for (int i = 0; i < piecePerPlayer; i++) {
            this.finishedPieces.put(this.colorArray[i], 0);
        }
    }

    protected void rollDice() {
        this.lastDiceRollResult = dice.roll();
        if (noMovesAvailable()) {
            finishRoll();
        } else {
            setState(new MoveState(this));
        }
    }

    public int roll() {
        notifyObservers();
        gameState.roll();
        return this.lastDiceRollResult;
    }

    protected boolean validateMove(Piece piece) {
        return (colorArray[currentColor].equals(piece.getColor()));
    }

    public void move(Piece piece) {
        gameState.move(piece);
    }

    protected void movePiece(Piece piece) {
        if (this.validateMove(piece)) {
            this.board.movePiece(piece, this.lastDiceRollResult);
            notifyObservers();
            finishMove();
        }
    }

    private void finishMove() {
        gameState.nextState(this);
        tryForPowerupSpawn();
        if (!hasRolledSix()) {
            nextPlayer();
        } else {
            increaseTurnNumber();
        }
    }

    private void finishRoll() {
        increaseTurnNumber();
        nextPlayer();
    }

    protected boolean validateBaseMove(Color color) { //Checks if player rolled 1 or 6 and if base is same color as player and base isn't empty
        return ((colorArray[currentColor].equals(color) && !(board.getBaseFromColor(color).isEmpty()))
                && (this.lastDiceRollResult == 1 || this.lastDiceRollResult == 6));
    }

    public void moveBasePiece(Color color) {
        this.gameState.pieceFromBaseToField(color);
    }

    public void movePieceOutOfBase(Color color) {
        if (this.validateBaseMove(color)) {
            board.pieceFromBaseToField(color, lastDiceRollResult);
            notifyObservers();
            finishMove();
        }
    }

    private void spawnPowerups() {
        this.board.spawnPowerUps();
        notifyObservers();
    }

    protected void nextPlayer() {
        this.currentColor = (this.currentColor + 1) % 4;
        String playerColor = this.colorArray[this.currentColor].toString();
        notifyObservers(playerColor);
    }

    public boolean noMovesAvailable() {
        return (baseMovePossible() && noPiecesLeft());
    }

    private boolean noPiecesLeft() {
        Color c = colorArray[currentColor];
        int pieceAmount = board.getPieceAmount(c);
        return ((this.finishedPieces.get(c) + pieceAmount) == 4);
    }

    private boolean baseMovePossible() {
        return (this.lastDiceRollResult != 1 && this.lastDiceRollResult != 6);
    }

    public boolean hasRolledSix(){
        return this.lastDiceRollResult == 6;
    }

    public void increaseTurnNumber(){
        this.turnNumber++;
    }

    public void tryForPowerupSpawn() {
        if (turnNumber % 8 == 0) {
            spawnPowerups();
        }
    }

    @Override
    public void update() {
        Color c = colorArray[currentColor];
        int increasedFinishedPieces = this.finishedPieces.get(c) + 1;
        this.finishedPieces.replace(c, increasedFinishedPieces);
        if (increasedFinishedPieces == 4) {
            System.out.println(currentColor + "won!"); //Should display a proper victory message
        }
    }

    @Override
    public void addObserver(StringObserver stringObserver) {
        stringObservers.add(stringObserver);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String playerColor) {
        for (StringObserver o : this.stringObservers) {
            o.update(playerColor);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    //setters

    protected void setState(GameState gamestate) {
        this.gameState = gamestate;
    }

}


