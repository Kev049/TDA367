package org.group7.model;

public class Game {

    private Dice dice;

    public Game(){
        this.dice = Dice.getInstance();
    }

    public int rollDice() {
        return dice.roll();
    }
}
