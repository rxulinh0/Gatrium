package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class new_reminder_medicines extends AppCompatActivity {
    private MaterialCardView hours_interval_card,name_card,arrow_forward_card;
    private TextInputEditText hours_interval_input,name_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder_medicines);
        hours_interval_card = findViewById(R.id.new_reminder_medicines_hours_interval_card);
        name_card = findViewById(R.id.new_reminder_medicines_name_card);
        hours_interval_input = findViewById(R.id.new_reminder_medicines_hours_interval_input);
        name_input = findViewById(R.id.new_reminder_medicines_name_input);
        arrow_forward_card = findViewById(R.id.new_reminder_medicines_arrow_forward_card);
        hours_interval_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hours_interval_card.setBackgroundColor(getResources().getColor(R.color.main_purple));
                name_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
            }
        });
        name_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hours_interval_card.setBackgroundColor(getResources().getColor(R.color.main_orange));
                name_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
            }
        });
        arrow_forward_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarms();
            }
        });
    }
    private void setAlarms() {
        int frequency = 1; // Default frequency is 1 hour
        try {
            frequency = Integer.parseInt(hours_interval_input.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        String medicine_name = Objects.requireNonNull(name_input.getText()).toString();
        int pet_id = getIntent().getIntExtra("pet_id",-1);
        intent.putExtra("medicine_name",medicine_name);
        intent.putExtra("type","MEDICINE");
        intent.putExtra("pet_id",pet_id);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                (int) System.currentTimeMillis(), // Use a unique request code to avoid conflicts
                intent,
                0
        );
        basic_user_data user = basic_user_data.getInstance();
        Reminder new_reminder = new Reminder(pendingIntent,getApplicationContext(),medicine_name);
        if(user.setReminderToPet(pet_id,new_reminder));
        else{
            Toast.makeText(new_reminder_medicines.this,getResources().getString(R.string.not_able_to_set_reminder),Toast.LENGTH_LONG).show();
            finish();
        }
        // Set the alarm to trigger at the current time, repeating every X hours
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_HOUR * frequency,
                pendingIntent
        );

        /*Toast.makeText(
                this,
                "Alarms set with a frequency of " + frequency + " hours.",
                Toast.LENGTH_SHORT
        ).show();*/
    }
}