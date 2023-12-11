package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class GoalStretch implements Observable {

    private final int capacity = 4;
    private final int insertCapacity = 5;
    private Tile[] tiles = new Tile[capacity];
    private IInsertable[] insertables = new IInsertable[insertCapacity];
    private Color color;
    private int finishedPieces;

    private IMoveHandler handler;

    private List<Observer> observers;

    public GoalStretch(Color color, IMoveHandler handler) {
        this.color = color;
        initTiles();
        initInsertables();
        this.finishedPieces = 0;
        this.observers = new ArrayList<>();
        this.handler = handler;
    }

    private void initInsertables(){
        for (int j=0; j < capacity; j++){
            insertables[j] = tiles[j];
        }
        insertables[4] = new Goal();
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
    }

    public void goalStretchMove(Piece p, int steps) { //TODO clean up this function, only temp to check functionality
        int pos = p.getPos();  // där den står
        int oldPos = pos;
        boolean isNotNewToGoalStretch = p.isAtGoalStretch();
        pos += steps;  // där den ska
        pos = 4 - abs((pos - 4));
        if(isNotNewToGoalStretch){ removePiece(oldPos);}
//        if (pos == 4) { //om/när den går i mål
//            this.finishedPieces++;
//            if(isNotNewToGoalStretch){
//                p.removeFromGoalStretch();
//                removePiece(oldPos);
//            }
//            p.setPos(pos); //kan bytas ut mot p.setPos(-1) beroende på om "speedboosts" inne i rakstreckan ska finnas (om pos kan bli mer än 10)
//            p = null; //tar bort pjäsen
//            System.out.println("goal!");
//        }
        /*else*/ if (pos < 0){
            this.handler.yeetPieceFromGoal(p);
            p.removeFromGoalStretch();
        } else {
            p.setPos(pos);
            this.tiles[pos].insertPiece(p);
        }
    }

    public void removePiece(int index){ //har ändrat removeEntity så har kanske pajat denna, removeEntity returnade en entity innan
        this.insertables[index].removePiece();
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
