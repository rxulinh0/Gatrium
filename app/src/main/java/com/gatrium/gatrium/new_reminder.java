package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class new_reminder extends AppCompatActivity {
    private MaterialCardView food_card,refill_water_card,cat_litter_card,shower_card,walk_card,medicines_card;
    private int pet_id;
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
}
