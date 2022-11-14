package com.example.myapplication;


import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final int DEFAULT_HOUR = 19;
    private final int DEFAULT_MINUTE = 0;

    // Internship
    private final Time INTERNSHIP_TIME = new Time(6, 0);
    private final Time INTERNSHIP_BREAK = new Time(0, 15);

    // Full time
    private final Time FULL_TIME = new Time(8, 48);
    private final Time FULL_TIME_BREAK = new Time(1, 12);

    // Left area
    private RadioButton internshipTimeRadioButton;
    private RadioButton fullTimeRadioButton;

    // Right Area
    private RadioButton startTimeRadioButton;
    private RadioButton endTimeRadioButton;

    // Time pickers
    private TimePicker startTimePicker;
    private TimePicker endTimePicker;

    // Clear button
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Left area
        internshipTimeRadioButton = findViewById(R.id.internshipTimeRadioButton);
        internshipTimeRadioButton.setChecked(false);

        fullTimeRadioButton = findViewById(R.id.fullTimeRadioButton);
        fullTimeRadioButton.setChecked(true);

        // Right area
        startTimeRadioButton = findViewById(R.id.startTimeRadioButton);

        endTimeRadioButton = findViewById(R.id.endTimeRadioButton);
        endTimeRadioButton.setChecked(true);

        // Bottom Area
        startTimePicker = findViewById(R.id.startTimePicker);
        endTimePicker = findViewById(R.id.endTimePicker);

        // Clear button
        clearButton = findViewById(R.id.clearButton);

        this.setListeners();
    }

    private void setListeners() {
        // Left area
        internshipTimeRadioButton.setOnCheckedChangeListener((arg0, arg1) -> {
            internshipTimeRadioButton.setChecked(arg1);
            fullTimeRadioButton.setChecked(!arg1);
        });

        fullTimeRadioButton.setOnCheckedChangeListener((arg0, arg1) -> {
            fullTimeRadioButton.setChecked(arg1);
            internshipTimeRadioButton.setChecked(!arg1);
        });

        // Right area
        startTimeRadioButton.setOnCheckedChangeListener((arg0, arg1) -> {
            startTimeRadioButton.setChecked(arg1);
            endTimeRadioButton.setChecked(!arg1);
        });

        endTimeRadioButton.setOnCheckedChangeListener((arg0, arg1) -> {
            endTimeRadioButton.setChecked(arg1);
            startTimeRadioButton.setChecked(!arg1);
        });

        // Time pickers
        startTimePicker.setOnTimeChangedListener((view, hour, minute) -> {
            if (startTimeRadioButton.isChecked()) {
                Time startTime = new Time(hour, minute);
                Time targetTime;
                if (internshipTimeRadioButton.isChecked()) {
                    targetTime = startTime.plus(INTERNSHIP_TIME).plus(INTERNSHIP_BREAK);
                }
                else {
                    targetTime = startTime.plus(FULL_TIME).plus(FULL_TIME_BREAK);
                }
                endTimePicker.setHour(targetTime.getHours());
                endTimePicker.setMinute(targetTime.getMinutes());
            }
        });

        // Time pickers
        endTimePicker.setOnTimeChangedListener((view, hour, minute) -> {
            if (endTimeRadioButton.isChecked()) {
                Time startTime = new Time(hour, minute);
                Time targetTime;
                if (internshipTimeRadioButton.isChecked()) {
                    targetTime = startTime.minus(INTERNSHIP_TIME).minus(INTERNSHIP_BREAK);
                }
                else {
                    targetTime = startTime.minus(FULL_TIME).minus(FULL_TIME_BREAK);
                }
                startTimePicker.setHour(targetTime.getHours());
                startTimePicker.setMinute(targetTime.getMinutes());
            }
        });

        // Clear button
        clearButton.setOnClickListener(view -> {
            internshipTimeRadioButton.setChecked(false);
            fullTimeRadioButton.setChecked(true);
            startTimeRadioButton.setChecked(true);
            endTimeRadioButton.setChecked(false);

            startTimePicker.setHour(DEFAULT_HOUR);
            startTimePicker.setMinute(DEFAULT_MINUTE);

            endTimePicker.setHour(DEFAULT_HOUR);
            endTimePicker.setMinute(DEFAULT_MINUTE);
        });
    }
}