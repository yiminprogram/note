package com.example.androidtutorial.Jetpack.LiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidtutorial.R;

public class LiveDataViewModelActivity extends AppCompatActivity
{
    private LiveDataViewModel mLiveDataViewModel;
    private TextView mText;
    private Button mBtn1;
    private Button mBtn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jetpack_activity_live_data_viewmodel);

        mText = findViewById(R.id.text_view);
        mBtn1 = findViewById(R.id.button1);
        mBtn2 = findViewById(R.id.button2);

        mLiveDataViewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);
        mLiveDataViewModel.getNumber().observe(this, new Observer<Integer>()
        {
            @Override
            public void onChanged(Integer integer)
            {
                mText.setText(String.valueOf(integer));
            }
        });

        mBtn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mLiveDataViewModel.addNumber(1);
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mLiveDataViewModel.addNumber(-1);
            }
        });
    }
}
