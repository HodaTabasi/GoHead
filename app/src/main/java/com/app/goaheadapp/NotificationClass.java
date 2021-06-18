package com.app.goaheadapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.app.goaheadapp.activitys.Splash;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class NotificationClass {
    RemoteMessage notification;
    Context context;
   NotificationClass notificationClass;


    public NotificationClass(RemoteMessage remoteMessage, Context applicationContext) {
        this.notification = remoteMessage;
        this.context = applicationContext;
        notificationClass = new NotificationClass();
    }

    public NotificationClass() {
    }

    private void sendOreoNotification(RemoteMessage order) {
        String body = "new Notification";
        String title = "GoAHead";
        String id ;

//        if (order.getData().get("msg_from") != null){
//            body = order.getData().get("message");
//            title = order.getData().get("new Message");
//            id = order.getData().get("order_id");
//        } else {
//            body = getMessageBody(order.getData().get("type"),order.getData().get("target"));
//            title = order.getData().get("new Notification");
//            id = order.getData().get("id");
//        }

//        Paper.book().write("id",Integer.parseInt(id));
//        Paper.book().write("flag",true);


        int j = (int) System.currentTimeMillis();

        Intent intent = new Intent(context, Splash.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);


//        intent.putExtra("id", Integer.parseInt(id));
//        intent.putExtra("flag", true);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, j, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        OreoNotification oreoNotification = new OreoNotification(context);
        NotificationCompat.Builder builder = oreoNotification.builder(title, body, pendingIntent, defaultSound, R.drawable.ic_logo);


        final int min = 10;
        final int max = 800;
        final int random = new Random().nextInt((max - min) + 1) + min;

        oreoNotification.getNotificationManager().notify(random, builder.build());
    }

    private void sendNotification(RemoteMessage order) {

        String body = "new Notification";
        String title = "GoAHead";
        String id ;

//        if (order.getData().get("msg_from") != null){
//             body = order.getData().get("message");
//             title = context.getString(R.string.n_mesg);
//             id = order.getData().get("order_id");
//        } else {
//            body = getMessageBody(order.getData().get("type"),order.getData().get("target"));
//            title = context.getString(R.string.n_notif);
//            id = order.getData().get("id");
//        }

//        Paper.book().write("id",Integer.parseInt(id));
//        Paper.book().write("flag",true);

        int j = (int) System.currentTimeMillis();

        Intent intent = new Intent(context, Splash.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);


//        intent.putExtra("id", Integer.parseInt(id));
//        intent.putExtra("flag", true);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, j, intent, PendingIntent.FLAG_ONE_SHOT);

//        Uri uri = Uri.parse("android.resource://"+context.getPackageName()+"/" + R.raw.pristine);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.tawseel.driverapp";

        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logo)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(Notification.PRIORITY_MAX);

        final int min = 10;
        final int max = 800;
        final int random = new Random().nextInt((max - min) + 1) + min;

        notificationManager.notify(random, notificationBuilder.build());
    }
//
//    private String getMessageBody(String status , String target) {
//        switch (status) {
//            case "deliver-cancel":
//                return context.getString(R.string.new_bill)+" "+ target;
//            case "new-order":
//                return context.getString(R.string.deliver_order)+" "+ target;
//            case "deliver-confirm":
//                return context.getString(R.string.confirm_deliver)+" "+ target;
//            case "use-add-rate":
//                return context.getString(R.string.user_add_rate)+" "+ target;
//            case "approve-bill":
//                return context.getString(R.string.approve_bill)+" "+ target;
//            case "cancel-bill":
//                return context.getString(R.string.cancel_bill) +" "+ target;
//            default:
//                return target;
//        }
//    }

    public NotificationClass getNotificationClass() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sendOreoNotification(notification);
        } else
            sendNotification(notification);

        return notificationClass;
    }
}
