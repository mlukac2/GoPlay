package com.unipu.hr.GoPlay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    String tip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = NavigacijaFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

            }
        });
        findViewById(R.id.signOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent myIntent = new Intent(Home.this, MainActivity.class);
                startActivity(myIntent);
                }


        });




    }
    public void Button1(View v) {
        ViewFlipper vf = (ViewFlipper)findViewById(R.id.vf);
        vf.setDisplayedChild(0);
    }

    public void Button2(View v) {
        ViewFlipper vf = (ViewFlipper)findViewById(R.id.vf);
        vf.setDisplayedChild(1);
    }


    public void Button3(View v) {
        ViewFlipper vf = (ViewFlipper)findViewById(R.id.vf);
        vf.setDisplayedChild(2);
    }

    public void Button4(View v) {
        ViewFlipper vf = (ViewFlipper)findViewById(R.id.vf);
        vf.setDisplayedChild(3);
    }

    public void data(String s1){
        TextView tv = (TextView)findViewById(R.id.textView3);
        tv.setText(s1);
        ViewFlipper vf = (ViewFlipper)findViewById(R.id.vf);
        vf.setDisplayedChild(4);
    }


}
