package com.example.kholis.smartparking.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.adapter.KendaraanAdapter;
import com.example.kholis.smartparking.helper.BaseApiService;
import com.example.kholis.smartparking.helper.SharedPrefManager;
import com.example.kholis.smartparking.model.DataKendaraan;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PilihMobilFragment extends Fragment {


    private RecyclerView recyclerview;
    BaseApiService mBaseApiService;
    private KendaraanAdapter mKendaraanAdapter;
    SharedPrefManager mSharedPreferenceManager;
    @BindView(R.id.rvKendaraan)
    RecyclerView rvKendaraan;



    public PilihMobilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mSharedPreferenceManager = new SharedPrefManager(getContext());
        String token = mSharedPreferenceManager.getSpToken();
        int id = mSharedPreferenceManager.getSpId();

        mBaseApiService.getSemuakendaraan(id, token).enqueue(new Callback<List<DataKendaraan>>() {
            @Override
            public void onResponse(Call<List<DataKendaraan>> call, Response<List<DataKendaraan>> response) {
                if (response.isSuccessful()){
                    List<DataKendaraan> listKendaraan = response.body();
                    recyclerview = rvKendaraan;
                    mKendaraanAdapter = new KendaraanAdapter(listKendaraan);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerview.setLayoutManager(mLayoutManager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mKendaraanAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<DataKendaraan>> call, Throwable t) {

            }
        });

        return inflater.inflate(R.layout.fragment_pilih_mobil, container, false);
    }

}
