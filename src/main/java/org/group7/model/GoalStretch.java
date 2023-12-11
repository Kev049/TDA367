package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class GoalStretch implements Observable, IMoveHandler {

    private final int capacity = 4;
    private Tile[] tiles = new Tile[capacity];
    private Color color;
    private int finishedPieces;

    private PieceExtractor handler;

    private List<Observer> observers;

    public GoalStretch(Color color, PieceExtractor handler) {
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

    @Override
    public void addPiece(Piece p, int index) {
        int entryTileIndex = 4 - abs(index - 4);
        p.setPos(entryTileIndex);
        p.addToGoalStretch();
        if (entryTileIndex < 4) {
            this.tiles[entryTileIndex].insertPiece(p);
            goalStretchMove(p, 0);
        } else {
            System.out.println("GOAAGALGLAGLALLLLLL");
        }

        //this.tiles[finishedPieces].insertPiece(p); //TODO change to actual index not dummy checker
        //this.finishedPieces += 1;
        //checkIfFull(); //TODO use observer pattern here to tell Game that a player has won
    }

    public void goalStretchMove(Piece p, int steps) { //TODO clean up this function, only temp to check functionality
        int pos = p.getPos();  // där den står
        int oldPos = pos;
        boolean isNotNewToGoalStretch = p.isAtGoalStretch();
        pos += steps;  // där den ska
        pos = 4 - abs((pos - 4));
        if (pos == 4) { //om/när den går i mål
            this.finishedPieces++;
            if(isNotNewToGoalStretch){
                p.removeFromGoalStretch();
                this.tiles[oldPos].removePiece();
            }
            p.setPos(pos); //kan bytas ut mot p.setPos(-1) beroende på om "speedboosts" inne i rakstreckan ska finnas (om pos kan bli mer än 10)
            p = null; //tar bort pjäsen
            System.out.println("goal!");
        }
        else if (pos < 0){
            if(isNotNewToGoalStretch){ this.tiles[oldPos].removePiece();}
            this.handler.yeetPieceFromGoal(p);      //Antagligen inte optimalt
            p.removeFromGoalStretch();
        } else {
            if(isNotNewToGoalStretch){ this.tiles[oldPos].removePiece();}
            this.tiles[pos].insertPiece(p);
            p.setPos(pos);
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

    @Override
    public void returnPieceToBase(Piece p) {
        //VERY TEMPORARY
    }


}
