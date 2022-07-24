package com.example.androidtutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentActivity extends AppCompatActivity {
    private FragmentPage1 f1;
    private FragmentPage2 f2;
    private FragmentPage3 f3;
    private FragmentManager fragmentManager;
    private TextView mainText;
    private Button mainBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

        findViewById(R.id.page1).setOnClickListener(goToPage1);
        findViewById(R.id.page2).setOnClickListener(goToPage2);
        findViewById(R.id.page3).setOnClickListener(goToPage3);
        mainText = findViewById(R.id.f_main_text);
        mainBtn = findViewById(R.id.f_main_btn);

        mainBtn.setOnClickListener(test1);

        f1 = new FragmentPage1();
        f2 = new FragmentPage2();
        f3 = new FragmentPage3();
        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, f1);
        fragmentTransaction.commit();
    }

    private final View.OnClickListener goToPage1 = v -> {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f1);
        fragmentTransaction.commit();
    };

    private final View.OnClickListener goToPage2 = v -> {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f2);
        fragmentTransaction.commit();
    };

    private final View.OnClickListener goToPage3 = v -> {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f3);
        fragmentTransaction.commit();
    };

    public void changeText() {
        mainText.setText(String.valueOf((int) (Math.random() * 49 + 1)));
    }

    private final View.OnClickListener test1 = v -> {
        f3.changeText();
    };
}
