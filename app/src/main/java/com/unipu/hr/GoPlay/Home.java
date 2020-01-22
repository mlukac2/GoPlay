package com.unipu.hr.GoPlay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class Home extends AppCompatActivity {

    private FirebaseUser currentFirebaseUser;
    FirebaseFirestore db;
    List<item> mlist = new ArrayList<>();
    Adapter adapter;
    long novac;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ID = getIntent().getStringExtra("dogadaj");
        findViewById(R.id.fab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = NavigacijaFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

            }
        });


        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        db = FirebaseFirestore.getInstance();

        final Context hContex = this;


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    String token;

                    @Override
                    public void onComplete(@NonNull final Task<InstanceIdResult> task2) {
                        if (!task2.isSuccessful()) {
                            Log.w("instance", "getInstanceId failed", task2.getException());
                            return;
                        }
                        db.collection("korisnici").document(currentFirebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    Map<String, Object> korisnik = new HashMap<>();
                                    korisnik.put("ime", currentFirebaseUser.getDisplayName());
                                    korisnik.put("slika", currentFirebaseUser.getPhotoUrl().toString());
                                    SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("user_id", currentFirebaseUser.getUid());
                                    editor.putString("name", currentFirebaseUser.getDisplayName());
                                    editor.putString("picture", currentFirebaseUser.getPhotoUrl().toString());
                                    if (task2.isSuccessful()) {
                                        korisnik.put("token", task2.getResult().getToken());
                                        editor.putString("token", task2.getResult().getToken());
                                    }

                                    if (!document.exists()) {
                                        Log.d("kreiranje", "uso");
                                        korisnik.put("novac", 0);
                                        db.collection("korisnici").document(currentFirebaseUser.getUid()).set(korisnik).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("Dogadaji", "Added ");
                                            }
                                        })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w("Dogadaji fail", "Error adding document", e);
                                                    }
                                                });
                                    } else {
                                        Log.d("izmjena", "uso");
                                        db.collection("korisnici").document(currentFirebaseUser.getUid()).update(korisnik)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        Log.d("Dogadaji", "Added ");
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w("Dogadaji fail", "Error adding document", e);
                                                    }
                                                });

                                    }
                                    editor.putLong("novac", document.getLong("novac"));
                                    TextView novacTxt = findViewById(R.id.novcanik_gumb);
                                    novacTxt.setText(document.getLong("novac").toString());
                                    editor.commit();

                                } else {
                                    Log.d("nije", "Error getting documents: ", task.getException());
                                }
                            }
                        });
                    }
                });

        if (ID == null){
            db.collection("korisnici").document(currentFirebaseUser.getUid())
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot snapshot,
                                            @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.w("h:korisnici", "Listen failed.", e);
                                return;
                            }
                            List<String> IDs = null;
                            if (snapshot != null && snapshot.exists()) {
                                IDs = (List<String>) snapshot.get("dogadaji");
                                if (IDs == null) {
                                    IDs = new ArrayList<>();
                                    IDs.add("0");
                                } else if (IDs.isEmpty()) {
                                    IDs.add("0");
                                }
                                Log.d("uso if", "kor");
                            } else {
                                Log.d("uso e", "kor");
                                Log.d("h:korisnici", "Current data: null");
                            }


                            db.collection("dogadaji").whereIn(FieldPath.documentId(), IDs)
                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot value,
                                                            @Nullable FirebaseFirestoreException e) {
                                            if (e != null) {
                                                Log.w("listen", "Listen failed.", e);
                                                return;
                                            }

                                            mlist.clear();
                                            for (QueryDocumentSnapshot document : value) {
                                                item item2 = document.toObject(item.class);
                                                item2.setDocumentId(document.getId());
                                                mlist.add(item2);
                                                Log.d("listen", "uso");
                                            }
                                            RecyclerView recyclerView = findViewById(R.id.rv_list);
                                            DividerItemDecoration itemDecorator = new DividerItemDecoration(hContex, DividerItemDecoration.VERTICAL);
                                            itemDecorator.setDrawable(ContextCompat.getDrawable(hContex, R.drawable.divider));
                                            recyclerView.addItemDecoration(itemDecorator);
                                            adapter = new Adapter(hContex, mlist);
                                            adapter.setOnItemClickListener(new Adapter.ClickListener() {
                                                @Override
                                                public void onItemClick(int position, View v, String mData) {
                                                    Intent myIntent = new Intent(Home.this, Sudionici.class);
                                                    myIntent.putExtra("sudionici", mData);
                                                    myIntent.putExtra("napravio", "home");
                                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                    startActivity(myIntent);
                                                }

                                                @Override
                                                public void onItemLongClick(int position, View v, String mData) {
                                                    Intent myIntent = new Intent(Home.this, Sudionici.class);
                                                    myIntent.putExtra("sudionici", mData);
                                                    myIntent.putExtra("napravio", "home");
                                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                    startActivity(myIntent);
                                                }
                                            });
                                            recyclerView.setAdapter(adapter);
                                            recyclerView.setLayoutManager(new LinearLayoutManager(hContex));

                                        }
                                    });
                        }
                    });
    }
        else{
            db.collection("dogadaji").whereEqualTo(FieldPath.documentId(), ID).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                mlist.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    item item2 = document.toObject(item.class);
                                    item2.setDocumentId(document.getId());
                                    mlist.add(item2);
                                    Log.d("listen", "uso");
                                }
                            } else {
                                Log.d("notifikacija", "Error getting documents: ", task.getException());
                            }

                            RecyclerView recyclerView = findViewById(R.id.rv_list);
                            DividerItemDecoration itemDecorator = new DividerItemDecoration(hContex, DividerItemDecoration.VERTICAL);
                            itemDecorator.setDrawable(ContextCompat.getDrawable(hContex, R.drawable.divider));
                            recyclerView.addItemDecoration(itemDecorator);
                            adapter = new Adapter(hContex, mlist);
                            adapter.setOnItemClickListener(new Adapter.ClickListener() {
                                @Override
                                public void onItemClick(int position, View v, String mData) {
                                    Intent myIntent = new Intent(Home.this, Sudionici.class);
                                    myIntent.putExtra("sudionici", mData);
                                    myIntent.putExtra("napravio", "home");
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(myIntent);
                                }

                                @Override
                                public void onItemLongClick(int position, View v, String mData) {
                                    Intent myIntent = new Intent(Home.this, Sudionici.class);
                                    myIntent.putExtra("sudionici", mData);
                                    myIntent.putExtra("napravio", "home");
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(myIntent);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(hContex));

                        }
                    });
        }

    }

    public void Button2(View v) {
        Intent myIntent = new Intent(Home.this, Dogadaji.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);


    }


    public void Button3(View v) {
        Intent myIntent = new Intent(Home.this, Profile.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);
    }

    public void Button4(View v) {
        Intent myIntent = new Intent(Home.this, Novcanik.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        TextView novacTxt = findViewById(R.id.novcanik_gumb);
        novacTxt.setText(String.valueOf(preferences.getLong("novac",0)));

    }


}
