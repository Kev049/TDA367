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
        goalStretchMove(p, steps);
        p.addToGoalStretch();

        //this.tiles[finishedPieces].insertPiece(p); //TODO change to actual index not dummy checker
        //this.finishedPieces += 1;
        //checkIfFull(); //TODO use observer pattern here to tell Game that a player has won
    }

    public void goalStretchMove(Piece p, int steps) { //TODO clean up this function, only temp to check functionality
        int current = p.getPos();
        int from = current;     // där den står
        System.out.println(current);
        current += steps;       // där den ska
        System.out.println(current);
        if(current == 4) {      // Om i målet
            this.finishedPieces++;  // lägg till att en är klar
            System.out.println(finishedPieces);
            checkIfFull();      // kolla om alla är klara
        }
        else {                  // Om inte i målet
            if (current > 4){
                current = (8 - current); //Bounce logic
            }
            if (current < 0) {
                //TODO Move out to field again, possibly with a handler?
                this.handler.yeetPieceFromGoal(p);
                this.tiles[from].removeEntity();
                p.removeFromGoalStretch();
            }
            else if (current != from){
                this.tiles[current].insertPiece(p);
                this.tiles[from].removeEntity();
            }
            else if (current == from) {
                this.tiles[current].insertPiece(p);
            }
            p.setPos(current);
        }

    }

    public void removePiece(int index){ //har ändrat removeEntity så har kanske pajat denna, removeEntity returnade en entity innan
        this.tiles[index].removeEntity();
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
