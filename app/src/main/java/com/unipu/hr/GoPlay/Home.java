package com.unipu.hr.GoPlay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Home extends AppCompatActivity {

    private FirebaseUser currentFirebaseUser;
    FirebaseFirestore db;

// ...

    String tip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = NavigacijaFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

            }
        });




        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(this, " " + currentFirebaseUser.getUid() +" " +currentFirebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();


        db = FirebaseFirestore.getInstance();
        FirebaseUserMetadata metadata = currentFirebaseUser.getMetadata();
        if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
            Map<String, Object> korisnik = new HashMap<>();
            korisnik.put("novac", 0);
            unos(korisnik,"korisnici",currentFirebaseUser.getUid().toString());



        }


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

    public void data(String s1){
        Intent myIntent = new Intent(Home.this, Kreiranje_dogadaja.class);
        myIntent.putExtra("Sport", s1);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);

    }



}
