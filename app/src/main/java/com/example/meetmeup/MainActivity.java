package com.example.meetmeup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.meetmeup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
//    Button bLoggin, bSignup, bSignupNewAccount, bLoginLogin;
//    FirebaseDatabase db; //For Connection to DataBase
//    FirebaseAuth auth; // For Authorization
//    DatabaseReference users; // For work with tables in DB


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#343a40"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //initialisation
//        bLoggin = findViewById(R.id.bLoggin);
//        bSignup = findViewById(R.id.bSignup);
//        bSignupNewAccount = findViewById(R.id.bSignupNewAccount);
//        bLoginLogin = findViewById(R.id.bLoginLogin);

//        auth = FirebaseAuth.getInstance();
//        db = FirebaseDatabase.getInstance();
//        users = db.getReference("Users"); //"Users" is a name of table
//
//        bLoggin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoginWindow();
//            }
//        });
//        bSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showCreateNewAccountWindow();
//            }
//        });
//    }
//    private void showLoginWindow(){
//
//    }
//    private void showCreateNewAccountWindow(){
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle("Create new account");
//        dialog.setMessage("Enter your data");
//
//        //take "create_new_account" and put it in "dialog"
//        LayoutInflater inflater = LayoutInflater.from(this); //create object inflater
//        View createNewAccount_Window = inflater.inflate(R.layout.activity_signup, null); //put window in inflater
//        dialog.setView(createNewAccount_Window);
//
//        //take the data from reg fields
//        // MaterialTextInputPicker email = createNewAccount_Window.findViewById(R.id.editTextTextEmailAddress2);
//        EditText text = (EditText)findViewById(R.id.editTextTextEmailAddress2);
//        String value = text.toString();
//
    }

    public void toLogin(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void toSignup(View view) {
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}