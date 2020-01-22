package com.unipu.hr.GoPlay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;


import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

        private static final int RC_SIGN_IN = 123;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button mButton;
            mButton = findViewById(R.id.sign_in);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }

            });

        }

        private void signIn() {

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build()
                            ))
                            .build()
                    ,RC_SIGN_IN);
                    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                Intent myIntent = new Intent(MainActivity.this, Home.class);
                if (getIntent().getExtras() != null) {
                    myIntent.putExtra("dogadaj",getIntent().getStringExtra("dogadaj"));
                }
                startActivity(myIntent);
            } else {
                Snackbar.make(findViewById(R.id.main_layout), response.getError().getErrorCode(), Snackbar.LENGTH_SHORT).show();

            }
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