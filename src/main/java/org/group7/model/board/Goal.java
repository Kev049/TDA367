package org.group7.model.board;

import org.group7.controller.observe.Observable;
import org.group7.controller.observe.Observer;
import org.group7.model.board.entities.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Goal implements IInsertable, Observable {
    private Piece piece;
    private List<Observer> observers;

    public Goal() {
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
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update();
        }
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }
}
