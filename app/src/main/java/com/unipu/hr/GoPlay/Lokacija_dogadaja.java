package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lokacija_dogadaja extends AppCompatActivity {
    final int lokacijaKod = 1;
    String igralista;
    String lokacija = "Pula";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lokacija_dogadaja);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        findViewById(R.id.lokacijaGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Lokacija_dogadaja.this, Odabir_igralista.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(myIntent,lokacijaKod);
            }
        });
        TextView lokacija = findViewById(R.id.editLokacija);
        lokacija.setText("Pula");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (lokacijaKod) : {
                if (resultCode == Activity.RESULT_OK) {
                    igralista = data.getStringExtra("igraliste");
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("lokacija", lokacija+", "+igralista );
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();

                }
                break;
            }


        }
    }
}
