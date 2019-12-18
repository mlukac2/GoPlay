package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Odabir_igralista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.odabir_igralista);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        findViewById(R.id.buttonVidikovac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("igraliste", "Vidikovac" );
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });
        findViewById(R.id.buttonStoja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("igraliste", "Stoja" );
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });
        findViewById(R.id.buttonVerudela).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("igraliste", "Verudela" );
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });
    }

}
