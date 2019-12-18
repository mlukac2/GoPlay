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



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        CheckBox amater,rekreativac,profesionalac;
        amater = (CheckBox)findViewById(R.id.amater);
        rekreativac = (CheckBox)findViewById(R.id.rekreativac);
        profesionalac = (CheckBox)findViewById(R.id.profesionalac);

/*
        switch(view.getId()) {
            case R.id.amater:
                if (checked)
                    nogometNapredni.setChecked(false);
            case R.id.nogometNapredni:
                if (checked)
                    amater.setChecked(false);
            case R.id.rekreativac:
                if (checked)
                    kosarkaNapredni.setChecked(false);
            case R.id.kosarkaNapredni:
                if (checked)
                    rekreativac.setChecked(false);
            case R.id.tenisPocetnik:
                if (checked)
                    profesionalac.setChecked(false);
            case R.id.profesionalac:
                if (checked)
                    tenisPocetnik.setChecked(false);

        }
    }
    public void unosProfilSpremi(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        List<String> sportovi = new ArrayList<String>();

        switch(view.getId()) {
            case R.id.nogometPocetnik:
                if (checked)
                    sportovi.add("nogometPocetnik");
            case R.id.nogometNapredni:
                if (checked)
                    sportovi.add("nogometNapredni");
            case R.id.kosarkaPocetnik:
                if (checked)
                    sportovi.add("kosarkaPocetnik");
            case R.id.kosarkaNapredni:
                if (checked)
                    sportovi.add("kosarkaNapredni");
            case R.id.tenisPocetnik:
                if (checked)
                    sportovi.add("tenisPocetnik");
            case R.id.tenisNapredni:
                if (checked)
                    sportovi.add("tenisPocetnik");
        }
        Map<String, Object> korisnik = new HashMap<>();
        korisnik.put("Sport", sportovi);
        unos(korisnik,"korisnici",currentFirebaseUser.getUid().toString());
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

    public void data(String s1){
        Intent myIntent = new Intent(Home.this, Kreiranje_dogadaja.class);
        myIntent.putExtra("Sport", s1);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);

    }



}
