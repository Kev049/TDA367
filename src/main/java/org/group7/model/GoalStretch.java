package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class GoalStretch implements Observable {

    private final int capacity = 4;
    private Tile[] tiles = new Tile[capacity];
    private Color color;
    private int finishedPieces;

    private IMoveHandler handler;

    private List<Observer> observers;

    public GoalStretch(Color color, IMoveHandler handler) {
        this.color = color;
        initTiles();
        this.finishedPieces = 0;
        this.observers = new ArrayList<>();
        this.handler = handler;
    }

    private void initTiles(){
        for (int i = 0; i < capacity; i++) {
            this.tiles[i] = new Tile(i);
        }
    }

    public void addPiece(Piece p, int steps) {
        p.setPos(0);
        //this.tiles[0].insertPiece(p);
        p.addToGoalStretch();
        goalStretchMove(p, steps);

        //this.tiles[finishedPieces].insertPiece(p); //TODO change to actual index not dummy checker
        //this.finishedPieces += 1;
        //checkIfFull(); //TODO use observer pattern here to tell Game that a player has won
    }

    public void goalStretchMove(Piece p, int steps) { //TODO clean up this function, only temp to check functionality
        int pos = p.getPos();  // där den står
        int oldPos = pos;
        pos += steps;  // där den ska
        if (pos > (4 + oldPos)){ //kollar om den kommer åka ut
            this.handler.yeetPieceFromGoal(p);
            this.tiles[oldPos].removePiece();
            p.removeFromGoalStretch();
        } else {
            if (pos > 4){ pos = -pos;} //så att den ska studsa
            pos = pos % 4;
            this.tiles[oldPos].removePiece(); //görs här i fall att oldPos == pos, skulle kunna bytas mot if check
            this.tiles[abs(pos)].insertPiece(p);
        }
            p.setPos(pos);
    }

    private void bounce(Piece p){

    }

    public void removePiece(int index){ //har ändrat removeEntity så har kanske pajat denna, removeEntity returnade en entity innan
        this.tiles[index].removePiece();
    }

    public Color getColor(){
        return this.color;
    }

    public Tile[] getTiles(){
        return this.tiles;
    }

    private void checkIfFull(){
        if (finishedPieces == capacity) {
            notifyObservers();
        }
    }

    @Override
    public void notifyObservers(){
        for (Observer o: this.observers){
            o.update();
        }
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }


}
