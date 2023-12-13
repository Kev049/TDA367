package org.group7.model;

import java.awt.Color;

public class RollState extends GameState {

    public RollState(Game g) {
        super(g);
    }

    @Override
    public void move(Piece piece) {

    }

    @Override
    public void pieceFromBaseToField(Color c) {

    }

    @Override
    public void roll() {
        game.rollDice();
        if(game.noMovesAvailable()){
            finishRound();
        }
        else {
            game.setState(new MoveState(game));
        }
    }

    @Override
    public void finishRound() {
        game.nextPlayer();
        game.endTurn();
    }
}
