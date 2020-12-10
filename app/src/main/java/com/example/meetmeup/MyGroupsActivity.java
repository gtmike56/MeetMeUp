package com.example.meetmeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyGroupsActivity extends AppCompatActivity {

    private LinkedList<Group> groups = new LinkedList<Group>();
    private RecyclerView gRecyclerView;
    private GroupListAdapter gAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    CollectionReference groupRef = db.collection("Groups");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygroups);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);


        gRecyclerView = findViewById(R.id.groupsView);

        gRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        groupRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                ArrayList<String> members = (ArrayList<String>) documentSnapshot.getData().get("members");
                                if(!members.contains(user.getEmail())){
                                    continue;
                                }
                                Group newGroup = new Group();
                                String title = documentSnapshot.getString("title");
                                newGroup.setName(title);
                                newGroup.setEmails(members);
                                newGroup.setDocumentId(documentSnapshot.getId());

                                groups.add(newGroup);
                            }
                            gAdapter = new GroupListAdapter(MyGroupsActivity.this, groups);
                            gRecyclerView.setAdapter(gAdapter);
                        }else{
                            Log.d("error", "Error getting documents: ", task.getException());
                        }

                    }
                });
    }

    public void createNewGroup(View view) {
        Intent intent = new Intent(MyGroupsActivity.this, GroupCreateActivity.class);
        startActivityForResult(intent,1);
    }
}