package com.unipu.hr.GoPlay;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context mContext;
    List<item> mData;
    FirebaseFirestore db;
    int position2;
    private static ClickListener clickListener;



    public void setOnItemClickListener(ClickListener clickListener) {
        Adapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v, List<String> mData);
        void onItemLongClick(int position, View v,List<String> mData);
    }


    public Adapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item,parent,false);
        return new myViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {
        position2 = position;
        holder.datum.setText(mData.get(position).getDatum().toString());
        db.collection("korisnici").document(mData.get(position).getUserId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("dohvacanje imena", "DocumentSnapshot data: " + document.getData());
                        holder.ime.setText(document.getString("ime"));
                        new ImageLoadTask(document.getString("slika"),holder.slika).execute();


                    } else {
                        Log.d("dohvacanje imena", "No such document");
                    }
                } else {
                    Log.d("dohvacanje imena", "get failed with ", task.getException());
                }
            }
        });


        holder.cijena.setText(mData.get(position).getUplacneno() +" / "+mData.get(position).getCijena()+" KN");
        holder.lokacija.setText(mData.get(position).getLokacija());
        holder.sport.setText(mData.get(position).getSport());



    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView slika;
        TextView datum,sport,lokacija,cijena,ime;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            slika  = itemView.findViewById(R.id.kreator_slika);
            datum = itemView.findViewById(R.id.datum_text);
            sport = itemView.findViewById(R.id.sport_text);
            lokacija = itemView.findViewById(R.id.lokacija_text);
            cijena = itemView.findViewById(R.id.cijena_text);
            ime = itemView.findViewById(R.id.kreator_text);

        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v,mData.get(position2).getSudionici());
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v,mData.get(position2).getSudionici());
            return false;
        }
    }
}
