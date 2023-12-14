package org.group7.controllers;

public interface Observable {
    void addObserver(Observer o);
    void notifyObservers();
}
