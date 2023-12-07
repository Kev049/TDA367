package org.group7.model;
import java.util.HashSet;
import java.util.Set;

import org.group7.controllers.Observer;
import org.group7.controllers.StringObservable;
import org.group7.controllers.StringObserver;
import java.awt.Color;

public class Game implements Observable {

    private final Set<StringObserver> stringObservers;
    private Dice dice;
    private Board board;
    public Player[] players; //TODO:Byt tillbaka till private
    //private Player[] players;
    private Player currentPlayer;
    private int currentPlayerNumber;
    private int turnNumber;
    private final int turnNumberStart = 0;
    private GameState gameState;


    private int lastDiceRollResult;

    public Game(Board board) { //TODO Game should create the board, not Main
        this.dice = Dice.getInstance();
        this.board = board;
        this.board.addGoalObserver(this);
        this.players = new Player[4];
        this.players[0] = PlayerFactory.createPlayer(Color.RED);
        this.players[1] = PlayerFactory.createPlayer(Color.GREEN);
        this.players[2] = PlayerFactory.createPlayer(Color.BLUE);
        this.players[3] = PlayerFactory.createPlayer(Color.YELLOW);
        this.currentPlayerNumber = 0;
        this.currentPlayer = players[currentPlayerNumber];
        this.turnNumber = turnNumberStart;
        this.lastDiceRollResult = 0;
        this.gameState = new RollState(this); //TODO this should come from the constructor to avoid dependency
        this.stringObservers = new HashSet<>();

    }

    public int rollDice() {         //TODO implementera så att en state bestämmer vad som händer. RollState - rulla tärning, MoveState - gör inget (man ska flytta pjäs)
        //for (Observer o : observers){
        //    o.update();           //TODO ksks lägga till igen
        //}
        this.lastDiceRollResult = dice.roll();
        return this.lastDiceRollResult;
    }

    public int roll(){
        gameState.roll();
        return this.lastDiceRollResult;
    }

    protected boolean validateMove(Tile tile) {
        //Måste kolla piece color, men tile borde inte arbeta med konkreta pieces.
        return ((!tile.isEmpty()) && tile.getPieceColor().equals(currentPlayer.getColor()));
    }

    public void move(Tile tile) {
        gameState.move(tile);
    }

    protected void movePiece(Tile tile) {
        //if(validateMove(tile)) {
        Piece p = tile.getPiece();
        this.board.movePiece(p, this.lastDiceRollResult);
        setState(this.gameState);
        //}
    }

    //TODO: Validate that it is player's turn

    protected boolean validateBaseMove(Color color){
        return (this.currentPlayer.getColor().equals(color));
    }
    public void moveBasePiece(Color color){
            this.gameState.pieceFromBaseToField(color);
    }

    public void movePieceOutOfBase(Color color){
        board.pieceFromBaseToField(color);
    }

    @Override
    public void addObserver(StringObserver stringObserver) {
        stringObservers.add(stringObserver);
    }

    @Override
    public void notifyObservers(String playerColor) {
        for (StringObserver o: this.stringObservers) {
            o.update(playerColor);
        }
    }

    public Piece[] getPiecesFromBase(Player player){
        return this.board.getPiecesFromBase(player.getColor());
    }

    /* public void placePowerups() { // Where should this be implemented? Should we create a new class?

    }*/
    public int getLastDiceRollResult() {
        return lastDiceRollResult;
    }

    private void spawnPowerups(){
        //TODO: Implementera något som spawnar olika powerups beroende på hur långt in i matchen vi kommit
        this.board.spawnPowerUp();
    }

    public void setState(GameState gamestate){
        this.gameState = gamestate;
    }

    public void nextPlayer(){
        this.currentPlayerNumber = (this.currentPlayerNumber + 1) % 4;
        this.currentPlayer = this.players[currentPlayerNumber];
        String playerColor = this.currentPlayer.getColor().toString();
        notifyObservers(playerColor);
    }


}


