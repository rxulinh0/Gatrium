package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.slider.Slider;

public class pedometer_settings extends AppCompatActivity {
    private MaterialCardView change_number_of_steps_goal_card;
    private boolean change_number_of_steps_goal_card_toggled;
    private Slider steps_goal_slider;
    private RelativeLayout goal_setting_slider_rel_lay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer_settings);
        steps_goal_slider = findViewById(R.id.pedometer_steps_goal_slider);
        change_number_of_steps_goal_card = findViewById(R.id.change_steps_card);
        goal_setting_slider_rel_lay = findViewById(R.id.goal_setting_slider_rel_lay);
        change_number_of_steps_goal_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!change_number_of_steps_goal_card_toggled){
                    change_number_of_steps_goal_card_toggled = true;
                    change_number_of_steps_goal_card.setCardBackgroundColor(getResources().getColor(R.color.main_sky_blue));
                    goal_setting_slider_rel_lay.setVisibility(View.VISIBLE);
                } else{
                    change_number_of_steps_goal_card_toggled = false;
                    change_number_of_steps_goal_card.setCardBackgroundColor(getResources().getColor(R.color.white));
                    goal_setting_slider_rel_lay.setVisibility(View.GONE);
                }
            }
        });
    }
}