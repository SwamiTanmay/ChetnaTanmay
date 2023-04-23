package com.example.chetnatanmay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_profile extends AppCompatActivity {

    Button mHome, mSave_msg;

    FirebaseAuth fAuth;
    DatabaseReference fRef;
    EditText mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fAuth = FirebaseAuth.getInstance();


        mHome = findViewById(R.id.home);
        mMessage = findViewById(R.id.message);
        mSave_msg = findViewById(R.id.save_msg);

        mSave_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                final String uid=current_user.getUid();
                fRef  = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("messages");

                String msg = mMessage.getText().toString().trim();

                fRef.child(msg).setValue(msg);

            }
        });
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
}