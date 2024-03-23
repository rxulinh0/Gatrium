package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class new_reminder extends AppCompatActivity {
    private MaterialCardView food_card,refill_water_card,cat_litter_card,shower_card,walk_card,medicines_card;
    private int pet_id,day_index;
    private boolean one_day_reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        food_card = findViewById(R.id.food_reminder_card);
        refill_water_card = findViewById(R.id.refill_water_reminder_card);
        shower_card = findViewById(R.id.shower_reminder_card);
        walk_card = findViewById(R.id.walk_cat_card);
        medicines_card = findViewById(R.id.medicine_card);
        cat_litter_card = findViewById(R.id.change_litter_reminder_card);
        pet_id = getIntent().getIntExtra("pet_id",-1);
        one_day_reminder = getIntent().getBooleanExtra("one_day_reminder",false);
        if(one_day_reminder){
            one_day_reminder_ui_setup();
        }
        food_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_reminder.this,new_reminder_cat_food_question.class);
                intent.putExtra("pet_id",pet_id);
                startActivity(intent);
            }
        });
        refill_water_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_reminder.this,set_reminder_hour.class); // By default value is an daily alarm, so we don't need to request user the days frequency / weeks frequency
                intent.putExtra("days",1); // Data for directly calling to set alarms -> setAlarm() function -> AlarmManager Service
                intent.putExtra("type","CAT_REFILL_WATER"); //...
                intent.putExtra("pet_id",pet_id);
                startActivity(intent);
            }
        });
        cat_litter_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_reminder.this,new_cat_litter_reminder.class);
                intent.putExtra("pet_id",pet_id);
                startActivity(intent);
            }
        });
        shower_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_reminder.this,new_cat_shower_reminder.class);
                intent.putExtra("specie","CAT");
                intent.putExtra("pet_id",pet_id);
                startActivity(intent);
            }
        });
        walk_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_reminder.this,new_reminder_walk.class);
                intent.putExtra("type","CAT_WALK");
                intent.putExtra("pet_id",pet_id);
                startActivity(intent);
            }
        });
        medicines_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_reminder.this,new_reminder_medicines.class);
                intent.putExtra("pet_id",pet_id);
                startActivity(intent);
            }
        });
    }
    public void one_day_reminder_ui_setup(){
        day_index = getIntent().getIntExtra("one_day_index",-1);
        if(day_index==-1){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.error_retrieving_day),Toast.LENGTH_LONG).show();
            finish();
        }
        DayInformation current_day = basic_user_data.getAll_pets().get(pet_id).getCurrentMonthDays().get(day_index);
        TextView food_card_text,refill_water_card_text,change_cat_litter_card_text,shower_card_text,walk_card_text,medicines_card_text;
        food_card_text = findViewById(R.id.cat_food_card_text);
        refill_water_card_text = findViewById(R.id.cat_refill_water_text);
        change_cat_litter_card_text = findViewById(R.id.cat_change_litter_card_text);
        shower_card_text = findViewById(R.id.cat_shower_card_text);
        walk_card_text = findViewById(R.id.cat_walk_card_text);
        medicines_card_text = findViewById(R.id.cat_medicines_card_text);
        for(String iterator : current_day.getDisabledReminders()){
            switch(iterator){
                case "CAT_FOOD":
                    food_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                    food_card_text.setTextColor(getResources().getColor(R.color.main_yellow));
                case "CAT_REFILL_WATER":
                    food_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                    food_card_text.setTextColor(getResources().getColor(R.color.main_yellow));
                case "CAT_CHANGE_LITTER":
                    food_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                    food_card_text.setTextColor(getResources().getColor(R.color.main_yellow));
                case "CAT_SHOWER":
                    food_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                    food_card_text.setTextColor(getResources().getColor(R.color.main_yellow));
                case "CAT_WALK":
                    food_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                    food_card_text.setTextColor(getResources().getColor(R.color.main_yellow));
                case "MEDICINE":
                    food_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                    food_card_text.setTextColor(getResources().getColor(R.color.main_yellow));
            }
        }
    }
}
