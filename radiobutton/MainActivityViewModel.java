package com.fdananda.gitradiobutton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> mOptionSelected = new MutableLiveData<>();

    public void onChoice(String optionSelected){
        switch (optionSelected){
            case "1":
                mOptionSelected.setValue("1");
                break;
            case "2":
                mOptionSelected.setValue("2");
                break;
            case "3":
                mOptionSelected.setValue("3");
                break;
            case "4":
                mOptionSelected.setValue("4");
                break;
        }
    }

    public MutableLiveData<String> getOptionSelected(){
        return mOptionSelected;
    }
}
