package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Onama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onama);
    }
    public void BackButton2(View view) {
        Intent myIntent = new Intent(Onama.this, MainActivity.class);
        startActivity(myIntent);
    }
}
