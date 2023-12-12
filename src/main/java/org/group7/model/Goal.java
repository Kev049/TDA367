package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

import java.util.ArrayList;
import java.util.List;

public class Goal implements IInsertable, Observable {
    private Piece piece;
    private List<Observer> observers;

    public Goal(){
        this.observers = new ArrayList<>();
    }
    @Override
    public void insertPiece(Piece p) {
        this.piece = p;
        removeEntity();
    }

    @Override
    public void removeEntity() {
        this.piece.setPos(-1);
        this.piece = null;
        System.out.println("goal!");
        // om Goal är Observable så kan vi lägga till at vi räknar pjäser i mål eller liknade här
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
