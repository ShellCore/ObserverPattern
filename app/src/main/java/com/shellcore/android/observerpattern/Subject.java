package com.shellcore.android.observerpattern;

/**
 * Created by MOGC on 03/01/2018.
 */

public interface Subject {

    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();

    boolean getReady();
    void setReady(boolean ready);

}
