package com.fdananda.mvvm2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mResultado = new MutableLiveData<>();
    int numero = 0;

    public void homeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public void incrementar(){
        numero++;
        mResultado.setValue(String.valueOf(numero));
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getIncremento() {
        return mResultado;
    }
}
