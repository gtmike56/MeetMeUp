package com.example.meetmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);

        mEmail = findViewById(R.id.Login_editText_Email);
        mPassword = findViewById(R.id.Login_editText_password);
        progressBar = findViewById(R.id.Login_ProgressBar);
        fAuth = FirebaseAuth.getInstance();
        progressBar.getIndeterminateDrawable().setColorFilter(0xFF343A40, PorterDuff.Mode.MULTIPLY);
        error = findViewById(R.id.Login_errorMessage);
    }

    public void toMyAccount(View view) {
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            mEmail.setError("Email is required");
            return;
        }

        if(TextUtils.isEmpty(password)){
            mPassword.setError("Password is required");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Welcome back! " + email, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MyAccount.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    error.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}