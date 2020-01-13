package com.unipu.hr.GoPlay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;



public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        ImageView userPicture = findViewById(R.id.userPicture);
        SharedPreferences preferences = getSharedPreferences("preferences",
                MODE_PRIVATE);
        new ImageLoadTask(preferences.getString("picture", "0"), userPicture).execute();
        TextView userName = findViewById(R.id.userName);
        userName.setText(preferences.getString("name", "0"));

        findViewById(R.id.signOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();

            }
        });
        findViewById(R.id.sportovi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Profile.this, Unos_profila.class);
                startActivity(myIntent);

            }

        });
        findViewById(R.id.fab4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = NavigacijaFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

            }
        });
    }
    public void Button1(View v) {
               Intent myIntent = new Intent(Profile.this, Home.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);


    }

    public void Button2(View v) {
        Intent myIntent = new Intent(Profile.this, Dogadaji.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);


    }





    public void Button4(View v) {
        Intent myIntent = new Intent(Profile.this, Novcanik.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);
    }
    private void signout(){

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        startActivity(new Intent(Profile.this, MainActivity.class));
                        finish();
                    }
                });
    }


}
