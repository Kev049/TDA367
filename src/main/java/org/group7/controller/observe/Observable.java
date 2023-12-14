package org.group7.controller.observe;

public interface Observable {
    void addObserver(Observer o);
    void notifyObservers();
}
