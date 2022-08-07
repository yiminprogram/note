package com.example.androidtutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

public class SlideViewPagerActivity extends AppCompatActivity
{
    private ViewPager viewPager;
    private TextView[] titleViews = new TextView[4];
    private View[] lineViews = new View[4];
    private int[] titles = {R.id.title0, R.id.title1, R.id.title2, R.id.title3};
    private int[] lines = {R.id.line0, R.id.line1, R.id.line2, R.id.line3};
    private MyPagerAdapter myPagerAdapter;
    private LinkedList<SlideViewPagerFragment> pages = new LinkedList<>();
    private LinkedList<String> headers = new LinkedList<>();
    private int startIndex, nowItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_view_pager);

        viewPager = findViewById(R.id.view_pager);

        for (int i = 0; i < 4; i++)
        {
            titleViews[i] = findViewById(titles[i]);
            lineViews[i] = findViewById(lines[i]);
        }

        titleViews[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                nowItem = startIndex;
                viewPager.setCurrentItem(nowItem);
            }
        });

        titleViews[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                nowItem = startIndex + 1;
                viewPager.setCurrentItem(nowItem);
            }
        });

        titleViews[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                nowItem = startIndex + 2;
                viewPager.setCurrentItem(nowItem);
            }
        });

        titleViews[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                nowItem = startIndex + 3;
                viewPager.setCurrentItem(nowItem);
            }
        });

        initViewPager();

        addPages();
    }

    private void initViewPager()
    {
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                nowItem = viewPager.getCurrentItem();
                startIndex = 0;
                if (nowItem > startIndex + 3)
                {
                    startIndex = nowItem - 3;
                }
                changePage();
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(@NonNull FragmentManager fm)
        {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position)
        {
            return pages.get(position);
        }

        @Override
        public int getCount()
        {
            return pages.size();
        }
    }

    private void addPages()
    {
        for (int i = 0; i < 4; i++)
        {
            String pageName = "Page" + (i + 1);
            SlideViewPagerFragment viewPagerFragment = new SlideViewPagerFragment(pageName);
            pages.add(viewPagerFragment);
            headers.add(pageName);
        }

        myPagerAdapter.notifyDataSetChanged();
        changePage();
    }

    private void changePage()
    {
        for (int i = 0; i < 4; i++)
        {
            if (startIndex + i < headers.size())
            {
                titleViews[i].setText(headers.get(startIndex + i));
            }
            if (startIndex + i == nowItem)
            {
                lineViews[i].setVisibility(View.VISIBLE);
                titleViews[i].setTypeface(Typeface.DEFAULT_BOLD);
                titleViews[i].setTextColor(Color.rgb(0, 0, 0));
            }
            else
            {
                lineViews[i].setVisibility(View.INVISIBLE);
                titleViews[i].setTypeface(Typeface.DEFAULT);
                titleViews[i].setTextColor(Color.rgb(55, 55, 55));
            }
        }
    }

}