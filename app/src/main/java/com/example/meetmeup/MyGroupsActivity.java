package com.example.meetmeup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;

public class MyGroupsActivity extends AppCompatActivity {

    private LinkedList<Group> groups = GroupsDummyContent.getGroups();

    private RecyclerView gRecyclerView;
    private GroupListAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygroups);

        gRecyclerView = findViewById(R.id.groupsView);
        gAdapter = new GroupListAdapter(this, groups);
        gRecyclerView.setAdapter(gAdapter);
        gRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void createNewGroup(View view) {
        Intent intent = new Intent(MyGroupsActivity.this, GroupCreateActivity.class);
        startActivityForResult(intent,1);
    }
}