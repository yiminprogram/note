package com.example.androidtutorial.Jetpack.ViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidtutorial.R;

public class ViewModelActivity extends AppCompatActivity
{
    private MyViewModel m_viewModel;
    private TextView m_textView;
    private Button m_button1, m_button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        m_viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        m_textView = findViewById(R.id.text_view);
        m_button1 = findViewById(R.id.button1);
        m_button2 = findViewById(R.id.button2);

        m_textView.setText(m_viewModel.getNumber());

        m_button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                m_viewModel.add();
                m_textView.setText(String.valueOf(m_viewModel.getNumber()));
            }
        });

        m_button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                m_viewModel.minus();
                m_textView.setText(String.valueOf(m_viewModel.getNumber()));
            }
        });
    }
}
