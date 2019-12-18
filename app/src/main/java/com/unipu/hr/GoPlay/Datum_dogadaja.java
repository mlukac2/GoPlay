package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

public class Datum_dogadaja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datum_dogadaja);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        //final TextView editDatum = findViewById(R.id.editDatum);

        /*
        findViewById(R.id.datumGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("datum", editDatum.getText().toString() );
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });*/

        CalendarView view = new CalendarView(this);
        setContentView(view);

        view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
                Intent resultIntent = new Intent();
                String datum = date+"."+month+"."+year;
                resultIntent.putExtra("datum", datum);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });
    }
}
