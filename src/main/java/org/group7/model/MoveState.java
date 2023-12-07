package org.group7.model;

public class MoveState extends GameState {

    public MoveState(Game g) {
        super(g);
        System.out.println("Now in Move state");
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
    public void roll(){

    }

    public void changeState(){

    }
}
