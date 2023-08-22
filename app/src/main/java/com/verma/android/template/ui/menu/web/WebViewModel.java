package com.verma.android.template.ui.menu.web;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WebViewModel extends ViewModel {

    public boolean isLoadFinish;
    private MutableLiveData<String> mText;

    public WebViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String url) {
        mText.postValue(url);
    }
}