package com.example.meetmeup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupCreateActivity extends AppCompatActivity {

    private LinearLayout members;
    private EditText txtEmail, titleGroup;
    private int counter;
    private Group group;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String KEY_TITLE = "title";
    private static final String KEY_MEMBERS = "members";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);

        members = findViewById(R.id.added);
        txtEmail = findViewById(R.id.txtEmail);
        titleGroup = findViewById(R.id.txtName);
        counter = 0;
    }

    public void create(View view) {
        String title = titleGroup.getText().toString();
        group = new Group(title);
        for(int i= 1; i<=counter; i++){
            String emailMember = ((TextView) findViewById(i)).getText().toString();
            group.addMember(emailMember);
        }

        Map<String, Object> groupMap = new HashMap<>();
        groupMap.put(KEY_TITLE, title);
        groupMap.put(KEY_MEMBERS, group.getEmails());

        db.collection("Groups").add(groupMap);
        Toast.makeText(this, "Your group was created!", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void addMember(View view) {
        counter += 1;
        TextView newMember = new TextView(this);
        newMember.setId(counter);
        newMember.setText(txtEmail.getText());
        newMember.setTextSize(20);
        newMember.setTextColor(0xFF343A40);
        members.addView(newMember);
        txtEmail.setText("");
    }
}