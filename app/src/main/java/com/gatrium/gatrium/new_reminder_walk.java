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

public class new_reminder_walk extends AppCompatActivity {
    private MaterialCardView daily_card,each_three_days_card,weekly_card,each_fourteen_days_card,monthly_card,other_card;
    private boolean daily_card_toggled,each_three_days_card_toggled,weekly_card_toggled,each_fourteen_days_card_toggled, monthly_card_toggled, other_card_toggled;
    private TextView titleTextView;
    private ImageView arrow_forward;
    private RelativeLayout other_relative_layout;
    private TextInputEditText other_days_input;
    private String REMINDER_TYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder_walk);
        daily_card = findViewById(R.id.new_reminder_walk_daily_card);
        each_three_days_card = findViewById(R.id.new_reminder_walk_each_three_days_card);
        weekly_card = findViewById(R.id.new_reminder_walk_weekly_days_card);
        each_fourteen_days_card = findViewById(R.id.new_reminder_walk_each_fourteen_days_card);
        monthly_card = findViewById(R.id.new_reminder_walk_monthly_card);
        other_relative_layout = findViewById(R.id.new_reminder_walk_other_rel_lay);
        arrow_forward = findViewById(R.id.new_reminder_walk_arrow_forward);
        other_card = findViewById(R.id.new_reminder_walk_other_days_card);
        other_days_input = findViewById(R.id.new_reminder_walk_other_input);
        titleTextView = findViewById(R.id.title_new_reminder_walk_question);
        Intent intent = getIntent();
        REMINDER_TYPE = intent.getStringExtra("type");
        if(REMINDER_TYPE.equals("CAT_WALK")){
            titleTextView.setText(getResources().getString(R.string.how_frequently_do_you_walk_with_your_cat));
        }
        daily_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daily_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                each_three_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_fourteen_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                monthly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                daily_card_toggled = true;
                each_three_days_card_toggled = false;
                weekly_card_toggled = false;
                each_fourteen_days_card_toggled = false;
                monthly_card_toggled = false;
                other_card_toggled = false;
                other_relative_layout.setVisibility(View.GONE);
            }
        });
        each_three_days_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daily_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_three_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_fourteen_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                monthly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                daily_card_toggled = false;
                each_three_days_card_toggled = true;
                weekly_card_toggled = false;
                each_fourteen_days_card_toggled = false;
                monthly_card_toggled = false;
                other_card_toggled = false;
                other_relative_layout.setVisibility(View.GONE);
            }
        });
        weekly_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daily_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_three_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                each_fourteen_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                monthly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                daily_card_toggled = false;
                each_three_days_card_toggled = false;
                weekly_card_toggled = true;
                each_fourteen_days_card_toggled = false;
                monthly_card_toggled = false;
                other_card_toggled = false;
                other_relative_layout.setVisibility(View.GONE);
            }
        });
        each_fourteen_days_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daily_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_three_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_fourteen_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                monthly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                daily_card_toggled = false;
                each_three_days_card_toggled = false;
                weekly_card_toggled = false;
                each_fourteen_days_card_toggled = true;
                monthly_card_toggled = false;
                other_card_toggled = false;
                other_relative_layout.setVisibility(View.GONE);
            }
        });
        monthly_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daily_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_three_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_fourteen_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                monthly_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                daily_card_toggled = false;
                each_three_days_card_toggled = false;
                weekly_card_toggled = false;
                each_fourteen_days_card_toggled = false;
                monthly_card_toggled = true;
                other_card_toggled = false;
                other_relative_layout.setVisibility(View.GONE);
            }
        });
        other_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daily_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_three_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                weekly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                each_fourteen_days_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                monthly_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                other_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                daily_card_toggled = false;
                each_three_days_card_toggled = false;
                weekly_card_toggled = false;
                each_fourteen_days_card_toggled = false;
                monthly_card_toggled = false;
                other_card_toggled = true;
                other_relative_layout.setVisibility(View.VISIBLE);
            }
        });
        arrow_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)   {
                int pet_id = getIntent().getIntExtra("pet_id",-1);
                if(daily_card_toggled){
                    Intent new_intent = new Intent(new_reminder_walk.this,set_reminder_hour.class);
                    new_intent.putExtra("type",REMINDER_TYPE);
                    new_intent.putExtra("days",1);
                    new_intent.putExtra("pet_id",pet_id);
                }
                else if(each_three_days_card_toggled){
                    Intent new_intent = new Intent(new_reminder_walk.this,set_reminder_hour.class);
                    new_intent.putExtra("type",REMINDER_TYPE);
                    new_intent.putExtra("days",3);
                    new_intent.putExtra("pet_id",pet_id);
                }
                else if(weekly_card_toggled){
                    Intent new_intent = new Intent(new_reminder_walk.this,set_reminder_hour.class);
                    new_intent.putExtra("type",REMINDER_TYPE);
                    new_intent.putExtra("days",7);
                    new_intent.putExtra("pet_id",pet_id);
                }
                else if(each_fourteen_days_card_toggled){
                    Intent new_intent = new Intent(new_reminder_walk.this,set_reminder_hour.class);
                    new_intent.putExtra("type",REMINDER_TYPE);
                    new_intent.putExtra("days",14);
                    new_intent.putExtra("pet_id",pet_id);
                }
                else if(monthly_card_toggled){
                    Intent new_intent = new Intent(new_reminder_walk.this,set_reminder_hour.class);
                    new_intent.putExtra("type",REMINDER_TYPE);
                    new_intent.putExtra("days",30);
                    new_intent.putExtra("pet_id",pet_id);
                }
                else if(other_card_toggled) {
                    Intent new_intent = new Intent(new_reminder_walk.this,set_reminder_hour.class);
                    new_intent.putExtra("type",REMINDER_TYPE);
                    new_intent.putExtra("pet_id",pet_id);
                    try{
                        int days = Integer.parseInt(other_days_input.getText().toString());
                        new_intent.putExtra("days",days);
                        startActivity(new_intent);
                    } catch(NumberFormatException e){
                        Toast.makeText(new_reminder_walk.this,getResources().getString(R.string.error_did_not_get_walk_frequency).toString(),Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });
    }
}