package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class new_reminder_cat_food_question extends AppCompatActivity {
    private TextView title_new_reminder_cat_food_question;
    private MaterialCardView once_a_day_card,twice_a_day_card,thrice_a_day_card;
    private boolean once_a_day_card_toggled,twice_a_day_card_toggled,thrice_a_day_card_toggled;
    private ImageView arrow_forward;
    private String specie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder_cat_food_question);
        once_a_day_card = findViewById(R.id.once_a_day_card);
        twice_a_day_card = findViewById(R.id.twice_a_day_card);
        thrice_a_day_card = findViewById(R.id.thrice_a_day_card);
        arrow_forward = findViewById(R.id.new_reminder_cat_food_question_arrow_forward);
        specie = getIntent().getStringExtra("specie");
        title_new_reminder_cat_food_question = findViewById(R.id.title_new_reminder_cat_food_question);
        if(specie!=null&&specie.equals("DOG")){
            title_new_reminder_cat_food_question.setText(R.string.how_many_times_do_you_fed_your_dog);
        }
        once_a_day_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                once_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                twice_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                thrice_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                once_a_day_card_toggled = true;
                twice_a_day_card_toggled = false;
                thrice_a_day_card_toggled = false;
            }
        });
        twice_a_day_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                once_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                twice_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                thrice_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                once_a_day_card_toggled = false;
                twice_a_day_card_toggled = true;
                thrice_a_day_card_toggled = false;
            }
        });
        thrice_a_day_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                once_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                twice_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                thrice_a_day_card.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
                once_a_day_card_toggled = false;
                twice_a_day_card_toggled = false;
                thrice_a_day_card_toggled = true;
            }
        });
        arrow_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pet_id = getIntent().getIntExtra("pet_id",-1);
                if(once_a_day_card_toggled){
                    Intent intent = new Intent(new_reminder_cat_food_question.this,new_reminder_cat_food.class);
                    intent.putExtra("pet_id",pet_id);
                    intent.putExtra("num_foods",1);
                    if(specie!=null){
                        intent.putExtra("specie",specie);
                    }
                    startActivity(intent);
                }
                else if(twice_a_day_card_toggled){
                    Intent intent = new Intent(new_reminder_cat_food_question.this,new_reminder_cat_food.class);
                    intent.putExtra("pet_id",pet_id);
                    intent.putExtra("num_foods",2);
                    if(specie!=null){
                        intent.putExtra("specie",specie);
                    }
                    startActivity(intent);
                }
                else if(thrice_a_day_card_toggled){
                    Intent intent = new Intent(new_reminder_cat_food_question.this,new_reminder_cat_food.class);
                    intent.putExtra("pet_id",pet_id);
                    intent.putExtra("num_foods",3);
                    if(specie!=null){
                        intent.putExtra("specie",specie);
                    }
                    startActivity(intent);
                }
            }
        });
    }
}