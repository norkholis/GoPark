package com.example.kholis.smartparking.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.adapter.KendaraanAdapter;
import com.example.kholis.smartparking.helper.ApiUtils;
import com.example.kholis.smartparking.helper.BaseApiService;
import com.example.kholis.smartparking.helper.SharedPrefManager;
import com.example.kholis.smartparking.model.DataKendaraan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    int id_tempatParkir;




    public PilihMobilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_pilih_mobil, container, false);

        mSharedPreferenceManager = new SharedPrefManager(getContext());
        final String token = mSharedPreferenceManager.getSpToken();
        final int id = mSharedPreferenceManager.getSpId();
        id_tempatParkir = getArguments().getInt("id_tempatParkir");

        ButterKnife.bind(getActivity(), view);

        mBaseApiService = ApiUtils.getAPIService();
        mBaseApiService.getSemuakendaraan(id, token).enqueue(new Callback<List<DataKendaraan>>() {
            @Override
            public void onResponse(Call<List<DataKendaraan>> call, Response<List<DataKendaraan>> response) {
                if (response.isSuccessful()){
                    List<DataKendaraan> listKendaraan = response.body();
                    recyclerview = (RecyclerView)view.findViewById(R.id.rvKendaraan);
                    mKendaraanAdapter = new KendaraanAdapter(getContext(),listKendaraan,id_tempatParkir,id, token);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerview.setLayoutManager(mLayoutManager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mKendaraanAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<DataKendaraan>> call, Throwable t) {
                String Quotes = "Instropeksi diri, berdo'a nya ditambah lagi, usahanya tambah lagi," +
                        "semangatnya tambah lagi" +
                        "Harus tambah dekat lagi sama yang punya hidup";
                Toast.makeText(getActivity(),Quotes, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
