package org.group7.model;

import java.awt.Color;

public class MoveState extends GameState {

    public MoveState(Game g) {
        super(g);
    }

    @Override
    public void move(Tile tile){
        if (game.validateMove(tile)) { //TODO Can we make movestate only call one function (move) from game instead of validate + move
            game.movePiece(tile);
            game.setState(new RollState(game));
            game.nextPlayer();
        }
    }

    @Override
    public void pieceFromBaseToField(Color color){
        if (game.validateBaseMove(color)){
            game.movePieceOutOfBase(color);
            game.setState(new RollState(game));
            game.nextPlayer();
        }
    }


    @Override
    public void roll(){

    }

    public void changeState(){

    }
}
