package com.example.meetmeup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GroupCreateActivity extends AppCompatActivity {

    private LinearLayout members;
    private EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);

        members = findViewById(R.id.added);
        txtEmail = findViewById(R.id.txtEmail);
    }

    public void create(View view) {
    }

    public void addMember(View view) {
        TextView newMember = new TextView(this);
        newMember.setText(txtEmail.getText());
        members.addView(newMember);
        txtEmail.setText("");
    }
}