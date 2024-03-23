package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class Reminder_alarm extends AppCompatActivity {
    private TextView reminderTitle;
    private ImageView reminderIcon;
    private String REMINDER_TYPE;
    private MaterialCardView ready_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_alarm);
        reminderTitle = findViewById(R.id.reminder_title);
        reminderIcon = findViewById(R.id.reminderIcon);
        Intent intent = getIntent();
        REMINDER_TYPE = intent.getStringExtra("type");
        switch (REMINDER_TYPE) {
            case "CAT_LITTER":
                reminderTitle.setText(R.string.reminder_text_change_litter);
                reminderIcon.setImageResource(R.drawable.ic_sand);
                break;
            case "CAT_SHOWER":
                reminderTitle.setText(R.string.reminder_text_cat_shower);
                reminderIcon.setImageResource(R.drawable.ic_showericon);
                break;
            case "CAT_WALK":
                reminderTitle.setText(R.string.reminder_text_cat_walk);
                reminderIcon.setImageResource(R.drawable.ic_cat_walk);
                break;
            case "CAT_FOOD":
                reminderTitle.setText(R.string.reminder_text_cat_food);
                reminderIcon.setImageResource(R.drawable.ic_catfood);
                break;
            case "CAT_REFILL_WATER":
                reminderTitle.setText(R.string.reminder_text_cat_refill_water);
                reminderIcon.setImageResource(R.drawable.ic_baseline_local_drink_24);
                break;
            case "DOG_SHOWER":
                reminderTitle.setText(R.string.reminder_text_dog_shower);
                reminderIcon.setImageResource(R.drawable.ic_showericon);
                break;
            case "DOG_WALK":
                reminderTitle.setText(R.string.reminder_text_dog_walk);
                reminderIcon.setImageResource(R.drawable.ic_baseline_pets_24);
                break;
            case "DOG_FOOD":
                reminderTitle.setText(R.string.reminder_text_dog_food);
                reminderIcon.setImageResource(R.drawable.ic_dogfood);
                break;
            case "DOG_REFILL_WATER":
                reminderTitle.setText(R.string.reminder_text_dog_refill_water);
                reminderIcon.setImageResource(R.drawable.ic_baseline_local_drink_24);
                break;
            case "MEDICINE":
                String medicine_name = intent.getStringExtra("medicine_name");
                String new_reminder_title = getResources().getString(R.string.reminder_for_medicine) + " " + medicine_name;
                reminderTitle.setText(new_reminder_title);
                reminderIcon.setImageResource(R.drawable.ic_baseline_healing_24);
                break;
        }
        ready_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(REMINDER_TYPE.equals("CAT_WALK")||REMINDER_TYPE.equals("DOG_WALK")){
                    Intent intent = new Intent(Reminder_alarm.this,pedometer_question.class);
                    startActivity(intent);
                }
            }
        });
    }
}