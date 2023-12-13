package org.group7.model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.group7.controllers.Observer;
import org.group7.controllers.StringObservable;
import org.group7.controllers.StringObserver;
import java.awt.Color;

public class Game implements StringObservable, Observer {   //TODO ta bort onödiga metoder

    private final Set<StringObserver> stringObservers;
    private final Dice dice;
    private final Board board;
    public Player[] players; //TODO:Byt tillbaka till private
    //private Player[] players;
    private int amountOfPlayers = 4; //TODO:Ändra så att mängden players skickas in från ett annat ställe(menyn)
    private Player currentPlayer;
    private int currentPlayerNumber;
    private int turnNumber;
    private HashMap<Color, Integer> finishedPieces;

    private GameState gameState;
    private final Color[] colorArray; //TODO varför finns denna?
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
        this.turnNumber = 0;
        this.lastDiceRollResult = 0;
        this.gameState = new RollState(this); //TODO this should come from the constructor to avoid dependency
        this.stringObservers = new HashSet<>();
        this.finishedPieces = new HashMap<>();
        initFinishedPieces();
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

    private void initFinishedPieces(){
        for (int i = 0; i < 4; i++){
            this.finishedPieces.put(this.colorArray[i], 0);
        }
    }

    public void rollDice() {
        this.lastDiceRollResult = dice.roll();
    }

    public int roll(){
        gameState.roll();
        return this.lastDiceRollResult;
    }

    protected boolean validateMove(Piece piece) {
        return (piece.getColor().equals(currentPlayer.getColor()));
    }

    public void move(Piece piece) {
        gameState.move(piece);
    }

    protected void movePiece(Piece piece) {
        this.board.movePiece(piece, this.lastDiceRollResult);
        //setState(this.gameState);
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
    }   //Onödig?


    public int getLastDiceRollResult() {    //TODO antagligen ta bort?
        return lastDiceRollResult;
    }

    private void spawnPowerups(){
        //TODO: Implementera något som spawnar olika powerups beroende på hur långt in i matchen vi kommit
        this.board.spawnPowerUp();

    }

    protected void setState(GameState gamestate){
        this.gameState = gamestate;
    }

    protected void nextPlayer(){
        this.currentPlayerNumber = (this.currentPlayerNumber + 1) % 4;
        this.currentPlayer = this.players[currentPlayerNumber];
        String playerColor = this.currentPlayer.getColor().toString();
        notifyObservers(playerColor);
    }

    @Override
    public void update(){
        Color c = currentPlayer.getColor();
        this.finishedPieces.replace(c, this.finishedPieces.get(c) + 1);
        if (this.finishedPieces.get(c) == 4){
            System.out.println(c + "won!");
        }
    }

    public boolean noMovesAvailable(){      //Checks if the current player has any pieces on the board
        Color c = this.currentPlayer.getColor();
        Piece[] P = this.getPiecesFromBase(currentPlayer);
        int basePieces = P.length;
        return ((this.lastDiceRollResult != 1 && this.lastDiceRollResult != 6) && ((this.finishedPieces.get(c) + basePieces) == 4));
        //return ((((getPiecesFromBase(currentPlayer).length) + finishedPieces.get(currentPlayer.getColor())) == 4) && (lastDiceRollResult != (1 | 6)));
    } //TODO make it so Goal notifies Game of a new finished piece so this is actually usable
}


