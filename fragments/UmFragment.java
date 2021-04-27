package com.fdananda.gitfragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class UmFragment extends Fragment {

    private DoisFragment doisFragment;
    private Button buttonAbrirFragment2;

    public UmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_um, container, false);

        doisFragment = new DoisFragment();
        buttonAbrirFragment2 = view.findViewById(R.id.buttonAbrirFragmentDois);

        buttonAbrirFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutConteudo, doisFragment);
                transaction.commit();
            }
        });

        return view;
    }
}
