package org.group7.model;

public class RollState extends GameState {

    public RollState(Game g) {
        super(g);
        System.out.println("Now in RollState!");
    }

    @Override
    public void move(Tile t) {

    }

    @Override
    public void roll() {
        game.rollDice();
        game.setState(new MoveState(game));
        System.out.println("Changing to Move state");
    }
}
