package com.gatrium.gatrium;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DayOverview extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private ImageView annotationImage;
    private MaterialCardView addImageCard,addDayReminderCard;
    private SimpleDateFormat dateFormat;
    private RecyclerView dayRemindersRecyclerView;
    private int pet_id;
    private ArrayList<Reminder> all_reminders = new ArrayList<>();
    private String date;
    private Date dateFromCalendar;
    private Calendar calendar;
    private int year,month,day_index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_overview);
        addImageCard = findViewById(R.id.addImageToAnnotation);
        addDayReminderCard = findViewById(R.id.addDayReminderButtonCard);
        year = getIntent().getIntExtra("year",-1);
        month = getIntent().getIntExtra("month",-1);
        day_index = getIntent().getIntExtra("day_index",-1);
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day_index);
        dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy" );
        date = dateFormat.format(calendar.getTime()); // Gets the format of the
        pet_id = getIntent().getIntExtra("pet_id",-1);
        addImageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annotationImage = findViewById(R.id.dayAnnotationImageView);
                openGallery();
            }
        });
        dayRemindersRecyclerView = findViewById(R.id.dayRemindersRecyclerView);
        all_reminders = basic_user_data.getAll_pets().get(pet_id).getAllReminders();
        ArrayList<String> disabled_reminders = new ArrayList<>();
        day_index = getIntent().getIntExtra("day_index",-1);
        disabled_reminders = basic_user_data.getAll_pets().get(pet_id).getCurrentMonthDays().get(day_index).getDisabledReminders();
        for(String iterator : disabled_reminders){
            for(Reminder iterator_b : all_reminders){
                if(iterator_b.getReminderId().equals(iterator)){
                    all_reminders.remove(iterator_b);
                    break;
                }
            }
        }
        RemindersRecyclerViewAdapter reminders_recyclerViewAdapter = new RemindersRecyclerViewAdapter();
        reminders_recyclerViewAdapter.setAll_reminders(all_reminders);
        reminders_recyclerViewAdapter.setPet_id(pet_id);
        dayRemindersRecyclerView.setAdapter(reminders_recyclerViewAdapter);
        dayRemindersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Calendar calendar = Calendar.getInstance();
        // calendar.setTime(dateFromCalendar);
        if(day_index==-1){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.error_retrieving_day),Toast.LENGTH_LONG).show();
            finish();
        }
        addDayReminderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createIntentNewReminder();
            }
        });
    }

    private void createIntentNewReminder(){
        Intent new_reminder_intent = new Intent(DayOverview.this,new_reminder.class);
        new_reminder_intent.putExtra("one_day_reminder",true);
        new_reminder_intent.putExtra("one_day_index",day_index);
        startActivity(new_reminder_intent);
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                annotationImage.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}