package com.example.ha.ui.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class CalendarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    //Constructor that initializes the about text to the text below
    public CalendarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }


    //returns the text above
    public LiveData<String> getText() {
        return mText;
    }
}