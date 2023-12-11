package org.group7.model;
import java.util.HashSet;
import java.util.Set;

import org.group7.controllers.Observer;
import org.group7.controllers.StringObservable;
import org.group7.controllers.StringObserver;
import java.awt.Color;

public class Game implements StringObservable, Observer {

    private final Set<StringObserver> stringObservers;
    private Dice dice;
    private Board board;
    public Player[] players; //TODO:Byt tillbaka till private
    //private Player[] players;
    private int amountOfPlayers = 4; //TODO:Ändra så att mängden players skickas in från ett annat ställe(menyn)
    private Player currentPlayer;
    private int currentPlayerNumber;
    private int turnNumber;
    private final int turnNumberStart = 0;

    private GameState gameState;
    private Color[] colorArray;
    private int lastDiceRollResult;

    public Game(Board board) { //TODO Game should create the board, not Main
        this.dice = Dice.getInstance();
        this.board = board;
        this.board.addGoalObserver(this);
        this.colorArray = new Color[amountOfPlayers];
        this.colorArray[0] = Color.RED;
        this.colorArray[1] = Color.GREEN;
        this.colorArray[2] = Color.BLUE;
        this.colorArray[3] = Color.YELLOW;
        initPlayers();
        this.turnNumber = turnNumberStart;
        this.lastDiceRollResult = 0;
        this.gameState = new RollState(this); //TODO this should come from the constructor to avoid dependency
        this.stringObservers = new HashSet<>();
        spawnPowerups();
    }

    private void initPlayers(){
        this.players = new Player[amountOfPlayers];
        for( int i = 0; i < this.amountOfPlayers; i++) {
            this.players[i] = PlayerFactory.createPlayer(this.colorArray[i]);
        }
        this.currentPlayerNumber = 0;
        this.currentPlayer = players[currentPlayerNumber];
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

    protected boolean validateMove(Piece piece) {
        //Måste kolla piece color, men tile borde inte arbeta med konkreta pieces.
        return (piece.getColor().equals(currentPlayer.getColor()));
    }

    public void move(Piece piece) {
        gameState.move(piece);
    }

    protected void movePiece(Piece piece) {
        this.board.movePiece(piece, this.lastDiceRollResult);
        setState(this.gameState);
    }

    //TODO: Validate that it is player's turn

    protected boolean validateBaseMove(Color color){
        return (this.currentPlayer.getColor().equals(color) && !(board.getBaseFromColor(color).isEmpty()));
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

    @Override
    public void update(){
        System.out.println(this.currentPlayer.getColor() + "won!");
    }

}


