package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Termofuse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termofuse);
    }

    public void BackButton1(View view) {
        Intent myIntent = new Intent(Termofuse.this, MainActivity.class);
        startActivity(myIntent);
    }
}
