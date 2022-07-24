package com.example.androidtutorial;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentPage2 extends Fragment {
    private View view;
    private Button f2Btn;
    private FragmentActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_page2, container, false);

            f2Btn = view.findViewById(R.id.f2_btn);
            f2Btn.setOnClickListener(test2);
        }

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (FragmentActivity) context;
    }

    private final View.OnClickListener test2 = v -> {
        activity.changeText();
    };
}