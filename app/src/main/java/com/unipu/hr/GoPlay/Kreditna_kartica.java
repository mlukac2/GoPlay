package com.unipu.hr.GoPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class Kreditna_kartica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kreditna_kartica);
        final SharedPreferences preferences = getSharedPreferences("preferences",
                MODE_PRIVATE);
        final String userID = preferences.getString("user_id", "0");
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        findViewById(R.id.kreditna_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtNovac = findViewById(R.id.txtNovac);
                int novac = Integer.parseInt(txtNovac.getText().toString());
                db.collection("korisnici").document(userID).update("novac", novac);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("novac", novac);
                editor.commit();
                finish();
            }});
    }
    public void backButton(View v) {
        finish();
    }

}
