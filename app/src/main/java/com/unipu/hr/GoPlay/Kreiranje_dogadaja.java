package com.unipu.hr.GoPlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.util.HashMap;
import java.util.Map;



public class Kreiranje_dogadaja extends AppCompatActivity implements UpdateNovac  {

    FirebaseFirestore db;
    String userID;
    final int cijenaKod = 1;
    final int lokacijaKod = 2;
    final int vrijemeKod = 3;
    final int datumKod = 4;
    final int brOsoba = 5;
    int intcijena;
    int intBrOsoba;
    long novac;
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
        final Context childContext = getApplicationContext();
        final SharedPreferences preferences = getSharedPreferences("preferences",
                MODE_PRIVATE);
        userID = preferences.getString("user_id", "0");
        sport = getIntent().getStringExtra("Sport");
        TextView sportTV = findViewById(R.id.dodavanjeSport);
        sportTV.setText(sport);
        ImageView userPicture = findViewById(R.id.userPicture);
        new ImageLoadTask(preferences.getString("picture", "0"), userPicture).execute();
        TextView userName = findViewById(R.id.userName);
        userName.setText(preferences.getString("name", "0"));
        novac = preferences.getLong("novac", 0);

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


                Map<String, Object> dogadaj = new HashMap<>();
                final Map<String, Object> sudinici = new HashMap<>();
                dogadaj.put("userId", userID);
                dogadaj.put("sport", sport);
                dogadaj.put("lokacija", lokacija);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    String temp = datum+" "+vrijeme;
                    Log.d("unos temp",temp);
                    Date parsedDate = dateFormat.parse(temp);
                    Log.d("unos parse",parsedDate.toString());
                    timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    Log.d("unos time",timestamp.toString());

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
                final int tempUdio = intcijena / intBrOsoba;

                dogadaj.put("udio",tempUdio);
                dogadaj.put("uplaceno",tempUdio);
                dogadaj.put("brSudionika", 1);
                dogadaj.put("cijena",intcijena);
                sudinici.put("brisanje", false);

                final DocumentReference user = db.collection("korisnici").document(userID);

                if (tempUdio<=novac){
                db.collection("dogadaji")
                        .add(dogadaj)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                user.update("dogadaji", FieldValue.arrayUnion(documentReference.getId()));
                                user.update("novac", FieldValue.increment(-tempUdio));

                                db.collection("dogadaji").document(documentReference.getId()).collection("Sudionici").document(userID)
                                        .set(sudinici)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("sudionici uspio", "DocumentSnapshot successfully written!");



                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("unos dogadaja", "Error adding document", e);
                                            }
                                        });

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
                else{
                    Toast.makeText(childContext, "Nemate dovoljno novca na računu!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                finish();
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
                    Button btnCijena = findViewById(R.id.buttonCijena);
                    btnCijena.setText(cijena+" kn");

                }
                break;
            }
            case (vrijemeKod) : {
                if (resultCode == Activity.RESULT_OK) {
                    vrijeme = data.getStringExtra("vrijeme");
                    Button btnVrijeme = findViewById(R.id.buttonVrijeme);
                    btnVrijeme.setText(vrijeme);

                }
                break;
            }
            case (lokacijaKod) : {
                if (resultCode == Activity.RESULT_OK) {
                    lokacija = data.getStringExtra("lokacija");
                    Button btnVrijeme = findViewById(R.id.buttonLokacija);
                    btnVrijeme.setText(lokacija);

                }
                break;
            }

            case (datumKod) : {
                if (resultCode == Activity.RESULT_OK) {
                    datum = data.getStringExtra("datum");
                    Button btnDatum = findViewById(R.id.buttonDatum);
                    btnDatum.setText(datum);

                }
                break;
            }
            case (brOsoba) : {
                if (resultCode == Activity.RESULT_OK) {
                    brojOsoba = data.getStringExtra("brOsoba");
                    Button bOsoba = findViewById(R.id.buttonBrojOsoba);
                    bOsoba.setText(brojOsoba);
                }
                break;
            }

        }
    }


}
