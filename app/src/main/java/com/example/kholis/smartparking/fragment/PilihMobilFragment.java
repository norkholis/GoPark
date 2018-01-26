package com.example.kholis.smartparking.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kholis.smartparking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PilihMobilFragment extends Fragment {


    public PilihMobilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pilih_mobil, container, false);
    }

}
