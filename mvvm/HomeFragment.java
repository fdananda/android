package com.fdananda.mvvm2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.fdananda.mvvm2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FloatingActionButton mFab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final TextView textIncremento = root.findViewById(R.id.textViewIncremente);
        final FloatingActionButton mFab = root.findViewById(R.id.floatingActionButton);

        homeViewModel.homeViewModel();
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        homeViewModel.getIncremento().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textIncremento.setText(s);
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.incrementar();
            }
        });

        return root;
    }
}
