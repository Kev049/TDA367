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

public class Game implements StringObservable, Observable, Observer {

    private final Dice dice;
    private final Board board;
    private GameState gameState;
    private final Color[] colorArray;
    private int currentColorIndex;
    private int lastDiceRollResult;
    private int turnNumber;
    private final int piecePerPlayer = 4;
    private final Set<StringObserver> stringObservers;
    private final Set<Observer> observers;
    private final HashMap<Color, Integer> finishedPieces;

    public Game() {
        this.dice = new Dice();
        this.colorArray = new Color[4];
        initColors();
        this.board = new Board(this.colorArray);
        this.board.addGoalObserver(this);
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
        currentColorIndex = 0;
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
        return (colorArray[currentColorIndex].equals(piece.getColor()));
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
        return ((colorArray[currentColorIndex].equals(color) && !(board.getBaseFromColor(color).isEmpty()))
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
        this.currentColorIndex = (this.currentColorIndex + 1) % 4;
        String playerColor = this.colorArray[this.currentColorIndex].toString();
        notifyObservers(playerColor);
    }

    public boolean noMovesAvailable() {
        return (baseMovePossible() && noPiecesLeft());
    }

    private boolean noPiecesLeft() {
        Color c = colorArray[currentColorIndex];
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
        if (turnNumber % 6 == 0) {
            spawnPowerups();
        }
    }

    @Override
    public void update() {
        Color c = colorArray[currentColorIndex];
        int increasedFinishedPieces = this.finishedPieces.get(c) + 1;
        this.finishedPieces.replace(c, increasedFinishedPieces);
        if (increasedFinishedPieces == 4) {
            System.out.println(currentColorIndex + "won!");
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

    //Setters & getters

    protected void setState(GameState gamestate) {
        this.gameState = gamestate;
    }

    public Board getBoard(){
        return this.board;
    }
}


