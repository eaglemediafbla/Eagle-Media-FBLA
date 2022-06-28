package com.example.ha.ui.mail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MailViewModel extends ViewModel {
    //Constructor that initializes the about text to the text below
    private final MutableLiveData<String> mText;

    public MailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }


    //returns the text above
    public LiveData<String> getText() {
        return mText;
    }
}