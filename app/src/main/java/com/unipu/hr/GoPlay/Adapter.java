package com.unipu.hr.GoPlay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context mContext;
    List<item> mData;

    public Adapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.datum.setText(mData.get(position).getDatum());
        holder.ime.setText(mData.get(position).getIme());
        holder.cijena.setText(mData.get(position).getCijena());
        holder.lokacija.setText(mData.get(position).getLokacija());
        holder.sport.setText(mData.get(position).getSport());
        new ImageLoadTask(mData.get(position).getSlika(), holder.slika).execute();


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView slika;
        TextView datum,sport,lokacija,cijena,ime;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            slika  = itemView.findViewById(R.id.kreator_slika);
            datum = itemView.findViewById(R.id.datum_text);
            sport = itemView.findViewById(R.id.sport_text);
            lokacija = itemView.findViewById(R.id.lokacija_text);
            cijena = itemView.findViewById(R.id.cijena_text);
            ime = itemView.findViewById(R.id.kreator_text);

        }
    }
}
