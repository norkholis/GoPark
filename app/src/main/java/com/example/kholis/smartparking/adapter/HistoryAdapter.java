package com.example.kholis.smartparking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.model.DataHistory;
import com.example.kholis.smartparking.model.StatusHistory;

import java.util.List;

/**
 * Created by norkholis on 14/02/18.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<DataHistory> dataHistories ;
    private Context mContext;

    public HistoryAdapter(List<DataHistory> dataHistories, Context mContext) {
        this.dataHistories = dataHistories;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_history, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataHistory history = dataHistories.get(position);

        holder.hsWaktuMasuk.setText(history.getWaktuMasuk().toString());
        holder.hsWaktuKeluar.setText(history.getWaktuKeluar().toString());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    //Do Something
                }else{
                    //Do something
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataHistories.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView hsWaktuMasuk, hsWaktuKeluar;
        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            hsWaktuMasuk = (TextView)itemView.findViewById(R.id.hsWaktuMasuk);
            hsWaktuKeluar = (TextView)itemView.findViewById(R.id.hsWaktuKeluar);
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
