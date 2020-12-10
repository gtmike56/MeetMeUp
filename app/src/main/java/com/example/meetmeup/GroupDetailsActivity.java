package com.example.meetmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class GroupDetailsActivity extends AppCompatActivity {

    TextView titleGroup, membersEmails;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference groupRef = db.collection("Groups");
    String groupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);



        titleGroup = findViewById(R.id.groupDetails_txtTitle);
        membersEmails = findViewById(R.id.groupDetails_txtEmails);

        Intent intent = getIntent();
        groupID = intent.getStringExtra("group_id");

        groupRef.document(groupID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            titleGroup.setText(task.getResult().getString("title"));
                            membersEmails.setText(task.getResult().get("members").toString());
                        }else{
                            Log.d("error", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void toActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateFrame.class);
        intent.putExtra("group_id", groupID);
        ((Activity) GroupDetailsActivity.this).startActivity(intent);
    }
}