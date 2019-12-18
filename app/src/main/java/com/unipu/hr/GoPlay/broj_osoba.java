package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

public class broj_osoba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broj_osoba);
        final TextView editBrOsoba = findViewById(R.id.editBrosoba);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        editBrOsoba.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if(editBrOsoba.getText().length()!= 0) {
                    if (Integer.parseInt(editBrOsoba.getText().toString()) < 1) {
                        editBrOsoba.setText("");
                        editBrOsoba.setError("Broj osoba treba biti veci od 1");
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });
        findViewById(R.id.brOsobaGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("brOsoba", editBrOsoba.getText().toString() );
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });

    }

}
