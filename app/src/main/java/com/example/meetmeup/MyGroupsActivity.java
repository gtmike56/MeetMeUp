package com.example.meetmeup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MyGroupsActivity extends AppCompatActivity {

    private LinkedList<Group> notes = new LinkedList<>();

    private RecyclerView gRecyclerView;
    //private GroupListAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygroups);

        gRecyclerView = findViewById(R.id.groupsView);
        //gAdapter = new GroupListAdapter(this, notes);
        //mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}