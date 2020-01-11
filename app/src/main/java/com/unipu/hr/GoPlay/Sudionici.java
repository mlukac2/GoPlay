package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.List;

public class Sudionici extends AppCompatActivity {
    Button cancel;
    Button joinLeave;
    List<String> sudionici;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudionici);
        sudionici = getIntent().getStringArrayListExtra("sudionici");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list,R.id.textSud,sudionici);
        ListView listView = (ListView) findViewById(R.id.list9);
        listView.setAdapter(adapter);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        joinLeave = findViewById(R.id.join_leave);
        if(getIntent().getStringExtra("napravio") == "home")
            joinLeave.setText("Napusti");
        if(getIntent().getStringExtra("napravio") == "dogadaji")
            joinLeave.setText("Pridruži se");
        joinLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(getIntent().getStringExtra("napravio") == "home")

                    //if(getIntent().getStringExtra("napravio") == "dogadaji")


            }
        });

    }
}
