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


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter_sudionici extends RecyclerView.Adapter<Adapter_sudionici.myViewHolder2> {

    Context mContext;
    List<Item_sudionici> mData;
    FirebaseFirestore db;
    int position2;




    public Adapter_sudionici(Context mContext, List<Item_sudionici> mData) {
        this.mContext = mContext;
        this.mData = mData;
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public myViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item_sudionici,parent,false);
        return new myViewHolder2(v);


    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder2 holder, final int position) {
        position2 = position;
        db.collection("korisnici").document(mData.get(position).getIme()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("dohvacanje imena", "DocumentSnapshot data: " + document.getData());
                        holder.ime.setText(document.getString("ime"));
                        Log.d("Bool bris",mData.get(position).getBrisanje().toString());
                        if(mData.get(position).getBrisanje())
                            holder.slika.setVisibility(View.VISIBLE);
                        else{
                            holder.slika.setVisibility(View.INVISIBLE);
                        }


                    } else {
                        Log.d("dohvacanje imena", "No such document");
                    }
                } else {
                    Log.d("dohvacanje imena", "get failed with ", task.getException());
                }
            }
        });






    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder2 extends RecyclerView.ViewHolder {
        ImageView slika;
        TextView ime;

        public myViewHolder2(@NonNull View itemView) {
            super(itemView);
            slika  = itemView.findViewById(R.id.sudionici_cancel);
            ime = itemView.findViewById(R.id.sudionici_txt);

        }

    }
}
