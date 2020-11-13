package com.example.meetmeup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable  colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);
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
        finish();
    }
}