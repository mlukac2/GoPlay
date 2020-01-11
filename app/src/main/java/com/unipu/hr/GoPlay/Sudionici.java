package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Sudionici extends AppCompatActivity {
    List<String> sudionici;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudionici);
        sudionici = getIntent().getStringArrayListExtra("sudionici");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list,R.id.textSud,sudionici);
        ListView listView = (ListView) findViewById(R.id.list9);
        listView.setAdapter(adapter);

    }
}
