package com.unipu.hr.GoPlay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
        private Button mButton;
        private static final int RC_SIGN_IN = 518;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mButton = findViewById(R.id.sign_in);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }

            });

        }

        private void signIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build()
                            ))
                            .build()
                    ,RC_SIGN_IN);
                    }

                    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 518) {
            Intent myIntent = new Intent(MainActivity.this, Home.class);
            startActivity(myIntent);
        }


    }

    public void Button5(View view) {
        Intent myIntent = new Intent(MainActivity.this, Kontakt.class);
        startActivity(myIntent);
    }

    public void Button6(View view) {
        Intent myIntent = new Intent(MainActivity.this, Onama.class);
        startActivity(myIntent);
    }

    public void Button7(View view) {
        Intent myIntent = new Intent(MainActivity.this, Uvjetikoristenja.class);
        startActivity(myIntent);
    }

}