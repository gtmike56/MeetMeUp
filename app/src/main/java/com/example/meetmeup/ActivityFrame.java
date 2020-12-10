package com.example.meetmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ActivityFrame extends AppCompatActivity {

    private TextView title, category, date, time, place;
    String activityID, groupID;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference groupRef = db.collection("Groups");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actifity_frame);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);

        title = findViewById(R.id.textView_activityTitle);
        category = findViewById(R.id.textView_activityCategory);
        date = findViewById(R.id.textView_activityDate);
        time = findViewById(R.id.textView_activityTime);

        Intent intent = getIntent();
        activityID = intent.getStringExtra("activity_id");
        groupID = intent.getStringExtra("group_id");

        groupRef.document(groupID).collection("Activities").document(activityID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            title.setText(task.getResult().getString("title"));
                            category.setText(task.getResult().getString("category"));
                            date.setText(task.getResult().getString("date"));
                            time.setText(task.getResult().getString("time"));
                            Log.d("activities", task.getResult().getData().toString());
                        }else{
                            Log.d("error", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }
}