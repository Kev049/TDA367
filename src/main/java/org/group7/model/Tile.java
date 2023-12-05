package org.group7.model;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import org.group7.controllers.Observer;
import org.group7.controllers.Observable;

public class Tile implements Observable {

    private int index;
    private Entity entity;

    private List<Observer> observers;

    public Tile(int index) {
        entity = null;
        this.index = index;
        this.observers = new ArrayList<>();
    }

    public Entity getEntity() {
        return entity;
    }

    public void insertEntity(Piece p) {
        if (this.entity != null){
            this.entity.handleCollision(p);
        }
        this.entity = p;
    }

    /* TODO när vi implementerat powerups
    public void insertEntity(PowerUp p) {
        if (this.entity != null){
            //this.entity.handleCollision(p);
        }
        this.entity = p;
    }*/

    public void removeEntity() {
        this.entity = null;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isEmpty() {
        return this.entity == null;
    }

    public Color getEntityColor() {
        if (this.entity instanceof Piece) {
            return ((Piece) this.entity).getColor();
        }
        return null;
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
