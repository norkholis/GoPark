package com.example.kholis.smartparking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.model.DataKendaraan;

import java.util.List;

/**
 * Created by norkholis on 06/02/18.
 */

public class KendaraanAdapter extends RecyclerView.Adapter<KendaraanAdapter.CustomViewHolder>{
    private List<DataKendaraan> kendaraans;

    public KendaraanAdapter(List<DataKendaraan> kendaraans) {
        this.kendaraans = kendaraans;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kendaraan, parent, false);

        return new CustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        DataKendaraan kendaraan = kendaraans.get(position);
        holder.itJenisMobil.setText(kendaraan.getJenisKendaraan());
        holder.itNopolMobil.setText(kendaraan.getNopol());
    }

    @Override
    public int getItemCount() {
        return kendaraans.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView itJenisMobil, itNopolMobil;
        public Button sltMobil;

        public CustomViewHolder(View view) {
            super(view);
            itJenisMobil = (TextView)view.findViewById(R.id.itJenisMobil);
            itNopolMobil = (TextView)view.findViewById(R.id.itNopolMobil);
            sltMobil = (Button) view.findViewById(R.id.sltMobil);

        }
    }
}
