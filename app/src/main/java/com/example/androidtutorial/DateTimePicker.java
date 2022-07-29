package com.example.androidtutorial;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DateTimePicker extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_picker);

        findViewById(R.id.date_picker).setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    Log.d("apple", "date : " + i + "-" + (i1 + 1) + "-" + i2);
                }
            }, 2022, 0, 1);

            datePickerDialog.show();
        });

        findViewById(R.id.time_picker).setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    Log.d("apple", "time : " + i + ":" + i1);
                }
            }, 12, 00, true);
            timePickerDialog.show();
        });
    }
}
