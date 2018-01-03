package com.shellcore.android.observerpattern;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

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

    @OnClick(R.id.btn_text)
    public void onClickBtnText() {
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();

        style.setBigContentTitle("Felicidades, tienes un bocadillo gratis");
        style.setSummaryText("El próximo bocadillo que compres con Shell corre por nuestra cuenta!");
        style.bigText("Como usuario número 1000 de la app, hemos decidido regalarte un bocadillo para la próxima vez que vengas a nuestra tienda. ¡Te esperamos!");

        notificationId = 1;
        sendNotification(style);
    }

    @OnClick(R.id.btn_image)
    public void onClickBtnImage() {
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("Felicidades!!!");
        style.setSummaryText("El próximo bocadillo que compres con Shell corre por nuestra cuenta!");
        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.sandwich));

        notificationId = 2;
        sendNotification(style);
    }

    @OnClick(R.id.btn_list)
    public void onClickBtnList() {
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.setBigContentTitle("Felicidades!!!");
        style.setSummaryText("El próximo bocadillo que compres con Shell corre por nuestra cuenta!");

        String[] list = {"Jamón", "Queso", "Aceitunas", "Atún", "Tomate", "Pimientos", "Cebollas"};
        for (String s : list) {
            style.addLine(s);
        }

        notificationId = 3;
        sendNotification(style);
    }

    // Faltaría la notificación, además de la corrección de los errores
    public void onInflateNotificationClicked() {
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.activity_user_profile);
        sendNotification(notificationLayout);
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

    private void sendNotification(NotificationCompat.Style style) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(style);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(notificationId, builder.build());

        notificationId++;
    }

    private void sendNotification(RemoteViews view) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(view);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(notificationId, builder.build());

        notificationId++;
    }
}
