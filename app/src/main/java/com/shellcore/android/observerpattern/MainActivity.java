package com.shellcore.android.observerpattern;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Sandwich sandwich = new Sandwich();
    Observer order = new Order(sandwich);

    int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save)
    public void onClickBtnSave() {
        sandwich.register(order);

        sendNotification(order.update());
    }

    @OnClick(R.id.btn_update)
    public void onClickBtnUpdate() {
        sandwich.setReady(true);
        sendNotification(order.update());
    }

    private void sendNotification(String message) {

        Intent intent = new Intent(this, UserProfileActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(UserProfileActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sandwich Builder")
                .setContentText(message)
                .setAutoCancel(true)
                .setTicker("Los mejores bocadillos de la ciudad")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.sandwich))
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(notificationId, builder.build());

        notificationId++;
    }
}
