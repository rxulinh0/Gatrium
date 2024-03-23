package com.gatrium.gatrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class pedometer_main extends AppCompatActivity implements SensorEventListener, StepListener {
    private int goal_steps;
    private TextView progressMessage;
    private SimpleStepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private int numSteps;
    private CircularProgressIndicator walk_progress_ui;
    private ImageView stop_walk_button,check_button;
    private int currentProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer_main);
        goal_steps = getIntent().getIntExtra("goal_steps",-1);
        if(goal_steps==-1){
            finish();
        }
        walk_progress_ui = findViewById(R.id.walk_progress_ui);
        stop_walk_button = findViewById(R.id.pedometer_stop_walk_button);
        progressMessage = findViewById(R.id.pedometer_walk_progress_message);
        stop_walk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new SimpleStepDetector();
        simpleStepDetector.registerListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        numSteps = 0;
        currentProgress = (numSteps*100)/goal_steps;
        walk_progress_ui.setProgress(currentProgress);
        //textView.setText(TEXT_NUM_STEPS + numSteps);
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        currentProgress = (numSteps*100)/goal_steps;
        walk_progress_ui.setProgress(currentProgress);
    }
    public void setProgressMessage(){
        if(currentProgress < 25){
            progressMessage.setText(R.string.long_way_to_go_keep_going);
            progressMessage.setTextColor(getResources().getColor(R.color.main_sky_blue));
        } else if(currentProgress >= 25 && currentProgress < 50){
            progressMessage.setText(R.string.halfway_there);
            progressMessage.setTextColor(getResources().getColor(R.color.main_sky_blue));
        } else if (currentProgress >= 75){
            progressMessage.setText(R.string.almost_there);
            progressMessage.setTextColor(getResources().getColor(R.color.main_sky_blue));
        } else if(currentProgress == 100){
            progressMessage.setText(R.string.walk_goal_accomplished);
            progressMessage.setTextColor(getResources().getColor(R.color.green_positive));
            changeButton();
        }
    }
    public void changeButton(){
        stop_walk_button.setVisibility(View.GONE);
        check_button = findViewById(R.id.pedometer_check_walk);
        check_button.setVisibility(View.VISIBLE);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}