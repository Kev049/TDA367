package org.group7.model.game;

import org.group7.controller.observe.Observable;
import org.group7.controller.observe.Observer;
import org.group7.controller.observe.StringObservable;
import org.group7.controller.observe.StringObserver;
import org.group7.model.board.Board;
import org.group7.model.utilities.Dice;
import org.group7.model.board.entities.piece.Piece;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Game implements StringObservable, Observable, Observer {   //TODO ta bort onödiga metoder
    private final Set<StringObserver> stringObservers;
    private final Set<Observer> observers;
    private final Dice dice;
    private final Board board;
    private GameState gameState;
    private HashMap<Color, Integer> finishedPieces;
    private final Color[] colorArray;
    private int currentColor;
    private int lastDiceRollResult;
    private int turnNumber;
    private final int piecePerPlayer = 4;

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
        spawnPowerups();
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

    public void rollDice() {
        this.lastDiceRollResult = dice.roll();
    }

    public int roll() {
        gameState.roll();
        notifyObservers();
        return this.lastDiceRollResult;
    }

    protected boolean validateMove(Piece piece) {
        return (colorArray[currentColor].equals(piece.getColor()));
    }

    public void move(Piece piece) {
        gameState.move(piece);
    }

    protected void movePiece(Piece piece) {
        this.board.movePiece(piece, this.lastDiceRollResult);
        notifyObservers();
    }

    private void finishRound() {
        gameState.nextState(this);
        this.spawnPowerUpsEachSixteenTurns();
        if (!this.hasRolledSix()) {
            this.nextPlayer();
        } else {
            this.increaseTurnNumber();
        }
    }

    protected boolean validateBaseMove(Color color) { //Checks if player rolled 1 or 6 and if base is same color as player and base isn't empty
        return ((colorArray[currentColor].equals(color) && !(board.getBaseFromColor(color).isEmpty()))
                && (this.lastDiceRollResult == 1 || this.lastDiceRollResult == 6));
    }

    public void moveBasePiece(Color color) {
        this.gameState.pieceFromBaseToField(color);
    }

    public void movePieceOutOfBase(Color color) {
        board.pieceFromBaseToField(color, lastDiceRollResult);
        notifyObservers();
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
    public void spawnPowerUpsEachSixteenTurns() {
        if (turnNumber % 16 == 0) {
            spawnPowerups();
        }
    }

    @Override
    public void update() {
        Color c = colorArray[currentColor];
        int increasedFinishedPieces = this.finishedPieces.get(c) + 1;
        this.finishedPieces.replace(c, increasedFinishedPieces);
        if (this.finishedPieces.get(c) == 4) {
            System.out.println(currentColor + "won!");     //TODO change this to proper victory popup
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


