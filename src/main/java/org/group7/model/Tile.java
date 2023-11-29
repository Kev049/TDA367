package org.group7.model;

import java.awt.Color;

import java.awt.*;
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

    public void insertEntity(Entity e) {
        if (this.entity != null){
            notifyObservers();
        }
        this.entity = e;
    }

    public Entity removeEntity() {
        Entity e = this.entity;
        this.entity = null;
        return e;
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
