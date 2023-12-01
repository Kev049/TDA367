package org.group7.model;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.group7.controllers.Observer;
import java.awt.Color;

public class Game {

    private final Set<Observer> observers;
    private Dice dice;
    private Board board;
    public Player[] players; //TODO:Byt tillbaka till private
    //private Player[] players;
    private int currentPlayer;
    private int turnNumber;
    private final int turnNumberStart = 0;

    private int lastDiceRollResult;

    public Game() {
        this.dice = Dice.getInstance();
        this.board = new Board();
        this.players = new Player[4];
        this.currentPlayer = 0;
        this.turnNumber = turnNumberStart;
        this.lastDiceRollResult = 0;

        this.players[0] = PlayerFactory.createPlayer(Color.RED);
        this.players[1] = PlayerFactory.createPlayer(Color.GREEN);
        this.players[2] = PlayerFactory.createPlayer(Color.YELLOW);
        this.players[3] = PlayerFactory.createPlayer(Color.BLUE);

//        for (int i = 0; i < 4; i++) {
//            Piece[] playerPieceArray = new Piece[4];
//            for (int j = 0; i < 4; i++) {
//                playerPieceArray[j] = this.bases[i].getPieces()[j];
//            }
//            this.players[i] = new Player(this.bases[i].getColour(), playerPieceArray);
//        }


        this.observers = new HashSet<>();
//        while(true) {
//            this.currentPlayer = players[i];
//            int diceRoll = rollDice();
//            List<Piece> currentPieces = players[i].getPieces();
//            Piece movingPiece = players[i].choosePiece();
//            movingPiece.move_piece(diceRoll);
//            i++;
//            i = (i % 4);
//        }
    }

    public int rollDice() {
        for (Observer o : observers){
            o.update();
        }
        this.lastDiceRollResult = dice.roll();
        return this.lastDiceRollResult;
    }

    public boolean validateMove(Tile tile) {
        //Måste kolla piece color, men tile borde inte arbeta med konkreta pieces.
        return ((!tile.isEmpty()) && tile.getEntityColor().equals(players[currentPlayer].getColor()));
    }

    public void movePiece(Tile tile) {
        if (validateMove(tile)) {
            this.board.movePiece(tile.getIndex(),this.lastDiceRollResult);
        }
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



}


