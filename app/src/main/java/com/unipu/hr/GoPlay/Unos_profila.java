package com.unipu.hr.GoPlay;

import android.content.SharedPreferences;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Unos_profila extends AppCompatActivity {


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_profil);
        SharedPreferences preferences = getSharedPreferences("preferences",
                MODE_PRIVATE);

        ImageView userPicture = findViewById(R.id.userPicture);
        new ImageLoadTask(preferences.getString("picture", "0"), userPicture).execute();
        TextView userName = findViewById(R.id.userName);
        userName.setText(preferences.getString("name", "0"));

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }
}
