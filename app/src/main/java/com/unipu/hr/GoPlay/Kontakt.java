package com.unipu.hr.GoPlay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class Kontakt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontakt);



    }

    public void BackButton3(View view) {
        Intent myIntent = new Intent(Kontakt.this, MainActivity.class);
        startActivity(myIntent);
    }
}
