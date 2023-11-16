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

        this.players[0] = new Player("red");
        this.players[1] = new Player("blue");
        this.players[2] = new Player("yellow");
        this.players[3] = new Player("green");

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
}


