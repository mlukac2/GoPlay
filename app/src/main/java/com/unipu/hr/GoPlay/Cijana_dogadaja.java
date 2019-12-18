package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Cijana_dogadaja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cijena_dogadaja);
        final TextView editCijena = findViewById(R.id.editCiiena);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        findViewById(R.id.cijenaGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("cijena", editCijena.getText().toString() );
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });
    }
}
