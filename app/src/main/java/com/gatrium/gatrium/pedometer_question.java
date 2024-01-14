package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class pedometer_question extends AppCompatActivity {
    private MaterialCardView yes_sure,no_thanks,five_k_steps,ten_k_steps;
    private boolean five_k_steps_toggled,ten_k_steps_toggled;
    private boolean listener_a_started,listener_b_started;
    private TextView set_a_goal_text;
    private ImageView arrow_forward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer_question);
        yes_sure = findViewById(R.id.pedometer_yes_sure_card);
        no_thanks = findViewById(R.id.pedometer_no_thanks_card);
        five_k_steps = findViewById(R.id.pedometer_five_thousand_steps_card);
        ten_k_steps = findViewById(R.id.pedometer_ten_thousand_steps_card);
        set_a_goal_text = findViewById(R.id.set_goal_pedometer_text);
        arrow_forward = findViewById(R.id.pedometer_question_arrow_forward);
        yes_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yes_sure.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                set_a_goal_text.setVisibility(View.VISIBLE);
                five_k_steps.setVisibility(View.VISIBLE);
                ten_k_steps.setVisibility(View.VISIBLE);
                arrow_forward.setVisibility(View.VISIBLE);
                if(!listener_a_started) {setCardsEventsListeners();}
                listener_a_started = true;
            }
        });
        no_thanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void setCardsEventsListeners(){
        five_k_steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                five_k_steps_toggled = true;
                ten_k_steps_toggled = false;
                five_k_steps.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                ten_k_steps.setCardBackgroundColor(getResources().getColor(R.color.white));
                if(!listener_b_started){
                    setArrowForwardEventListener();
                }
                listener_b_started = true;
            }
        });
        ten_k_steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                five_k_steps_toggled = false;
                ten_k_steps_toggled = true;
                five_k_steps.setCardBackgroundColor(getResources().getColor(R.color.white));
                ten_k_steps.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                if(!listener_b_started){
                    setArrowForwardEventListener();
                }
                listener_b_started = true;
            }
        });
    }
    public void setArrowForwardEventListener(){
        arrow_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int goal_steps = 0;
                if(five_k_steps_toggled){
                    goal_steps = 5000;
                }
                else if(ten_k_steps_toggled){
                    goal_steps = 10000;
                }
                Intent intent = new Intent(pedometer_question.this,pedometer_main.class);
                intent.putExtra("goal_steps",goal_steps);
                startActivity(intent);
            }
        });
    }
}