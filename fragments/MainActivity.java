package com.fdananda.gitfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private UmFragment   umFragment;
    private DoisFragment doisFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        umFragment  = new UmFragment();
        doisFragment  = new DoisFragment();

        //Configura fragment que será apresentado inicialmente
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayoutConteudo, umFragment);
        transaction.commit();
    }

    public void abrirFragment1DaActivity(View view){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutConteudo, umFragment);
        transaction.commit();

    }

    public void abrirFragment2DaActivity(View view){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutConteudo, doisFragment);
        transaction.commit();

    }
}
