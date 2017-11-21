package com.example.enter_01.vfin.firebase.fcm;

/**
 * Created by kosian on 6/20/2016.
 */


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.bumptech.glide.Glide;
import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.component.main.MainActivity;
import com.example.enter_01.vfin.utility.Contextor;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.concurrent.ExecutionException;


public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String txt = remoteMessage.getData().get("title");
        String detail = remoteMessage.getData().get("body");
        String imageProduct = remoteMessage.getData().get("imageProduct");
        showNoti(txt,detail,imageProduct);
    }



    private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.logo_v_fin : com.example.enter_01.vfin.R
                .drawable
                .logo_v_fin;
    }


    private void showNoti(String title, String detail,String imageProduct) {

        Intent notificationIntent = null;

            notificationIntent = new Intent(this, MainActivity.class);
           // notificationIntent.putExtra("", link);



        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Bitmap bitmap = null;
        try {
             bitmap = Glide.with(Contextor.getInstance().getContext())
                    .asBitmap()
                    .load(imageProduct)
                    .submit()
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try

        {
            Notification notification = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                notification = builder.setContentTitle(title + "")
                        .setContentText(detail)
                        .setDefaults(Notification.DEFAULT_ALL) // requires VIBRATE permission
            /*
             * Sets the big view "big text" style and supplies the
             * text (the user's reminder Message) that will be displayed
             * in the detail area of the expanded notification.
             * These calls are ignored by the support library for
             * pre-4.1 devices.
             */.setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(detail))
                        .setTicker("แจ้งเตือนใหม่")
                        .setAutoCancel(true)
                        .setColor(this.getResources().getColor(com.example.enter_01.vfin.R.color.colorPrimary))
                        .setSmallIcon(getNotificationIcon())
                        .setLargeIcon(bitmap)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(bitmap))
                        .setContentIntent(pendingIntent).build();
            }

            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);
        }catch (Exception e){}



    }

}
