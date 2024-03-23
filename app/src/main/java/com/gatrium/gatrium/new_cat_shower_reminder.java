package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class new_cat_shower_reminder extends AppCompatActivity {
    private MaterialCardView each_four_weeks_card,each_six_weeks_card,each_two_months_card,other_card;
    private RelativeLayout frequency_rellay;
    private TextView titleTextView;
    private TextInputEditText frequency_days;
    private boolean each_four_weeks_toggle,each_six_weeks_toggle,each_two_months_toggle,other_card_toggle;
    private ImageView arrow_forward;
    private String REMINDER_TYPE = "CAT_SHOWER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cat_shower_reminder);
        each_four_weeks_card = findViewById(R.id.each_four_weeks_card);
        each_six_weeks_card = findViewById(R.id.each_six_weeks_card);
        each_two_months_card = findViewById(R.id.each_two_months_card);
        other_card = findViewById(R.id.other);
        frequency_rellay = findViewById(R.id.frequency_rellay);
        frequency_days = findViewById(R.id.cat_shower_frequency_input);
        arrow_forward = findViewById(R.id.cat_shower_arrow_forward);
        String specie = getIntent().getStringExtra("specie");
        if(specie!=null&&specie.equals("DOG")){
            titleTextView = findViewById(R.id.title_how_frequently_cat_shower);
            titleTextView.setText(R.string.how_frequently_shower_dog);
            REMINDER_TYPE = "DOG_SHOWER";
        }
        each_four_weeks_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                each_four_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                each_six_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_two_months_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                frequency_rellay.setVisibility(View.GONE);
                each_four_weeks_toggle = true;
                each_six_weeks_toggle = false;
                each_two_months_toggle = false;
                other_card_toggle = false;
            }
        });
        each_six_weeks_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                each_four_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_six_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                each_two_months_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                frequency_rellay.setVisibility(View.GONE);
                each_four_weeks_toggle = false;
                each_six_weeks_toggle = true;
                each_two_months_toggle = false;
                other_card_toggle = false;
            }
        });
        each_two_months_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                each_four_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_six_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_two_months_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                frequency_rellay.setVisibility(View.GONE);
                each_four_weeks_toggle = false;
                each_six_weeks_toggle = false;
                each_two_months_toggle = true;
                other_card_toggle = false;
            }
        });
        other_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                each_four_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_six_weeks_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_two_months_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                frequency_rellay.setVisibility(View.VISIBLE);
                each_four_weeks_toggle = false;
                each_six_weeks_toggle = false;
                each_two_months_toggle = false;
                other_card_toggle = true;
            }
        });
        arrow_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pet_id = getIntent().getIntExtra("pet_id",-1);
                if(other_card_toggle){
                    try{
                        int days = Integer.parseInt(frequency_days.getText().toString());
                        Intent intent = new Intent(new_cat_shower_reminder.this,set_reminder_hour.class);
                        intent.putExtra("days",days);
                        intent.putExtra("type",REMINDER_TYPE);
                        intent.putExtra("pet_id",pet_id);
                        startActivity(intent);
                    } catch(NumberFormatException e){
                        Toast.makeText(new_cat_shower_reminder.this,getResources().getString(R.string.please_enter_a_valid_number_days),Toast.LENGTH_LONG).show();
                    }
                } else {
                    Intent intent = new Intent(new_cat_shower_reminder.this,set_reminder_hour.class);
                    intent.putExtra("type",REMINDER_TYPE);
                    intent.putExtra("pet_id",pet_id);
                    if(each_four_weeks_toggle){
                        int days = 28;
                        intent.putExtra("days",days);
                    }
                    else if(each_six_weeks_toggle){
                        int days = 42;
                        intent.putExtra("days",days);
                    }
                    else if(each_two_months_toggle){
                        int days = 62;
                        intent.putExtra("days",days);
                    }
                    startActivity(intent);
                }
            }
        });
    }
}