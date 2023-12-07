package org.group7.model;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.group7.controllers.Observer;
import java.awt.Color;
import java.util.List;

public class Game {

    private final Set<Observer> observers;
    private Dice dice;
    private Board board;
    public Player[] players; //TODO:Byt tillbaka till private
    //private Player[] players;
    private Player currentPlayer;
    private int turnNumber;
    private int amountOfPlayers; //TODO:Ändra så att mängden players ställs in på ett annat ställe
    private final int turnNumberStart = 0;

    private Color[] colorArray;

    private int lastDiceRollResult;

    public Game(Board board) { //TODO Game should create the board, not Main
        this.dice = Dice.getInstance();
        this.board = board;
        this.players = new Player[amountOfPlayers];
        this.currentPlayer = players[0];
        this.turnNumber = turnNumberStart;
        this.lastDiceRollResult = 0;
        this.colorArray = new Color[amountOfPlayers];
        this.colorArray[0] = Color.RED;
        this.colorArray[1] = Color.GREEN;
        this.colorArray[2] = Color.BLUE;
        this.colorArray[3] = Color.YELLOW;
        //Om vi har tid kan vi implementera en metod för att välja antalet spelare och
        //vilka som skall initieras. Isf, 3 spelare funkar precis som 4 fast loopa en gång mindre.
        //2 spelare: Välj mod 2 (i) av colors i colorArray när vi initierar spelare, kom ihåg att
        //se till att player 1 platsen inte sätts ut, blir nog jobbigt.
        initPlayers();

//        for (int i = 0; i < 4; i++) {
//            Piece[] playerPieceArray = new Piece[4];
//            for (int j = 0; i < 4; i++) {
//                playerPieceArray[j] = this.bases[i].getPieces()[j];
//            }
//            this.players[i] = new Player(this.bases[i].getColour(), playerPieceArray);
//        }

        this.observers = new HashSet<>();

        //gameloop
        int i = 0;
        while(true) {
            this.currentPlayer = players[i];
            int diceRoll = rollDice();
            List<Piece> currentPlayerPieces = this.currentPlayer.getPieces();
            i++;
            i = (i % 4);
            if (i == 2){
                System.out.println("yeet");
            }
            else{
                break;
            }
        }
    }

    private void initPlayers(){
        for( int i = 0; i < this.amountOfPlayers - 1; i++){
            this.players[i] = PlayerFactory.createPlayer(this.colorArray[i]);
        }
    }

    public int rollDice() {         //TODO implementera så att en state bestämmer vad som händer. RollState - rulla tärning, MoveState - gör inget (man ska flytta pjäs)
        for (Observer o : observers){
            o.update();
        }
        this.lastDiceRollResult = dice.roll();
        return this.lastDiceRollResult;
    }

    public boolean validateMove(Tile tile) {
        //Måste kolla piece color, men tile borde inte arbeta med konkreta pieces.
        return ((!tile.isEmpty()) && tile.getPieceColor().equals(currentPlayer.getColor()));
    }

    public void movePiece(Tile tile){
        if(validateMove(tile)) {
            Piece p = tile.getPiece();
            this.board.movePiece(p, this.lastDiceRollResult);
        }
    }

    //TODO: Validate that it is player's turn
    public void movePieceOutOfBase(){
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
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

}


