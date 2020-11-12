package com.example.meetmeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MySchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
    }

    public void toShceduleEdit(View view) {
        Intent intent = new Intent(MySchedule.this, ScheduleEdit.class);
        startActivity(intent);
    }
}