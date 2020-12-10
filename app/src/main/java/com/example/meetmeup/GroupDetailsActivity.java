package com.example.meetmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetmeup.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;

public class GroupDetailsActivity extends AppCompatActivity {

    TextView titleGroup, membersEmails;
    private LinearLayout members;
    private RecyclerView aRecyclerView;
    private LinkedList<Activity> activities = new LinkedList<Activity>();
    private ActivityListAdapter aAdapter;
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
        members = findViewById(R.id.members);
        aRecyclerView = findViewById(R.id.activitiesView);
        aRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        groupID = intent.getStringExtra("group_id");

        groupRef.document(groupID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            titleGroup.setText(task.getResult().getString("title"));
                            ArrayList<String> groupMembers = (ArrayList<String>)task.getResult().get("members");
                            for (String email: groupMembers) {
                                TextView newMember = new TextView(GroupDetailsActivity.this);
                                newMember.setText(email);
                                newMember.setTextSize(20);
                                newMember.setTextColor(0xFF343A40);
                                members.addView(newMember);
                            }
                            Log.d("activities", task.getResult().getData().toString());
                            //Log.d("activities", task.getResult().get("Activities").toString());
                        }else{
                            Log.d("error", "Error getting documents: ", task.getException());
                        }
                    }
                });

        groupRef.document(groupID).collection("Activities").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Activity newActivity = new Activity();
                                newActivity.setTitle(documentSnapshot.getString("title"));
                                newActivity.setDate(documentSnapshot.getString("date"));
                                newActivity.setTime(documentSnapshot.getString("time"));
                                newActivity.setPlace(documentSnapshot.getString("place"));
                                newActivity.setCategory(documentSnapshot.getString("category"));
                                newActivity.setId(documentSnapshot.getId());
                                newActivity.setGroupID(groupID);
                                activities.add(newActivity);

                                Log.d("activities", documentSnapshot.getData().toString());
                            }
                            aAdapter = new ActivityListAdapter(GroupDetailsActivity.this, activities);
                            aRecyclerView.setAdapter(aAdapter);
                        }else{
                            Log.d("error", "Error getting documents: ", task.getException());
                        }

                    }
                });
    }

    public void toActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateFrame.class);
        intent.putExtra("group_id", groupID);
        startActivity(intent);
    }
}