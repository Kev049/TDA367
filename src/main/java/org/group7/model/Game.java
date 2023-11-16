package org.group7.model;
import java.util.List;

public class Game {

    private Dice dice;
    private Board board;
    private Player[] players;
    private Player currentPlayer;

    public Game() {
        this.dice = Dice.getInstance();
        this.board = new Board();
        this.players = new Player[4];

        for (int i = 0; i < 6; i++) {
            this.currentPlayer = players[i];
            int diceRoll = rollDice();
            List<Piece> currentPieces = players[i].getPieces();
            Piece movingPiece = players[i].choosePiece();
            movingPiece.move_piece(diceRoll);
        }
    }

    public int rollDice() {
        return dice.roll();
    }
}


