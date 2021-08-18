package com.fdananda.gitmvvm3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Boolean> mButton = new MutableLiveData<>();
    private MutableLiveData<String> mType = new MutableLiveData<>();

    public void onText(String string){
        if (string.length()<5){
            mButton.setValue(false);
        }else {
            mButton.setValue(true);
            onDefineType(string);
        }
    }

    private void onDefineType(String string){

        boolean isInteger = string.matches("-?\\d+");
        if (isInteger){
            mType.setValue("É um número");
        }else {
            mType.setValue("Não é um número");
        }
    }

    public MutableLiveData<Boolean> getButtonStatus(){
        return mButton;
    }

    public MutableLiveData<String> getResultType(){
        return mType;
    }
}
