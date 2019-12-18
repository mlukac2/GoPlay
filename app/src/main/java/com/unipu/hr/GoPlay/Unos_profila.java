package com.unipu.hr.GoPlay;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Unos_profila extends AppCompatActivity {
    private FirebaseUser currentFirebaseUser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_profil);

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ImageView userPicture = findViewById(R.id.userPicture);
        new ImageLoadTask(currentFirebaseUser.getPhotoUrl().toString(), userPicture).execute();
        TextView userName = findViewById(R.id.userName);
        userName.setText(currentFirebaseUser.getDisplayName());

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }
}
