package com.gatrium.gatrium;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Reminder { // Basic information to access reminders in AlarmManager & NotificationManager, also works for displaying information in UI
    public PendingIntent reminderPendingIntent;
    public Context mContext; //TODO: Include hours and minutes so it can display in UI
    public String reminder_id;
    public Reminder(PendingIntent reminderPendingIntent,Context mContext,String reminder_id) {
        this.reminderPendingIntent = reminderPendingIntent;
        this.mContext = mContext;
        this.reminder_id = reminder_id;
    }
    public PendingIntent getReminderPendingIntent() {
        return reminderPendingIntent;
    }
    public void setReminderPendingIntent(PendingIntent reminderPendingIntent) {
        this.reminderPendingIntent = reminderPendingIntent;
    }
    public void setReminderId(String reminder_id){
        this.reminder_id = reminder_id;
    }
    public String getReminderId(){
        return reminder_id;
    }
    public void removeThisReminder(){ // Reminder function for removing a Reminder --> This is why we need a Context (mContext)
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(mContext.ALARM_SERVICE);
        Intent myIntent = new Intent(mContext,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                mContext,1,myIntent,0
        );
        alarmManager.cancel(pendingIntent);
    }
}
