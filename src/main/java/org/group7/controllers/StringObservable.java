package org.group7.controllers;

public interface StringObservable {
    void notifyObservers(String s);

    void addObserver(StringObserver o);
}
