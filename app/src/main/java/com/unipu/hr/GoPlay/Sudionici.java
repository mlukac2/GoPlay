package com.unipu.hr.GoPlay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sudionici extends AppCompatActivity {
    Button cancel;
    FirebaseFirestore db;
    Button joinLeave;
    final Context hContex = this;
    String sudionici;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudionici);
        SharedPreferences preferences = getSharedPreferences("preferences",
                MODE_PRIVATE);
        final String userID = preferences.getString("user_id", "0");

        cancel = findViewById(R.id.cancel);
        joinLeave = findViewById(R.id.join_leave);
        if(getIntent().getStringExtra("napravio").equals( "home"))
            joinLeave.setText("Napusti");
        if(getIntent().getStringExtra("napravio").equals("dogadaji"))
            joinLeave.setText("Pridruži se");
        sudionici = getIntent().getStringExtra("sudionici");
        Log.d("sudionici",sudionici);
        db = FirebaseFirestore.getInstance();
        db.collection("dogadaji").document(sudionici).collection("Sudionici")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Item_sudionici> mlist = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Item_sudionici item = document.toObject(Item_sudionici.class);
                                item.setIme(document.getId());
                                Log.d("sudIspis",document.getData().toString());
                                if(userID.equals(document.getId())&&item.getBrisanje() == true)
                                    joinLeave.setText("Ostani");
                                Log.d("mlist",document.getData().toString());
                                item.setBrisanje(document.getBoolean("brisanje"));
                                mlist.add(item);
                            }
                            Log.d("mlist",mlist.toString());

        RecyclerView recyclerView = findViewById(R.id.rv_sudionici);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(hContex, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(hContex, R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);
        Adapter_sudionici adapter = new Adapter_sudionici(hContex,mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(hContex));

                    } else {
                        Log.d("ne dohvati", "Error getting documents: ", task.getException());
                    }
                }
    });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        joinLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(joinLeave.getText().equals("Napusti")){
                    db.collection("dogadaji").document(sudionici).update("brisanje", FieldValue.increment(1));
                    db.collection("dogadaji").document(sudionici).collection("Sudionici").document(userID).update("brisanje", true);
                    finish();
                }
                else if(joinLeave.getText().equals("Ostani")){
                    db.collection("dogadaji").document(sudionici).update("brisanje", FieldValue.increment(-1));
                    db.collection("dogadaji").document(sudionici).collection("Sudionici").document(userID).update("brisanje", false);
                    finish();
                }
                else{
                    Map<String, Object> brisnje = new HashMap<>();
                    brisnje.put("brisanje", false);
                    db.collection("dogadaji").document(sudionici).collection("Sudionici").document(userID).set(brisnje);
                    db.collection("dogadaji").document(sudionici).update("brSudionika",FieldValue.increment(1));
                    db.collection("korisnici").document(userID).update("dogadaji", FieldValue.arrayUnion(sudionici));
                    finish();
                }



            }
        });

    }
}
