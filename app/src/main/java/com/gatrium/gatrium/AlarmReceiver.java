package com.gatrium.gatrium;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "alarm_channel";
    private static final int NOTIFICATION_ID = 1;
    private String REMINDER_TYPE;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent = new Intent(context, Reminder_alarm.class);
        REMINDER_TYPE = intent.getStringExtra("type");
        newIntent.putExtra("type", REMINDER_TYPE);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Create a notification to alert the user
        createNotification(context, newIntent);
    }
    // When the alarm triggers, this method is called

    // Create an Intent to start the PopupActivity
        /*Intent popupIntent = new Intent(context, Reminder_alarm.class);
        popupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String REMINDER_TYPE = intent.getStringExtra("type");
        popupIntent.putExtra("type",REMINDER_TYPE);
        // Create a PendingIntent for the notification
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                ALARM_REQUEST_CODE,
                popupIntent,
                PendingIntent.FLAG_ONE_SHOT
        );

        // Build the notification
        String contentTitle = context.getResources().getString(R.string.gatrium_notification);
        String contentText = context.getResources().getString(R.string.tap_notification_reminder);
        switch (REMINDER_TYPE) {
            case "CAT_LITTER":
                contentTitle = context.getResources().getString(R.string.reminder_cat_litter);
                break;
            case "CAT_REFILL_WATER":
                contentTitle = context.getResources().getString(R.string.reminder_refill_water_cat);
                break;
            case "DOG_REFILL_WATER":
                contentTitle = context.getResources().getString(R.string.reminder_refill_water_dog);
                break;
            case "CAT_SHOWER":
                contentTitle = context.getResources().getString(R.string.reminder_shower_cat);
                break;
            case "DOG_SHOWER":
                contentTitle = context.getResources().getString(R.string.reminder_shower_dog);
                break;
            case "CAT_WALK":
                contentTitle = context.getResources().getString(R.string.reminder_cat_walk);
                break;
            case "DOG_WALK":
                contentTitle = context.getResources().getString(R.string.reminder_dog_walk);
                break;
            case "CAT_FOOD":
                contentTitle = context.getResources().getString(R.string.reminder_food_cat);
                break;
            case "DOG_FOOD":
                contentTitle = context.getResources().getString(R.string.reminder_food_dog);
                break;
            default:
                break;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "PopupChannel")
                .setSmallIcon(R.mipmap.roundicon_transparent)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true); // Dismiss the notification when tapped

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(ALARM_REQUEST_CODE, builder.build());}
    }*/
    private void createNotification(Context context, Intent intent) {
        // Create a notification channel (required for Android 8.0 and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Alarm Channel";
            String description = "Channel for alarm notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        String contentTitle = context.getResources().getString(R.string.gatrium_notification);
        String contentText = context.getResources().getString(R.string.tap_notification_reminder);
        switch (REMINDER_TYPE) {
            case "CAT_LITTER":
                contentTitle = context.getResources().getString(R.string.reminder_cat_litter);
                break;
            case "CAT_REFILL_WATER":
                contentTitle = context.getResources().getString(R.string.reminder_refill_water_cat);
                break;
            case "DOG_REFILL_WATER":
                contentTitle = context.getResources().getString(R.string.reminder_refill_water_dog);
                break;
            case "CAT_SHOWER":
                contentTitle = context.getResources().getString(R.string.reminder_shower_cat);
                break;
            case "DOG_SHOWER":
                contentTitle = context.getResources().getString(R.string.reminder_shower_dog);
                break;
            case "CAT_WALK":
                contentTitle = context.getResources().getString(R.string.reminder_cat_walk);
                break;
            case "DOG_WALK":
                contentTitle = context.getResources().getString(R.string.reminder_dog_walk);
                break;
            case "CAT_FOOD":
                contentTitle = context.getResources().getString(R.string.reminder_food_cat);
                break;
            case "DOG_FOOD":
                contentTitle = context.getResources().getString(R.string.reminder_food_dog);
                break;
            default:
                break;
        }
        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT))
                .setAutoCancel(true);

        // Show the notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

/*import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//import java.util.Calendar; -> enable if want to use days of the week and interact with the calendar --> Not for alpha-01 version

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String REMINDER_TYPE = intent.getStringExtra("type");
        Intent intent_b = new Intent(context,Reminder_alarm.class);
        intent_b.putExtra("type",REMINDER_TYPE);
        context.startActivity(intent_b);
    }

    function to get current day of week --> Not useful at the moment
    private String getDayOfWeek(int day) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return daysOfWeek[day - 1];
    }
}*/
}