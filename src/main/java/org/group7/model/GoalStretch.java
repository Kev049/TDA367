package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GoalStretch implements Observable {

    private final int capacity = 4;
    private Tile[] tiles = new Tile[capacity];
    private Color color;
    private int finishedPieces;

    private List<Observer> observers;

    public GoalStretch(Color color) {
        this.color = color;
        initTiles();
        this.finishedPieces = 0;
        this.observers = new ArrayList<>();
    }

    private void initTiles(){
        for (int i = 0; i < capacity; i++) {
            this.tiles[i] = new Tile(i);
        }
    }

    public void addPiece(Piece p, int steps) {

        this.tiles

        this.tiles[finishedPieces].insertPiece(p); //TODO change to actual index not dummy checker
        this.finishedPieces += 1;
        checkIfFull(); //TODO use observer pattern here to tell Game that a player has won
    }

    public void goalStrechMove(Piece p, int steps) {
        int current = p.getPos();
        for (int i = steps; i > 0; i--) {
            current++;
        }
        if (current == 4) {
            this.tiles[current].removePiece();
            this.finishedPieces++;
        }
        else {
            this.tiles[current].insertPiece(p);
        }
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
