package com.unipu.hr.GoPlay;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    private Context mContext;
    private List<item> mData;
    private FirebaseFirestore db;
    private static ClickListener clickListener;



    public void setOnItemClickListener(ClickListener clickListener) {
        Adapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v, String mData);
        void onItemLongClick(int position, View v,String mData);
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
        DateTime curentdate = new DateTime();
        DateTime dateobj = new DateTime();
        dateobj = dateobj.plusDays(6);
        Date izbaze = mData.get(position).getDatum();
        DateTime dateTime = new DateTime(izbaze);
        if(dateTime.withTimeAtStartOfDay().isEqual(curentdate.withTimeAtStartOfDay())){
            Log.d("danas","uso sam");
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("HH:mm");
            holder.datum.setText("Danas "+dtfOut.print(dateTime));
        }
        else if(dateTime.getMillis()<dateobj.getMillis()){
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE", new Locale("hr", "HR"));
        String goal = outFormat.format(mData.get(position).getDatum());
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("HH:mm");
        holder.datum.setText(goal+" "+dtfOut.print(dateTime));}
        else {
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd/MM HH:mm");
            holder.datum.setText(dtfOut.print(dateTime));

        }
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


        holder.cijena.setText(mData.get(position).getUplaceno() +" / "+mData.get(position).getCijena()+" KN");
        holder.lokacija.setText(mData.get(position).getLokacija());
        holder.sport.setText(mData.get(position).getSport());
        holder.brisanje.setText(mData.get(position).getBrisanje()+"/"+mData.get(position).getBrSudionika());
        holder.sudionici.setText(mData.get(position).getBrSudionika()+"/"+mData.get(position).getBrOsoba());
        holder.indCijena.setText(mData.get(position).getUdio()+"");



    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView slika;
        TextView datum,sport,lokacija,cijena,ime,sudionici,brisanje,indCijena;

        private myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            slika  = itemView.findViewById(R.id.kreator_slika);
            datum = itemView.findViewById(R.id.datum_text);
            sport = itemView.findViewById(R.id.sport_text);
            lokacija = itemView.findViewById(R.id.lokacija_text);
            cijena = itemView.findViewById(R.id.cijena_text);
            ime = itemView.findViewById(R.id.kreator_text);
            sudionici = itemView.findViewById(R.id.brSudionika);
            brisanje = itemView.findViewById(R.id.brBrisanje);
            indCijena = itemView.findViewById(R.id.indCijena);


        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clickListener.onItemClick(position, v,mData.get(position).getDocumentId());
            Log.d("pmedata",mData.get(position).getDocumentId());
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            clickListener.onItemLongClick(position, v,mData.get(position).getDocumentId());
            return false;
        }
    }
}
