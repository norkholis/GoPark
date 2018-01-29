package com.example.kholis.smartparking.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.formTambahMobil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class KendaraanFragment extends Fragment {

    @BindView(R.id.tambah_data_kendaraan)
    Button tambah_data_kendaraan;
    @BindView(R.id.list_data_kendaraan)
    Button list_data_kendaraan;
    View view;

    public KendaraanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ButterKnife.bind(getActivity());
        view = inflater.inflate(R.layout.fragment_kendaraan, container, false);

        tambah_data_kendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), formTambahMobil.class);
                startActivity(i);
            }
        });

        return view;
    }

}
