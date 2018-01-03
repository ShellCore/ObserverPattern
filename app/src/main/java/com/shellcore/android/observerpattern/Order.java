package com.shellcore.android.observerpattern;

/**
 * Created by MOGC on 03/01/2018.
 */

public class Order implements Observer {

    private Subject subject = null;

    public Order(Subject subject) {
        this.subject = subject;
    }


    @Override
    public String update() {
        if (subject.getReady()) {
            subject.unregister(this);
            return "Tu bocadillo está listo para ser recogido.";
        } else {
            return "Tu bocadillo está cocinándose. Estará listo muy pronto.";
        }
    }
}
