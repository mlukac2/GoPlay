package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;


public class Vrijeme_dogadaja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vrijeme_dogadaja);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        final NumberPicker numSati = findViewById(R.id.sat);

        numSati.setMinValue(00);
        numSati.setMaxValue(23);
        numSati.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });

        final NumberPicker minute = findViewById(R.id.minute);

        minute.setMinValue(00);
        minute.setMaxValue(59);
        minute.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });

        findViewById(R.id.odaberiVrijeme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                String vrijeme = numSati.getValue()+":"+minute.getValue();
                resultIntent.putExtra("vrijeme",vrijeme );
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });






    }


}
