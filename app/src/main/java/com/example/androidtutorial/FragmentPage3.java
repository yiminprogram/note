package com.example.androidtutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentPage3 extends Fragment {
    private View view;
    private TextView f3Text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_page3, container, false);

            f3Text = view.findViewById(R.id.f3_text);
        }

        return view;
    }

    public void changeText() {
        f3Text.setText(String.valueOf((int) (Math.random() * 49 + 1)));
    }
}