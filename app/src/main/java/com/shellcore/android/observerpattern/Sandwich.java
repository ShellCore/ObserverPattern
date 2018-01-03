package com.shellcore.android.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOGC on 03/01/2018.
 */

public class Sandwich implements Subject {

    public boolean isReady;
    private List<Observer> orders = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        orders.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        orders.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : orders) {
            o.update();
        }
    }

    @Override
    public boolean getReady() {
        return isReady;
    }

    @Override
    public void setReady(boolean ready) {
        this.isReady = ready;
    }
}
