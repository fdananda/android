package com.fdananda.gitmvvmsubtextviewbotao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityVM extends ViewModel {

    private MutableLiveData<String> mTexto = new MutableLiveData<>();
    private MutableLiveData<String> mTexto2 = new MutableLiveData<>();
    private MutableLiveData<Boolean> mBotao = new MutableLiveData<>();


    public void enviarTexto(String editText1, String editText2) {
        mTexto.setValue(editText1);
        mTexto2.setValue(editText2);
    }

    public LiveData<String> retornarTexto() {
        return mTexto;
    }

    public LiveData<String> retornarTexto2() {
        return mTexto2;
    }

    public void contarTexto(String editText1, String editText2) {
        mBotao.setValue(false);
        if (editText1.length()>=14 && editText2.length()>=6){
            mBotao.setValue(true);
        }else {
            mBotao.setValue(false);
        }
    }

    public LiveData<Boolean> ativarBotao() {
        return mBotao;
    }
}
