package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;

public class new_reminder_cat_food extends AppCompatActivity {
    private TextView title_new_reminder_cat_food;
    private MaterialCardView first_food_card,second_food_card,third_food_card;
    private TimePicker first_food_timePicker,second_food_timePicker,third_food_timePicker;
    private int[] alarmHours;
    private int[] alarmMinutes;
    private int num_foods;
    private ImageView arrow_forward;
    private String specie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder_cat_food);
        first_food_card = findViewById(R.id.first_cat_food_timePicker_card);
        second_food_card = findViewById(R.id.second_cat_food_timePicker_card);
        third_food_card = findViewById(R.id.third_cat_food_timePicker_card);
        first_food_timePicker = findViewById(R.id.first_cat_food_timePicker);
        second_food_timePicker = findViewById(R.id.second_cat_food_timePicker);
        third_food_timePicker = findViewById(R.id.third_cat_food_timePicker);
        arrow_forward = findViewById(R.id.new_reminder_cat_food_arrow_forward);
        Intent current_intent = getIntent();
        num_foods = current_intent.getIntExtra("num_foods",-1);
        specie = getIntent().getStringExtra("specie");
        title_new_reminder_cat_food = findViewById(R.id.title_set_cat_foods_time);
        if(specie!=null&&specie.equals("DOG")){
            title_new_reminder_cat_food.setText(R.string.set_dog_foods_time);
        }
        switch(num_foods){ //Setting UI according to number of foods
            case 1:
                first_food_card.setVisibility(View.VISIBLE);
                second_food_card.setVisibility(View.GONE);
                third_food_card.setVisibility(View.GONE);
                break;
            case 2:
                first_food_card.setVisibility(View.VISIBLE);
                second_food_card.setVisibility(View.VISIBLE);
                third_food_card.setVisibility(View.GONE);
                break;
            case 3:
                first_food_card.setVisibility(View.VISIBLE);
                second_food_card.setVisibility(View.VISIBLE);
                third_food_card.setVisibility(View.VISIBLE);
                break;
            default:
                Toast.makeText(new_reminder_cat_food.this,getResources().getString(R.string.error_did_not_get_num_foods_current_activity),Toast.LENGTH_LONG).show();
                finish();
                break;
        }
        first_food_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_food_card.setStrokeWidth(1);
                second_food_card.setStrokeWidth(0);
                third_food_card.setStrokeWidth(0);
            }
        });
        second_food_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_food_card.setStrokeWidth(0);
                second_food_card.setStrokeWidth(1);
                third_food_card.setStrokeWidth(0);
            }
        });
        third_food_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_food_card.setStrokeWidth(0);
                second_food_card.setStrokeWidth(0);
                third_food_card.setStrokeWidth(1);
            }
        });
        arrow_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarms();
            }
        });
    }
    private void setAlarms(){
        if(num_foods!=-1){
            alarmHours = new int[num_foods];
            alarmMinutes = new int[num_foods];
            if(num_foods>=1){
                alarmHours[0] = first_food_timePicker.getHour();
                alarmMinutes[0] = first_food_timePicker.getMinute();
            }
            if(num_foods>=2){
                alarmHours[1] = second_food_timePicker.getHour();
                alarmMinutes[1] = second_food_timePicker.getMinute();
            }
            if(num_foods==3){
                alarmHours[2] = third_food_timePicker.getHour();
                alarmMinutes[2] = third_food_timePicker.getMinute();
            }
        }
        else{
            Toast.makeText(new_reminder_cat_food.this,getResources().getString(R.string.error_did_not_get_num_foods_current_activity),Toast.LENGTH_LONG).show();
            finish();
        }
        int c = 0;
        for(int hour : alarmHours){
            int minute = alarmMinutes[c];

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.MINUTE,minute);
            calendar.set(Calendar.SECOND, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("type", "CAT_FOOD");

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    hour, // Use a unique request code for each alarm to avoid conflicts
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
            );
            int pet_id = getIntent().getIntExtra("pet_id",-1);
            basic_user_data user = basic_user_data.getInstance();
            Reminder new_reminder = new Reminder(pendingIntent,getApplicationContext(),"CAT_FOOD");
            if(user.setReminderToPet(pet_id,new_reminder));
            else{
                Toast.makeText(new_reminder_cat_food.this,  getResources().getString(R.string.not_able_to_set_reminder),Toast.LENGTH_LONG).show();
                finish();
            }
            // Set the alarm to trigger at the specified time, repeating every day
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            );
        }
    }
}