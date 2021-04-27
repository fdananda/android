package com.fdananda.gitfragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DoisFragment extends Fragment {

    private UmFragment umFragment;
    private Button buttonAbrirFragment1;

    public DoisFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dois, container, false);

        umFragment = new UmFragment();
        buttonAbrirFragment1 = view.findViewById(R.id.buttonAbrirFragmentUm);

        buttonAbrirFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutConteudo, umFragment);
                transaction.commit();
            }
        });

        return view;
    }
}
