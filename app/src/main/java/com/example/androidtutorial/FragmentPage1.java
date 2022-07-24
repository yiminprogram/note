package com.example.androidtutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentPage1 extends Fragment {
    private Button f1Btn;
    private TextView f1Text;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 判斷view是否存在，挑轉Fragment頁面可保留狀態
        if (view == null) {

            view = inflater.inflate(R.layout.fragment_page1, container, false);

            f1Btn = view.findViewById(R.id.f1_btn);
            f1Text = view.findViewById(R.id.f1_text);

            f1Btn.setOnClickListener(showLottery);
        }
        return view;
    }

    private final View.OnClickListener showLottery = v -> {
        int number = (int) (Math.random() * 49 + 1);
        f1Text.setText(String.valueOf(number));
    };
}