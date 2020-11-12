package com.example.meetmeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
    }

    public void toMySchedule(View view) {
        Intent intent = new Intent(MyAccount.this, MySchedule.class);
        startActivity(intent);
    }

    public void toMyGroups(View view) {
        Intent intent = new Intent(MyAccount.this, MyGroupsActivity.class);
        startActivity(intent);
    }
}