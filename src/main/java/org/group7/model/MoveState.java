package org.group7.model;

import java.awt.Color;

public class MoveState extends GameState {

    public MoveState(Game g) {
        super(g);
    }

    @Override
    public void move(Piece piece) {
        if (game.validateMove(piece)) { //TODO Can we make movestate only call one function (move) from game instead of validate + move
            game.movePiece(piece);
            finishRound();
        }
    }

    @Override
    public void pieceFromBaseToField(Color color) {
        if (game.validateBaseMove(color)) {
            game.movePieceOutOfBase(color);
            finishRound();
        }
    }

    @Override
    public void finishRound() {
        game.setState(new RollState(game));
        game.nextPlayer();
        game.endTurn();
    }


    @Override
    public void roll() {

    }
}
