package com.example.meetmeup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyAccount extends AppCompatActivity {

    TextView username;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable  colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        username = findViewById(R.id.myAccount_username);
        username.setText(user.getEmail());

    }

    public void toMySchedule(View view) {
        Intent intent = new Intent(MyAccount.this, MySchedule.class);
        startActivity(intent);
    }

    public void toMyGroups(View view) {
        Intent intent = new Intent(MyAccount.this, MyGroupsActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}