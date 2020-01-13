package com.unipu.hr.GoPlay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Sudionici extends AppCompatActivity {
    Button cancel;
    Button joinLeave;
    final Context hContex = this;
    String sudionici;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudionici);
        sudionici = getIntent().getStringExtra("sudionici");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                                //item.setBrisanje((Boolean) document.get("brisanje"));
                                mlist.add(item);}

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


        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        joinLeave = findViewById(R.id.join_leave);
        if(getIntent().getStringExtra("napravio") == "home")
            joinLeave.setText("Napusti");
        if(getIntent().getStringExtra("napravio") == "dogadaji")
            joinLeave.setText("Pridruži se");
        joinLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(getIntent().getStringExtra("napravio") == "home")

                    //if(getIntent().getStringExtra("napravio") == "dogadaji")


            }
        });

    }
}
