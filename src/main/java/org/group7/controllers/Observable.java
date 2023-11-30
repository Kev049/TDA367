package org.group7.controllers;

public interface Observable {
    public void notifyObservers();
    public void addObserver(Observer o);
}
