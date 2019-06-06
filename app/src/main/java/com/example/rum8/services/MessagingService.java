package com.example.rum8.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.rum8.R;
import com.example.rum8.activities.MatchedRoommateProfileActivity;
import com.example.rum8.activities.SettingsActivity;
import com.example.rum8.activities.ViewLinkListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MessagingService extends FirebaseMessagingService {
    public static final String USER_ID_STRING = "passed_user_id";

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            return;
        }

        final Map<String, String> data = remoteMessage.getData();
        final String title = data.get("title");
        final String body = data.get("body");
        final String matchUserId = data.get("newMatchId");

        Log.d("Success", String.format("Message received from: %s", remoteMessage.getFrom()));
        Log.d("Message title", title);
        Log.d("Message body", body);

        sendNotification(title, body, matchUserId);
    }

    private void sendNotification(final String title, final String body, final String matchedUserId) {
        final Intent intent = new Intent(this, MatchedRoommateProfileActivity.class);
        intent.putExtra(USER_ID_STRING, matchedUserId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

        final String channelId = getString(R.string.default_notification_channel_id);
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.logo_no_margin_small)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);

        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "default notification channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
