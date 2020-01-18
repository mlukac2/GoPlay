package com.unipu.hr.GoPlay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class Novcanik extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novcanik);
        TextView novacTxt = findViewById(R.id.novcanik_gumb);
        SharedPreferences preferences = getSharedPreferences("preferences",
                MODE_PRIVATE);
        novacTxt.setText(String.valueOf(preferences.getLong("novac",0)));
        findViewById(R.id.fab3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = NavigacijaFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

            }
        });

    }
    public void otvoriKarticu(View v) {
        Intent myIntent = new Intent(Novcanik.this, Kreditna_kartica.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);
    }
    public void Button1(View v) {
        Intent myIntent = new Intent(Novcanik.this, Home.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);


    }

    public void Button2(View v) {
        Intent myIntent = new Intent(Novcanik.this, Dogadaji.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);


    }



    public void Button3(View v) {
        Intent myIntent = new Intent(Novcanik.this, Profile.class);
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
