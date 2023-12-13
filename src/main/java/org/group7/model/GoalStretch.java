package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class GoalStretch implements Observable, IMoveHandler {

    private final int capacity = 4;
    private final int insertCapacity = capacity + 1;
    private final Tile[] tiles = new Tile[capacity];                //TODO varför finns både Tile och Insertable?
    private final IInsertable[] insertables = new IInsertable[insertCapacity];
    private final Color color;
    private int finishedPieces;

    private final PieceExtractor handler;

    private final List<Observer> observers;

    public GoalStretch(Color color, PieceExtractor handler) {
        this.color = color;
        initTiles();
        initInsertables();
        this.finishedPieces = 0;
        this.observers = new ArrayList<>();
        this.handler = handler;
    }

    private void initInsertables(){
        System.arraycopy(this.tiles, 0, this.insertables, 0, capacity);
        this.insertables[4] = new Goal();
        //insertables.add(4, new Goal());
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
        this.insertables[entryTileIndex].insertPiece(p);
        goalStretchMove(p, 0);

        //this.tiles[finishedPieces].insertPiece(p); //TODO change to actual index not dummy checker
        //this.finishedPieces += 1;
        //checkIfFull(); //TODO use observer pattern here to tell Game that a player has won
    }

    public void goalStretchMove(Piece p, int steps) { //TODO clean up this function, only temp to check functionality
        int pos = p.getPos();  // där den står
        int oldPos = pos;
        boolean isNotNewToGoalStretch = p.isAtGoalStretch();    // TODO varför finns detta? vad är poängen?
        pos += steps;  // där den ska
        pos = 4 - abs((pos - 4));
        if(isNotNewToGoalStretch){ removeEntity(oldPos);}
        if (pos < 0){ //pjäsen studsar ut
            this.handler.yeetPieceFromGoal(p);
            p.removeFromGoalStretch();
        } else { //pjäsen hamnar antingen på målrutan eller på någon av tilesen i "goalstretch"
            p.setPos(pos);
            this.insertables[pos].insertPiece(p);
            //lägga till en if check eller liknande som lägger till/räknar antalet pjäser som går i mål?
        }
    }

    public void removeEntity(int index){ //har ändrat removeEntity så har kanske pajat denna, removeEntity returnade en entity innan
        this.insertables[index].removeEntity();
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
