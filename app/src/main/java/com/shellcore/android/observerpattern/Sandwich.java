package com.shellcore.android.observerpattern;

import java.util.Observable;

/**
 * Created by MOGC on 03/01/2018.
 */

public class Sandwich extends Observable {

    private boolean isReady;


    public Sandwich(boolean b) {
        this.isReady = b;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;

        setChanged();
        notifyObservers();
    }
}
