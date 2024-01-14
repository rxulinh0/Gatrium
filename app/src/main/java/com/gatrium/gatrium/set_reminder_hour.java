package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;

public class set_reminder_hour extends AppCompatActivity {
    private TimePicker timePicker;
    private MaterialCardView setReminderCard;
    private int REMINDER_INTERVAL_DAYS;
    private String REMINDER_TYPE;
    private final int ALARM_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder_hour);

        timePicker = findViewById(R.id.timePicker);
        setReminderCard = findViewById(R.id.ready_set_reminder_hour_card);
        Intent intent = getIntent();
        REMINDER_INTERVAL_DAYS = intent.getIntExtra("days",-1);
        REMINDER_TYPE = intent.getStringExtra("type");
        createNotificationChannel();
        setReminderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(REMINDER_INTERVAL_DAYS!=-1){
                    Toast.makeText(set_reminder_hour.this,"Alarm submitted --> for test purposes",Toast.LENGTH_LONG).show();
                    setAlarm();
                    Intent intentMain = new Intent(set_reminder_hour.this,MainActivity.class);
                    startActivity(intentMain);
                }
            }
        });
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Popup Channel";
            String description = "Channel for Popup Notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel("PopupChannel", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void setAlarm() {
        // Get the selected hour and minute from the TimePicker
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        // Create a Calendar object to set the alarm time
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // Create an Intent to trigger the AlarmReceiver
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("type",REMINDER_TYPE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        int pet_id = getIntent().getIntExtra("pet_id",-1);
        basic_user_data user = basic_user_data.getInstance();
        Reminder new_reminder = new Reminder(pendingIntent,getApplicationContext(),REMINDER_TYPE);
        if(user.setReminderToPet(pet_id,new_reminder));
        else{
            Toast.makeText(set_reminder_hour.this,getResources().getString(R.string.not_able_to_set_reminder),Toast.LENGTH_LONG).show();
            finish();
        }
        // Get the AlarmManager service
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Set the alarm to repeat every day at the specified time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(this, "Alarm set for " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
    }
    /*private void setAlarm() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("type",REMINDER_TYPE);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        // Set the alarm to trigger at the specified time, repeating every two weeks
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY * REMINDER_INTERVAL_DAYS,
                pendingIntent
        );

        // Toast.makeText(this, "Alarm set for " + hour + ":" + minute + " every two weeks on selected days.", Toast.LENGTH_SHORT).show();
    }*/
}