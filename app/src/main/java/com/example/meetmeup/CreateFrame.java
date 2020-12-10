package com.example.meetmeup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateFrame extends AppCompatActivity {
    String groupID;
    EditText titleActivity, dateActivity, timeActivity, categoryActivity, placeActivity;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference groupRef = db.collection("Groups");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_frame);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);

        titleActivity = findViewById(R.id.editTextText_title);
        dateActivity = findViewById(R.id.editTextText_date);
        timeActivity = findViewById(R.id.editTextText_time);
        categoryActivity = findViewById(R.id.editTextText_category);
        placeActivity = findViewById(R.id.editTextText_place);

        Intent intent = getIntent();
        groupID = intent.getStringExtra("group_id");
    }

    public void pickTheDate(View view) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateActivity.setText(year+ "/" + (month+1) + "/" + day);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void pickTheTime(View view) {
        Calendar c = Calendar.getInstance();
        final int mHours = c.get(Calendar.HOUR_OF_DAY);
        final int mMinutes = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours , int minutes) {
                timeActivity.setText(hours+ ":" + minutes);
            }
        }, mHours, mMinutes, true);
        timePickerDialog.show();
    }

    public void createNewActivity(View view) {

        String titleActivityText = titleActivity.getText().toString();
        String dateActivityText = dateActivity.getText().toString();
        String timeActivityText = timeActivity.getText().toString();
        String categoryActivityText = categoryActivity.getText().toString();
        String placeActivityText = placeActivity.getText().toString();

        Map<String, Object> activity = new HashMap<>();
        activity.put("title", titleActivityText);
        activity.put("date", dateActivityText);
        activity.put("time", timeActivityText);
        activity.put("category", categoryActivityText);
        activity.put("place", placeActivityText);

        db.collection("Groups").document(groupID).collection("Activities").add(activity);
        Toast.makeText(this, "Your Activity was saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}