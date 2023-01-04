package com.saylonn.messenger.FCM;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.saylonn.messenger.MainActivity;
import com.saylonn.messenger.R;

import java.io.Console;

/*

public class FMService extends FirebaseMessagingService {
    public final String TAG = "DEBUG_FCM";
    public final String TOKEN_PREFERENCE_KEY = "fcm_token";
    SharedPreferences sharedPreferences;





    public void getCurrenttoken(Context context){
        
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(!task.isSuccessful()){
                            Log.d(TAG, "read current registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        Log.d(TAG, "current fcm token: " +token);
                        //writeToSharedPref("current_fcm_token", token);
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        sharedPreferences.edit().putString("current_fcm_token", token);
                    }
                });

    }



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message Payload: "+ remoteMessage.getData());

        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }
    @Override
    public void onNewToken(@NonNull String token){
        //super.onNewToken(token);
        Log.d(TAG, "Refreshed Token: "+ token);
        sharedPreferences.edit().putString(TOKEN_PREFERENCE_KEY, token).apply();

    }
    */

    

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "Debug_FCM";
    private static final String TAG1 = "VolleyRequest";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Data Massages are always handled here
        //notification messages are handled here if the app is in the foreground
        //so handle all notifications here
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        if (remoteMessage.getData().size() > 0) {
            //check if data needs to bee processed by long running job if yes use worker Thread (siehe quickstart android fcm beispiele)
            if (true) {
                //here scheduleJob();
                System.out.println("longterm job");
            } else {
                //Handle MEssage within 10 sec
                System.out.println("handleNow()");
            }
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notificationo Body: " + remoteMessage.getNotification().getBody());
            String notificationBody = remoteMessage.getNotification().getBody();
            if (remoteMessage.getNotification().getBody() != null) {
                sendNotification(notificationBody);
            }
        }
    }

    public void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        String channelId = "fcm_default_channel";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setContentTitle("Messenger")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(channelId, "Messenger Channel", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);
        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed Token: " + token);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putString(getString(R.string.token_key), token).apply();
    }

}
