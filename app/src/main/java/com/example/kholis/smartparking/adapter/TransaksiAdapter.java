package com.example.kholis.smartparking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.model.DataPesan;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by norkholis on 16/02/18.
 */

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {
    private List<DataPesan> dataPesans;
    private Context mContext;

    public TransaksiAdapter(List<DataPesan> dataPesans, Context mContext) {
        this.dataPesans = dataPesans;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_transaksi, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataPesan pesan = dataPesans.get(position);

        holder.trid.setText(pesan.getId());
        holder.trwaktu.setText(pesan.getCreatedAt());
        holder.trstatus.setText(pesan.getStatus());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    //showing barcode
                }else{
                    //showing barcode
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPesans.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView trid, trwaktu, trstatus;
        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            trid = (TextView)itemView.findViewById(R.id.trid);
            trwaktu = (TextView)itemView.findViewById(R.id.trwaktu);
            trstatus = (TextView)itemView.findViewById(R.id.trstatus);
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