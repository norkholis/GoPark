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
import com.example.kholis.smartparking.adapter.HistoryAdapter;
import com.example.kholis.smartparking.helper.ApiUtils;
import com.example.kholis.smartparking.helper.BaseApiService;
import com.example.kholis.smartparking.helper.SharedPrefManager;
import com.example.kholis.smartparking.model.DataHistory;
import com.example.kholis.smartparking.model.ListDataHistory;
import com.example.kholis.smartparking.model.StatusHistory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    BaseApiService mBaseApiService;
    SharedPrefManager mSharedPrefManager;
    private HistoryAdapter mHistoryAdapter;


    @BindView(R.id.rvHistory)
    RecyclerView rvHistory;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_history, container, false);

        mSharedPrefManager = new SharedPrefManager(getContext());
        String token = mSharedPrefManager.getSpToken().toString();
        int id = mSharedPrefManager.getSpId();

        ButterKnife.bind(getActivity());

        mBaseApiService = ApiUtils.getAPIService();
        mBaseApiService.getDataHistory(token, id).enqueue(new Callback<ListDataHistory>() {
            @Override
            public void onResponse(Call<ListDataHistory> call, Response<ListDataHistory> response) {
               if (response.isSuccessful()){
                List<DataHistory> dataHistoryList = response.body().getData();
                List<StatusHistory> statusHistoryList = (List<StatusHistory>) response.body().getStatus();
                recyclerView = (RecyclerView)view.findViewById(R.id.rvHistory);
                mHistoryAdapter = new HistoryAdapter(dataHistoryList, statusHistoryList,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mHistoryAdapter);

               }
            }

            @Override
            public void onFailure(Call<ListDataHistory> call, Throwable t) {

            }
        });

        return view;
    }

}
