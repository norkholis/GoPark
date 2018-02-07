package com.example.kholis.smartparking.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.adapter.KendaraanAdapter;
import com.example.kholis.smartparking.formTambahMobil;
import com.example.kholis.smartparking.helper.BaseApiService;
import com.example.kholis.smartparking.helper.SharedPrefManager;
import com.example.kholis.smartparking.model.DataKendaraan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KendaraanFragment extends Fragment {

    @BindView(R.id.tambah_data_kendaraan)
    ImageButton tambah_data_kendaraan;
    @BindView(R.id.list_data_kendaraan)
    ImageButton list_data_kendaraan;




    public KendaraanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kendaraan, container, false);

        ButterKnife.bind(this, view);


        tambah_data_kendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), formTambahMobil.class);
                startActivity(i);
            }
        });

        list_data_kendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PilihMobilFragment pilihMobilFragment = new PilihMobilFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, pilihMobilFragment);
                transaction.commit();
            }
        });

        return view;
    }

}
