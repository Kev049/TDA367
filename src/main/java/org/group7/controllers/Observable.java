package org.group7.controllers;

public interface Observable {
    void notifyObservers(String s);
    void addObserver(Observer o);
}
