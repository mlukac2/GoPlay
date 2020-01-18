package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Kreditna_kartica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kreditna_kartica);
        findViewById(R.id.kreditna_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }});
    }
    public void backButton(View v) {
        finish();
    }

}
