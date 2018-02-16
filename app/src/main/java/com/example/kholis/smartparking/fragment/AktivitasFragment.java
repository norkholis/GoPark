package com.example.kholis.smartparking.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.helper.ApiUtils;
import com.example.kholis.smartparking.helper.BaseApiService;
import com.example.kholis.smartparking.helper.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AktivitasFragment extends Fragment {
    private RecyclerView recyclerView;
    BaseApiService mBaseApiService;
    SharedPrefManager mSharedPrefManager;

    @BindView(R.id.rvHistory)
    RecyclerView rvHistory;


    public AktivitasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_aktivitas, container, false);

        mSharedPrefManager = new SharedPrefManager(getContext());
        String token = mSharedPrefManager.getSpToken().toString();

        ButterKnife.bind(getActivity());

        mBaseApiService = ApiUtils.getAPIService();

        return view;
    }

}
