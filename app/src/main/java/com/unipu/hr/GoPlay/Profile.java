package com.unipu.hr.GoPlay;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;


public class Profile extends AppCompatActivity {
    private FirebaseUser currentFirebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ImageView userPicture = findViewById(R.id.userPicture);
        new ImageLoadTask(currentFirebaseUser.getPhotoUrl().toString(), userPicture).execute();
        TextView userName = findViewById(R.id.userName);
        userName.setText(currentFirebaseUser.getDisplayName());

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
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
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
    public void data(String s1){
        Intent myIntent = new Intent(Profile.this, Kreiranje_dogadaja.class);
        myIntent.putExtra("Sport", s1);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(myIntent);

    }

}
