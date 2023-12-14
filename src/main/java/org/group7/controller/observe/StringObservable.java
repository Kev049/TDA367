package org.group7.controller.observe;

public interface StringObservable {
    void notifyObservers(String s);

    void addObserver(StringObserver o);
}
