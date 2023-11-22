package org.group7.model;
import java.util.HashSet;
import java.util.Set;
import org.group7.controllers.Observer;

public class Game {

    private final Set<Observer> observers;
    private Dice dice;
    private Board board;
    private Player[] players;
    private int currentPlayer;

    public Game() {
        this.dice = Dice.getInstance();
        this.board = new Board();
        this.players = new Player[4];
        this.bases = this.board.getBases();
        for (int i = 0; i < 4; i++) {
            Piece[] playerPieceArray = new Piece[4];
            for (int j = 0; i < 4; i++) {
                playerPieceArray[j] = this.bases[i].getPieces()[j];
            }
            this.players[i] = new Player(this.bases[i].getColour(), playerPieceArray);
        }



        this.players[0] = new Player(Colour.RED, this.pieces[0]);
        this.players[1] = new Player(Colour.BLUE, this.bases[1]);
        this.players[2] = new Player(Colour.YELLOW, this.bases[2]);
        this.players[3] = new Player(Colour.GREEN, this.bases[3]);

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
        return dice.roll();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public Piece[] getPiecesFromBase(Player player){
        return this.board.getPiecesFromBase(player.getColour());
    }
}


