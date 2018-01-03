package com.shellcore.android.observerpattern;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by MOGC on 03/01/2018.
 */

public class Order implements Observer {

    private String update;

    @Override
    public void update(Observable o, Object arg) {
        Sandwich sandwich = (Sandwich) o;

        if (sandwich.isReady()) {
            sandwich.deleteObserver(this);
            update = "El bocadillo esta listo para ser recogido.";
        } else {
            update = "El bocadillo todavía no está listo. Le queda poco.";
        }
    }

    public String getUpdate() {
        return update;
    }
}
