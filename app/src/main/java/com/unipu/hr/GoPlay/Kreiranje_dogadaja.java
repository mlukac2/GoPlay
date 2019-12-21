package com.unipu.hr.GoPlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Kreiranje_dogadaja extends AppCompatActivity {
    private FirebaseUser currentFirebaseUser;
    FirebaseFirestore db;
    final int cijenaKod = 1;
    final int lokacijaKod = 2;
    final int vrijemeKod = 3;
    final int datumKod = 4;
    final int brOsoba = 5;
    int intcijena;
    int intBrOsoba;
    String sport;
    String cijena;
    String lokacija;
    String vrijeme;
    String datum;
    String brojOsoba="1";
    Date timestamp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kreiranje_dogadaja);
        sport = getIntent().getStringExtra("Sport");
        TextView sportTV = findViewById(R.id.dodavanjeSport);
        sportTV.setText(sport);
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ImageView userPicture = findViewById(R.id.userPicture);
        new ImageLoadTask(currentFirebaseUser.getPhotoUrl().toString(), userPicture).execute();
        TextView userName = findViewById(R.id.userName);
        userName.setText(currentFirebaseUser.getDisplayName());

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        findViewById(R.id.buttonCijena).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Kreiranje_dogadaja.this, Cijana_dogadaja.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(myIntent, cijenaKod);


            }
        });
        findViewById(R.id.buttonPozovi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Kreiranje_dogadaja.this, PozivPrijatelja.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(myIntent);


            }
        });
        findViewById(R.id.buttonLokacija).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Kreiranje_dogadaja.this, Lokacija_dogadaja.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(myIntent, lokacijaKod);


            }
        });
        findViewById(R.id.buttonVrijeme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Kreiranje_dogadaja.this, Vrijeme_dogadaja.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(myIntent, vrijemeKod);


            }
        });
        findViewById(R.id.buttonDatum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Kreiranje_dogadaja.this, Datum_dogadaja.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(myIntent, datumKod);


            }
        });
        findViewById(R.id.buttonBrojOsoba).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Kreiranje_dogadaja.this, broj_osoba.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(myIntent, brOsoba);

            }
        });

        findViewById(R.id.buttonKreirajDogadaj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = FirebaseFirestore.getInstance();
                FirebaseUserMetadata metadata = currentFirebaseUser.getMetadata();

                Map<String, Object> dogadaj = new HashMap<>();
                dogadaj.put("userId", currentFirebaseUser.getUid());
                dogadaj.put("sport", sport);
                dogadaj.put("lokacija", lokacija);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    String temp = datum+" "+vrijeme;
                    Date parsedDate = dateFormat.parse(temp);
                    timestamp = new java.sql.Timestamp(parsedDate.getTime());

                } catch(Exception e) { //this generic but you can control another types of exception
                    Log.e("date format",e.toString());
                }

                dogadaj.put("datum", timestamp);
                try {
                    intcijena = Integer.parseInt(cijena);
                }catch(Exception e) {
                    Log.e("string to int cijena",e.toString());
                }
                try {
                    intBrOsoba = Integer.parseInt(brojOsoba);
                }catch(Exception e) {
                    Log.e("string to int cijena",e.toString());
                }
                dogadaj.put("brOsoba",intBrOsoba);
                int tempUdio = intcijena / intBrOsoba;
                if(cijena != "0")
                    if(tempUdio != 0)
                    dogadaj.put("udio",tempUdio);
                    else
                        return;
                else
                    dogadaj.put("uplacno",0);
                dogadaj.put("uplacneno",tempUdio);
                dogadaj.put("cijena",intcijena);
                dogadaj.put("sudionici", Arrays.asList(currentFirebaseUser.getUid()));

                final DocumentReference user = db.collection("korisnici").document(currentFirebaseUser.getUid());



                db.collection("dogadaji")
                        .add(dogadaj)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                user.update("dogadaji", FieldValue.arrayUnion(documentReference.getId()));
                                Log.d("unos dogadaja", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("unos dogadaja", "Error adding document", e);
                            }
                        });


            }

        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (cijenaKod) : {
                if (resultCode == Activity.RESULT_OK) {
                    cijena = data.getStringExtra("cijena");
                    Button btnCijena = (Button)findViewById(R.id.buttonCijena);
                    btnCijena.setText(cijena+" kn");

                }
                break;
            }
            case (vrijemeKod) : {
                if (resultCode == Activity.RESULT_OK) {
                    vrijeme = data.getStringExtra("vrijeme");
                    Button btnVrijeme = (Button)findViewById(R.id.buttonVrijeme);
                    btnVrijeme.setText(vrijeme);

                }
                break;
            }
            case (lokacijaKod) : {
                if (resultCode == Activity.RESULT_OK) {
                    lokacija = data.getStringExtra("lokacija");
                    Button btnVrijeme = (Button)findViewById(R.id.buttonLokacija);
                    btnVrijeme.setText(lokacija);

                }
                break;
            }

            case (datumKod) : {
                if (resultCode == Activity.RESULT_OK) {
                    datum = data.getStringExtra("datum");
                    Button btnDatum = (Button)findViewById(R.id.buttonDatum);
                    btnDatum.setText(datum);

                }
                break;
            }
            case (brOsoba) : {
                if (resultCode == Activity.RESULT_OK) {
                    brojOsoba = data.getStringExtra("brOsoba");
                    Button bOsoba = (Button)findViewById(R.id.buttonBrojOsoba);
                    bOsoba.setText(brojOsoba);
                }
                break;
            }

        }
    }


}
