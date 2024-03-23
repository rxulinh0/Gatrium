package com.gatrium.gatrium;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.ArrayList;


public class getting_started_b extends AppCompatActivity {
    private int num_pets;
    private ImageView arrow;
    private boolean just_one_toggled = false;
    private MaterialCardView just_one_card,more_than_one_card;
    private RelativeLayout more_than_one_card_relative_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started_b);
        String[] items = getResources().getStringArray(R.array.num_possible_pets);
        // Create an ArrayAdapter using the string array and a default layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        arrow = findViewById(R.id.get_started_arrow);
        just_one_card = findViewById(R.id.just_one_card);
        more_than_one_card = findViewById(R.id.more_than_one_card);
        more_than_one_card_relative_layout = findViewById(R.id.spinnerRelativeLayout_more_than_one);
        just_one_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                just_one_toggled = true;
                just_one_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                more_than_one_card.setCardBackgroundColor(getResources().getColor(R.color.white));
                more_than_one_card_relative_layout.setVisibility(View.GONE);
                num_pets = 1;
            }
        });
        more_than_one_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                just_one_toggled = false;
                just_one_card.setCardBackgroundColor(getResources().getColor(R.color.white));
                more_than_one_card.setCardBackgroundColor(getResources().getColor(R.color.main_orange));
                more_than_one_card_relative_layout.setVisibility(View.VISIBLE);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                num_pets = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(just_one_toggled){
                    basic_user_data user = basic_user_data.getInstance();
                    user.setNum_pets(1);
                    Intent intent = new Intent(getting_started_b.this, getting_started_one_pet.class);
                    startActivity(intent);
                }
            }
        });
    }
}