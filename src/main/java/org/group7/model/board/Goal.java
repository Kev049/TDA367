package org.group7.model.board;

import org.group7.controller.observe.Observable;
import org.group7.controller.observe.Observer;
import org.group7.model.entities.Entity;
import org.group7.model.entities.piece.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * The Goal class represents the goal tile in the game that the pieces are trying to reach.
 */
public class Goal implements IInsertable, Observable {
    private Entity entity;
    private List<Observer> observers;

    /**
     * The constructor of the Goal class.
     */
    public Goal() {
        this.observers = new ArrayList<>();
    }

    /**
     * Places the provided piece in the goal and then removes it.
     * 
     * @param p The parameter "p" is of type Piece, which represents a game piece that is being
     * inserted.
     */
    @Override
    public void insertPiece(Piece p) {
        this.entity = p;
        removeEntity();
    }

    /**
     * The removeEntity() function sets the position of the piece to -1, sets the piece to null, and
     * notifies observers.
     */
    @Override
    public void removeEntity() {
        this.entity.setPos(-1);
        this.entity = null;
        notifyObservers();
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public boolean isEmpty() {
        return (this.entity == null);
    }

    /**
     * The function notifies all observers by calling their update method.
     */
    @Override
    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update();
        }
    }

    /**
     * The function adds an observer to a list of observers.
     * 
     * @param o The parameter "o" is an instance of the Observer interface that woöö ne added
     * to the list of observers.
     */
    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }
}
