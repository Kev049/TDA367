package org.group7.model;

import java.awt.Color;

public class RollState extends GameState {

    public RollState(Game g) {
        super(g);
    }

    @Override
    public void move(Tile t) {

    }

    @Override
    public void pieceFromBaseToField(Color c) {

    }

    @Override
    public void roll() {
        game.rollDice();
        game.setState(new MoveState(game));
    }
}
