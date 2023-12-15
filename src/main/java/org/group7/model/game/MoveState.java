package org.group7.model.game;

import org.group7.model.board.entities.piece.Piece;

import java.awt.*;

public class MoveState extends GameState {

    public MoveState(Game g) {
        super(g);
    }

    @Override
    public void move(Piece piece) {
        game.movePiece(piece);
    }

    @Override
    public void pieceFromBaseToField(Color color) {
        game.movePieceOutOfBase(color);
    }


    @Override
    public void roll() {

    }

    @Override
    public void nextState(Game game) {
        game.setState(new RollState(game));
    }
}
