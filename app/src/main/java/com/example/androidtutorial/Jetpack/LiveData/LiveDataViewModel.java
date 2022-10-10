package com.example.androidtutorial.Jetpack.LiveData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveDataViewModel extends ViewModel
{
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber()
    {
        if (number == null)
        {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void addNumber(int value)
    {
        number.setValue(number.getValue() + value);
    }
}
