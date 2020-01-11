package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

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

        CalendarView view = new CalendarView(this);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        long date = calendar.getTime().getTime();
        view.setMinDate(date);
        setContentView(view);

        view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
                Intent resultIntent = new Intent();
                Log.d("datum M", String.valueOf(month));
                String datum = date+"."+(month+1)+"."+year;
                Log.d("datum", datum);
                resultIntent.putExtra("datum", datum);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });
    }
}
