package com.example.ha.ui.extracurriculars;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ExtracurricularsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    //Constructor that initializes the about text to the text below
    public ExtracurricularsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }


    //returns the text above

    public LiveData<String> getText() {
        return mText;
    }
}