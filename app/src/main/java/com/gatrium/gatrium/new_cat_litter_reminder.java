package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class new_cat_litter_reminder extends AppCompatActivity {
    MaterialCardView weekly_card,every_two_weeks_card,other_card;
    RelativeLayout frequency_rellay;
    TextInputEditText frequency_days;
    boolean frequency_days_toggled,weekly_card_toggled,every_two_weeks_card_toggled;
    ImageView arrow_forward;
    String REMINDER_TYPE = "CAT_LITTER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cat_litter_reminder);
        weekly_card = findViewById(R.id.weekly_card);
        every_two_weeks_card = findViewById(R.id.every_two_weeks_card);
        other_card = findViewById(R.id.other);
        frequency_rellay = findViewById(R.id.frequency_rellay);
        frequency_days = findViewById(R.id.frequency_input);
        arrow_forward = findViewById(R.id.arrow_forward);
        weekly_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                every_two_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                weekly_card_toggled = true;
                every_two_weeks_card_toggled = false;
                frequency_days_toggled = false;
                frequency_rellay.setVisibility(View.GONE);
            }
        });
        every_two_weeks_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                every_two_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                weekly_card_toggled = false;
                every_two_weeks_card_toggled = true;
                frequency_days_toggled = false;
                frequency_rellay.setVisibility(View.GONE);
            }
        });
        other_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                every_two_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                weekly_card_toggled = false;
                every_two_weeks_card_toggled = false;
                frequency_days_toggled = true;
                frequency_rellay.setVisibility(View.VISIBLE);
            }
        });
        arrow_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int pet_id = getIntent().getIntExtra("pet_id",-1);
                if(frequency_days_toggled){
                    try{
                        int days = Integer.parseInt(frequency_days.getText().toString());
                        Intent intent = new Intent(new_cat_litter_reminder.this,set_reminder_hour.class);
                        intent.putExtra("days",days);
                        intent.putExtra("type",REMINDER_TYPE);
                        intent.putExtra("pet_id",pet_id);
                        startActivity(intent);
                    } catch(NumberFormatException e){
                        Toast.makeText(new_cat_litter_reminder.this,"Please enter a valid number (days)",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    if(weekly_card_toggled){
                        int days = 7;
                        Intent intent = new Intent(new_cat_litter_reminder.this,set_reminder_hour.class);
                        intent.putExtra("days",days);
                        intent.putExtra("type",REMINDER_TYPE);
                        intent.putExtra("pet_id",pet_id);
                        startActivity(intent);
                    }
                    else if(every_two_weeks_card_toggled){
                        int days = 14;
                        Intent intent = new Intent(new_cat_litter_reminder.this,set_reminder_hour.class);
                        intent.putExtra("days",days);
                        intent.putExtra("type",REMINDER_TYPE);
                        intent.putExtra("pet_id",pet_id);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}