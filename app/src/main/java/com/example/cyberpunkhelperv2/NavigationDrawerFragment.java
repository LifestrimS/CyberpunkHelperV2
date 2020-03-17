package com.example.cyberpunkhelperv2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends BottomSheetDialogFragment {

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        NavigationView navigationView = view.findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            switch (id) {
                case R.id.navMenu1:
                    Toast.makeText(getActivity(), "Nav menu1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navMenu2:
                    Toast.makeText(getActivity(), "Nav menu2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navMenu3:
                    Toast.makeText(getActivity(), "Nav menu3", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
        return view;
    }
}
