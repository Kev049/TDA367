package org.group7.model;

public class Game {

    private Dice dice;
    private Board board;
    private Player[] players;

    public Game() {
        this.dice = Dice.getInstance();
        this.board = new Board();
        this.players = new Player[4];
    }

    public int rollDice() {
        return dice.roll();
    }
}
