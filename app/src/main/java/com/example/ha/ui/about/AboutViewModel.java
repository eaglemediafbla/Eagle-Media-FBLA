package com.example.ha.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    //Constructor that initializes the about text to the text below
    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Eagle Media. \n\n" +
                "A place where you can see more and do more." +
                " An app which allows students to input their schedule, information about extracurricular activities, a lunch menu, the ability to email teachers and staff, and a calendar that is updated with school information.\n\n" +
                "For more information, please view our instagram account: eagle_media_fbla or contact us with our email: eaglemediacontact@gmail.com");
    }
    //returns the text above
    public LiveData<String> getText() {
        return mText;
    }
}