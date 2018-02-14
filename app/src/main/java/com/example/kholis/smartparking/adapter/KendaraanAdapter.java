package com.example.kholis.smartparking.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.model.DataKendaraan;

import java.util.List;

import butterknife.BindView;

/**
 * Created by norkholis on 06/02/18.
 */

public class KendaraanAdapter extends RecyclerView.Adapter<KendaraanAdapter.ViewHolder>{
    private List<DataKendaraan> kendaraans;
    private Context mContext;

    public KendaraanAdapter(Context context, List<DataKendaraan> kendaraans) {
        this.mContext = context;
        this.kendaraans = kendaraans;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View itemView = LayoutInflater.from(parent.getContext())
//                      .inflate(R.layout.item_kendaraan, parent, false);
        View itemView = inflater.inflate(R.layout.item_kendaraan, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataKendaraan kendaraan = kendaraans.get(position);
        holder.itJenisMobil.setText(kendaraan.getJenisKendaraan());
        holder.itNopolMobil.setText(kendaraan.getNopol());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(mContext, ""+kendaraan.getNopol().toString(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(mContext, ""+kendaraan.getNopol().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return kendaraans.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView itJenisMobil,itNopolMobil;
        private ItemClickListener itemClickListener;


        public ViewHolder(View itemView) {
            super(itemView);
            itJenisMobil = (TextView)itemView.findViewById(R.id.itJenisMobil);
            itNopolMobil = (TextView)itemView.findViewById(R.id.itNopolMobil);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getLayoutPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getLayoutPosition(), true);
            return true;
        }
    }
}
