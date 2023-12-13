package org.group7.model;

import org.group7.controllers.Observable;
import org.group7.controllers.Observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Goal implements IInsertable, Observable {
//public class Goal implements IInsertable, Observable {
    private Piece piece;
    private Color color;
    private List<Observer> observers;

    public Goal(){
        this.observers = new ArrayList<>();
    }

    @Override
    public void insertPiece(Piece p) {
        this.piece = p;
        this.color = piece.getColor();
        removeEntity();
    }

    @Override
    public void removeEntity() {
        this.piece.setPos(-1);
        this.piece = null;
        notifyObservers();
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
