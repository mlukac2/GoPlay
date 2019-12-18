package com.unipu.hr.GoPlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Kreiranje_dogadaja extends AppCompatActivity {
    private FirebaseUser currentFirebaseUser;
    final int cijenaKod = 1;
    final int lokacijaKod = 2;
    final int vrijemeKod = 3;
    final int datumKod = 4;
    String cijena;
    String lokacija;
    String vrijeme;
    String datum;

    //Button buttonLokacija = findViewById(R.id.buttonLokacija);
    //Button buttonVrijeme = findViewById(R.id.buttonVrijeme);
    //Button buttonDatum = findViewById(R.id.buttonDatum);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kreiranje_dogadaja);
        TextView sport = findViewById(R.id.dodavanjeSport);
        sport.setText(getIntent().getStringExtra("Sport"));
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
                startActivityForResult(myIntent,cijenaKod);


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
                startActivityForResult(myIntent,lokacijaKod);


            }
        });
        findViewById(R.id.buttonVrijeme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Kreiranje_dogadaja.this, Vrijeme_dogadaja.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(myIntent,vrijemeKod);


            }
        });
        findViewById(R.id.buttonDatum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Kreiranje_dogadaja.this, Datum_dogadaja.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(myIntent,datumKod);


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

        }
    }


}
