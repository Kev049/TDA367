package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class GoalStretch implements Observable {

    private final int capacity = 4;
    //private final int capacity = 5;
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
        //this.tiles[0].insertPiece(p); med nuvarande stilen behövs denna men eeh
        //p.addToGoalStretch();
        goalStretchMove(p, steps);
        p.addToGoalStretch();

        //this.tiles[finishedPieces].insertPiece(p); //TODO change to actual index not dummy checker
        //this.finishedPieces += 1;
        //checkIfFull(); //TODO use observer pattern here to tell Game that a player has won
    }

    public void goalStretchMove(Piece p, int steps) { //TODO clean up this function, only temp to check functionality
        int pos = p.getPos();  // där den står
        int oldPos = pos;
        //oldPos == pos efter att pos += steps så
        //Boolean isNewToGoalStretch = this.tiles[oldPos].isEmpty(); //Används för att kolla om piecen är ny till raksträckan, dock kommer det bli fel om det fanns en annan pjäs där
        boolean isNotNewToGoalStretch = p.isAtGoalStretch();
        pos += steps;  // där den ska
        if (pos == 4) { //om/när den går i mål
            this.finishedPieces++;
            if(isNotNewToGoalStretch){
                p.removeFromGoalStretch();
                this.tiles[oldPos].removePiece();
            }
            p.setPos(-1);
            p = null; //tar bort pjäsen
            System.out.println("goal!");
        }
        //den under funkar inte helt just nu(exempelvis när pjäsen står på pos 2[allstå index 2 av tilesen])
        else if (pos > (8 - oldPos)){ //kollar om den kommer åka ut, om man kommer utifrån med 10 i steps funkar den inte som den ska
            System.out.println("he gonna bounce");
            System.out.println(p.getColor());
            System.out.println(oldPos + " oldpos");
            System.out.println(pos + "pos");
            System.out.println(steps + "steps goin out");
            //this.handler.yeetPieceFromGoal(p);
            if(isNotNewToGoalStretch){ this.tiles[oldPos].removePiece();} //denna removePiece gör så att piece får -1 i pos tror jag
            this.handler.yeetPieceFromGoal(p);
            p.removeFromGoalStretch();
        } else {
            System.out.println(p.getColor());
            System.out.println(oldPos + " oldpos");
            System.out.println(pos + "pos");
            System.out.println(steps + "steps");
            //if (pos > 4){ pos = -pos;} //så att den ska studsa
            pos = pos % 4; //funkar inte om pjäsen börjar på platsen utanför goalStretch
            if(isNotNewToGoalStretch){ this.tiles[oldPos].removePiece();} //görs här i fall att oldPos == pos, skulle kunna bytas mot if check, då skulle rad ovan kunna vara på slutet
            pos = abs(pos);
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


}
