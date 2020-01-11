package com.unipu.hr.GoPlay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;


public class Dogadaji extends AppCompatActivity {
    private FirebaseUser currentFirebaseUser;
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

                db = FirebaseFirestore.getInstance();
                final Context hContex2 = this;
                db.collection("dogadaji")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    List<item> mlist = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("uspio", document.getId() + " => " + document.getData());
                                        mlist.add(document.toObject(item.class));

                                    }
                                    RecyclerView recyclerView = findViewById(R.id.rv_list_2);
                                    DividerItemDecoration itemDecorator = new DividerItemDecoration(hContex2, DividerItemDecoration.VERTICAL);
                                    itemDecorator.setDrawable(ContextCompat.getDrawable(hContex2, R.drawable.divider));
                                    recyclerView.addItemDecoration(itemDecorator);
                                    Adapter adapter = new Adapter(hContex2,mlist);
                                    adapter.setOnItemClickListener(new Adapter.ClickListener() {
                                        @Override
                                        public void onItemClick(int position, View v,List<String> mData) {
                                            Log.d("click", "onItemClick position: " + position);
                                            Intent myIntent = new Intent(Dogadaji.this, Sudionici.class);
                                            myIntent.putStringArrayListExtra("sudionici", (ArrayList<String>)mData);
                                            myIntent.putExtra("napravio","dogadaji");
                                            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(myIntent);
                                        }

                                        @Override
                                        public void onItemLongClick(int position, View v,List<String> mData) {
                                            Log.d("click", "onItemLongClick pos = " + position);
                                            Intent myIntent = new Intent(Dogadaji.this, Sudionici.class);
                                            myIntent.putStringArrayListExtra("sudionici",(ArrayList<String>) mData);
                                            myIntent.putExtra("napravio","dogadaji");
                                            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(myIntent);
                                        }
                                    });
                                    recyclerView.setAdapter(adapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(hContex2));
                                } else {
                                    Log.w("nije uspio", "Error getting documents.", task.getException());
                                }


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
    public void data(String s1){
        Intent myIntent = new Intent(Dogadaji.this, Kreiranje_dogadaja.class);
        myIntent.putExtra("Sport", s1);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);

    }

}
