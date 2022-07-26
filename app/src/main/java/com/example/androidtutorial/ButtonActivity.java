package com.example.androidtutorial;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ButtonActivity extends AppCompatActivity {
    Button b1, b2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_activity);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b1.setOnClickListener(handleClick);
        b2.setOnClickListener(handleClick);
    }

    private final View.OnClickListener handleClick = (view) -> {
        if (view == b1) {
            Log.d("apple", "button 1 click");
        } else if (view == b2) {
            Log.d("apple", "button 2 click");
        }
    };
}
