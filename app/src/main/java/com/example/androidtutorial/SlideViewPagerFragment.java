package com.example.androidtutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SlideViewPagerFragment extends Fragment
{
    private View mainView;
    private String content;

    public SlideViewPagerFragment(String content)
    {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (mainView == null)
        {
            mainView = inflater.inflate(R.layout.slide_view_pager_fragment, container, false);

            TextView contentView = mainView.findViewById(R.id.tab_content);
            contentView.setText(content);
        }
        return mainView;
    }
}