package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class getting_started_one_pet extends AppCompatActivity {
    private MaterialCardView cat_select,dog_select,ready_button,getowner_name_card;
    private boolean fromHomeFragmentCarousel = false;
    private boolean toggle_specie = false, isToggle_specie = false; // true(1) for cat <-> false(0) for dog
    private basic_user_data User;
    private boolean ready_button_toggled = false;
    private TextInputEditText pet_name_input,owner_name_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started_one_pet);
        cat_select = findViewById(R.id.is_cat_card);
        dog_select = findViewById(R.id.is_dog_card);
        pet_name_input = findViewById(R.id.edtTextPetName);
        owner_name_input = findViewById(R.id.edtTextOwnerName);
        ready_button = findViewById(R.id.ready_card);
        User = basic_user_data.getInstance();
        fromHomeFragmentCarousel = getIntent().getBooleanExtra("fromHomeFragmentCarousel",false);
        getowner_name_card = findViewById(R.id.card_get_owner_name);
        if(fromHomeFragmentCarousel){
            getowner_name_card.setVisibility(View.GONE);
        }
        cat_select.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                toggle_specie = true;
                isToggle_specie = true;
                cat_select.setCardBackgroundColor(getResources().getColor(R.color.main_purple)); //Changing Material CardView color to look like a RadioButton
                dog_select.setCardBackgroundColor(getResources().getColor(R.color.main_sky_blue));
            }
        });
        dog_select.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                toggle_specie = false;
                isToggle_specie = true;
                cat_select.setCardBackgroundColor(getResources().getColor(R.color.main_sky_blue));
                dog_select.setCardBackgroundColor(getResources().getColor(R.color.main_purple));
            }
        });
        ready_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ready_button_toggled);
                else if(fromHomeFragmentCarousel){
                    String pet_name = pet_name_input.getText().toString();
                    if(!pet_name.isEmpty()&&isToggle_specie){
                        saveData(pet_name,toggle_specie);
                        Intent intent = new Intent(getting_started_one_pet.this,MainActivity.class);
                        ready_button_toggled = true;
                        startActivity(intent);
                    }
                } else{
                    String pet_name = pet_name_input.getText().toString();
                    String owner_name = owner_name_input.getText().toString();
                    if(!pet_name.isEmpty()&&!owner_name.isEmpty()&&isToggle_specie){
                        saveData(pet_name,owner_name,toggle_specie);
                        Intent intent = new Intent(getting_started_one_pet.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    public void saveData(String pet_name,String owner_name,boolean dog_or_cat){
        basic_user_data userInstance = basic_user_data.getInstance();
        userInstance.setUser_name(owner_name);
        Pet_Data currentPet = new Pet_Data(pet_name,dog_or_cat,false);
        userInstance.addPet(currentPet);
        userInstance.refreshAddButtonArrayPositionForCarousel();
    }
    public void saveData(String pet_name,boolean dog_or_cat){
        basic_user_data userInstance = basic_user_data.getInstance();
        Pet_Data currentPet = new Pet_Data(pet_name,dog_or_cat,false);
        userInstance.addPet(currentPet);
        userInstance.refreshAddButtonArrayPositionForCarousel();
    }
}