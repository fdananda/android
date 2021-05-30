package com.fdananda.gitmvvmsubtextviewbotao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityVM extends ViewModel {

    private MutableLiveData<String> mTexto = new MutableLiveData<>();
    private MutableLiveData<Boolean> mBotao = new MutableLiveData<>();


    public void enviarTexto(String editText) {
        mTexto.setValue(editText);
    }

    public LiveData<String> retornarTexto() {
        return mTexto;
    }

    public void contarTexto(String editText) {
        mBotao.setValue(true);
        if (editText.length()>=6){
            mBotao.setValue(true);
        }else {
            mBotao.setValue(false);
        }
    }

    public LiveData<Boolean> ativarBotao() {
        return mBotao;
    }
}
