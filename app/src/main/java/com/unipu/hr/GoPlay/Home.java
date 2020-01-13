package com.unipu.hr.GoPlay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Home extends AppCompatActivity {

    private FirebaseUser currentFirebaseUser;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        findViewById(R.id.fab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = NavigacijaFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

            }
        });




        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(this, " " + currentFirebaseUser.getUid() +" " +currentFirebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();


        db = FirebaseFirestore.getInstance();
        final FirebaseUserMetadata metadata = currentFirebaseUser.getMetadata();
        Map<String, Object> korisnik = new HashMap<>();
        if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
            korisnik.put("novac", 0);
        }
        korisnik.put("ime",currentFirebaseUser.getDisplayName());
        korisnik.put("slika",currentFirebaseUser.getPhotoUrl().toString());
        //unos(korisnik,"korisnici",currentFirebaseUser.getUid());
        final Context hContex = this;
        final DocumentReference user = db.collection("korisnici").document(currentFirebaseUser.getUid());



        db.collection("korisnici").document(currentFirebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    final List<String> IDs = (List<String>)document.get("dogadaji");

                    db.collection("dogadaji")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        List<item> mlist = new ArrayList<>();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            for(String ID : IDs ){



                                                Log.d("uso", ID);
                                                Log.d("uso2", document.getId());
                                                if(document.getId().equals(ID))
                                                    mlist.add(document.toObject(item.class));}

                                        }
                                        
                                        RecyclerView recyclerView = findViewById(R.id.rv_list);
                                        DividerItemDecoration itemDecorator = new DividerItemDecoration(hContex, DividerItemDecoration.VERTICAL);
                                        itemDecorator.setDrawable(ContextCompat.getDrawable(hContex, R.drawable.divider));
                                        recyclerView.addItemDecoration(itemDecorator);
                                        Adapter adapter = new Adapter(hContex,mlist);
                                        adapter.setOnItemClickListener(new Adapter.ClickListener() {
                                            @Override
                                            public void onItemClick(int position, View v,List<String> mData) {
                                                Log.d("click", "onItemClick position: " + position);
                                                Intent myIntent = new Intent(Home.this, Sudionici.class);
                                                myIntent.putStringArrayListExtra("sudionici", (ArrayList<String>)mData);
                                                myIntent.putExtra("napravio","home");
                                                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivity(myIntent);
                                            }

                                            @Override
                                            public void onItemLongClick(int position, View v,List<String> mData) {
                                                Log.d("click", "onItemLongClick pos = " + position);
                                                Intent myIntent = new Intent(Home.this, Sudionici.class);
                                                myIntent.putStringArrayListExtra("sudionici",(ArrayList<String>) mData);
                                                myIntent.putExtra("napravio","home");
                                                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivity(myIntent);
                                            }
                                        });
                                        recyclerView.setAdapter(adapter);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(hContex));
                                    } else {
                                        Log.d("ne dohvati", "Error getting documents: ", task.getException());
                                    }
                                }
                            });


                } else {
                    Log.d("nije", "Error getting documents: ", task.getException());
                }
            }
        });


       /* db.collection("dogadaji").document().collection("Sudionici")
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
                            RecyclerView recyclerView = findViewById(R.id.rv_list);
                            DividerItemDecoration itemDecorator = new DividerItemDecoration(hContex, DividerItemDecoration.VERTICAL);
                            itemDecorator.setDrawable(ContextCompat.getDrawable(hContex, R.drawable.divider));
                            recyclerView.addItemDecoration(itemDecorator);
                            Adapter adapter = new Adapter(hContex,mlist);
                            adapter.setOnItemClickListener(new Adapter.ClickListener() {
                                @Override
                                public void onItemClick(int position, View v,List<String> mData) {
                                    Log.d("click", "onItemClick position: " + position);
                                    Intent myIntent = new Intent(Home.this, Sudionici.class);
                                    myIntent.putStringArrayListExtra("sudionici", (ArrayList<String>)mData);
                                    myIntent.putExtra("napravio","home");
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(myIntent);
                                }

                                @Override
                                public void onItemLongClick(int position, View v,List<String> mData) {
                                    Log.d("click", "onItemLongClick pos = " + position);
                                    Intent myIntent = new Intent(Home.this, Sudionici.class);
                                    myIntent.putStringArrayListExtra("sudionici",(ArrayList<String>) mData);
                                    myIntent.putExtra("napravio","home");
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(myIntent);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(hContex));
                        } else {
                            Log.w("nije uspio", "Error getting documents.", task.getException());
                        }
                    }
                });
*/
    }







    private void unos(Map unos,String put,String document){
        db.collection(put).document(document).set(unos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d("Dogadaji", "Added " );
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Dogadaji fail", "Error adding document", e);
                    }
                });

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




}
