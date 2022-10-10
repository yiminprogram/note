package com.example.androidtutorial.Jetpack.ViewModel;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel
{
    private int number = 0;

    public int getNumber()
    {
        return number;
    }

    public void add()
    {
        number++;
    }

    public void minus()
    {
        number--;
    }
}
