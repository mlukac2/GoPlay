package com.unipu.hr.GoPlay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kontakt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontakt);

        final EditText ime        = (EditText) findViewById(R.id.your_name);
        final EditText email_adresa       = (EditText) findViewById(R.id.your_email);
        final EditText naslov     = (EditText) findViewById(R.id.your_subject);
        final EditText poruka     = (EditText) findViewById(R.id.your_message);

        Button email = (Button) findViewById(R.id.post_message);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name      = ime.getText().toString();
                String email     = email_adresa.getText().toString();
                String subject   = naslov.getText().toString();
                String message   = poruka.getText().toString();
                if (TextUtils.isEmpty(name)){
                    ime.setError("Unesite svoje ime");
                    ime.requestFocus();
                    return;
                }
                Boolean onError = false;
                if (!isValidEmail(email)) {
                    onError = true;
                    email_adresa.setError("Krivi email");
                    return;
                }
                if (TextUtils.isEmpty(subject)){
                    naslov.setError("Unesite naslov");
                    naslov.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(message)){
                    poruka.setError("Unesite svoju poruku");
                    poruka.requestFocus();
                    return;
                }
                Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);
                sendEmail.setType("plain/text");
                sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"amederal@unipu.hr"});
                sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
                        "name:"+name+'\n'+"Email ID:"+email+'\n'+"Message:"+'\n'+message);

                startActivity(Intent.createChooser(sendEmail, "Send mail..."));
            }
        });
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void BackButton3(View view) {
        Intent myIntent = new Intent(Kontakt.this, MainActivity.class);
        startActivity(myIntent);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}
