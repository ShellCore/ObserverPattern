package com.shellcore.android.observerpattern;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private int notificationId = 1;
    Sandwich sandwich = new Sandwich(false);
    Order order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save)
    public void onClickBtnSave() {
        sandwich.addObserver(order);
        sandwich.setReady(false);
        sendNotification(order.getUpdate());
    }

    @OnClick(R.id.btn_load)
    public void onClickBtnLoad() {
        sandwich.setReady(true);
        sendNotification(order.getUpdate());
    }

    private void sendNotification(String message) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sandwich Builder")
                .setContentText(message);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(notificationId, builder.build());

        notificationId++;
    }
}
