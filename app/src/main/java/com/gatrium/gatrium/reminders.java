package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class reminders extends AppCompatActivity {
    private RecyclerView reminders_recyclerview;
    private basic_user_data user;
    private int pet_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        user = basic_user_data.getInstance();
        pet_id = getIntent().getIntExtra("pet_id",-1);
        reminders_recyclerview = findViewById(R.id.recyclerViewYourReminders);
        ArrayList<Reminder> all_reminders = new ArrayList<>();
        all_reminders = user.getAll_pets().get(pet_id).getAllReminders();
        RemindersRecyclerViewAdapter reminders_recyclerViewAdapter = new RemindersRecyclerViewAdapter();
        reminders_recyclerViewAdapter.setAll_reminders(all_reminders);
        reminders_recyclerViewAdapter.setPet_id(pet_id);
        reminders_recyclerview.setAdapter(reminders_recyclerViewAdapter);
        reminders_recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}