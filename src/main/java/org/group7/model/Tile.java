package org.group7.model;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import org.group7.controllers.Observer;
import org.group7.controllers.Observable;

public class Tile implements Observable {

    private final int index;
    private Piece piece;

    private List<Observer> observers;

    public Tile(int index) {
        this.piece = null;
        this.index = index;
        this.observers = new ArrayList<>();
    }

    public Piece getPiece() {
        return piece;
    }

    public void insertPiece(Piece p) {
        if(this.piece != null){
            this.piece.handleCollision(p);
        }
        this.piece = p;
        this.piece.setPos(index);
    }

    /* TODO när vi implementerat powerups
    public void insertEntity(PowerUp p) {
        if (this.entity != null){
            //this.entity.handleCollision(p);
        }
        this.entity = p;
    }*/

    public void removePiece() {
        this.piece.setPos(-1);
        this.piece = null;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public Color getPieceColor() {
        return this.piece.getColor();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update(this.index);
        }
    }
}
