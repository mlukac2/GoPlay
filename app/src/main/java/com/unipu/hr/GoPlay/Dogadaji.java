package com.unipu.hr.GoPlay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Dogadaji extends AppCompatActivity {
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dogadaji);
        findViewById(R.id.fab2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = NavigacijaFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });

        SharedPreferences preferences = getSharedPreferences("preferences",
                MODE_PRIVATE);


        final String userID = preferences.getString("user_id", "0");
                db = FirebaseFirestore.getInstance();
                final Context hContex2 = this;

        db.collection("korisnici").document(userID)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("h:korisnici", "Listen failed.", e);
                            return;
                        }
                        final List<String> IDs;
                        TextView novacTxt = findViewById(R.id.novcanik_gumb_2);
                        novacTxt.setText(String.valueOf(snapshot.getLong("novac")));
                        if (snapshot != null && snapshot.exists()) {
                            IDs = (List<String>) snapshot.get("dogadaji");


                        } else {
                            IDs = null;
                            Log.d("h:korisnici", "Current data: null");
                        }

                db.collection("dogadaji")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value,
                                                @Nullable FirebaseFirestoreException e) {
                                if (e != null) {
                                    Log.w("listen", "Listen failed.", e);
                                    return;
                                }

                                List<item> mlist = new ArrayList<>();
                                for (QueryDocumentSnapshot document : value) {
                                    Log.d("uspio", document.getId() + " => " + document.getData());
                                    if (IDs != null) {
                                        if (!IDs.contains(document.getId())) {
                                            item item2 = document.toObject(item.class);
                                            item2.setDocumentId(document.getId());
                                            mlist.add(item2);
                                        }
                                    } else{
                                        item item2 = document.toObject(item.class);
                                        item2.setDocumentId(document.getId());
                                        mlist.add(item2);
                                    }
                                }
                                    RecyclerView recyclerView = findViewById(R.id.rv_list_2);
                                    DividerItemDecoration itemDecorator = new DividerItemDecoration(hContex2, DividerItemDecoration.VERTICAL);
                                    itemDecorator.setDrawable(ContextCompat.getDrawable(hContex2, R.drawable.divider));
                                    recyclerView.addItemDecoration(itemDecorator);
                                    Adapter adapter = new Adapter(hContex2,mlist);
                                    adapter.setOnItemClickListener(new Adapter.ClickListener() {
                                        @Override
                                        public void onItemClick(int position, View v,String mData) {
                                            Intent myIntent = new Intent(Dogadaji.this, Sudionici.class);
                                            myIntent.putExtra("sudionici", mData);
                                            myIntent.putExtra("napravio","dogadaji");
                                            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(myIntent);
                                        }

                                        @Override
                                        public void onItemLongClick(int position, View v,String mData) {
                                            Intent myIntent = new Intent(Dogadaji.this, Sudionici.class);
                                            myIntent.putExtra("sudionici",mData);
                                            myIntent.putExtra("napravio","dogadaji");
                                            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(myIntent);
                                        }
                                    });
                                    recyclerView.setAdapter(adapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(hContex2));



            }
        });

                    }
                });


    }
    public void Button1(View v) {
        Intent myIntent = new Intent(Dogadaji.this, Home.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);


    }





    public void Button3(View v) {
        Intent myIntent = new Intent(Dogadaji.this, Profile.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);
    }

    public void Button4(View v) {
        Intent myIntent = new Intent(Dogadaji.this, Novcanik.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);
    }


}
