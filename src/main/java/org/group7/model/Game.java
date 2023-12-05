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
    private final int turnNumberStart = 0;

    private int lastDiceRollResult;

    public Game() {
        this.dice = Dice.getInstance();
        this.board = new Board();
        this.players = new Player[4];
        this.currentPlayer = players[0];
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
        //gameloop
        int i = 0;
        while(true) {
            this.currentPlayer = players[i];
            int diceRoll = rollDice();
            List<Piece> currentPlayerPieces = this.currentPlayer.getPieces();
            i++;
            i = (i % 4);
            if (i == 2){ //byt ut mot if (!currentPlayerPieces).isEmpty())
                System.out.println("yeet");
            }
            else {
                break;
            }
        }
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
        return ((!tile.isEmpty()) && tile.getEntityColor().equals(currentPlayer.getColor()));
    }

    public void movePiece(Tile tile) {
        if(validateMove(tile)) {
            this.board.movePiece((Piece) tile.getEntity(), this.lastDiceRollResult);
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
    
}


