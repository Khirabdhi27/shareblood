package com.example.hp.shareblood;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyProfile extends AppCompatActivity {
    private TextView text,email,blood,gender;
    private EditText age,name,number,location,address;
    private Button save;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference MdatabaseReference = firebaseDatabase.getReference();
    private DatabaseReference MCdatabaseReference = MdatabaseReference.child("users");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        text = (TextView) findViewById(R.id.textView5);
        age=(EditText)findViewById(R.id.age);
        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.number);
        blood=(TextView)findViewById(R.id.typeblood);
        email=(TextView)findViewById(R.id.email);
        gender=(TextView)findViewById(R.id.radiogroup);
        location=(EditText)findViewById(R.id.location);
        address=(EditText)findViewById(R.id.address);
        save=(Button)findViewById(R.id.datasave);
        String userid = getIntent().getStringExtra("userid");
        Toast.makeText(getApplicationContext(), userid, Toast.LENGTH_SHORT).show();
        MCdatabaseReference.child(userid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                name.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("age").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                age.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("blood").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                blood.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("gender").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                gender.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                number.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                email.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("location").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                location.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                address.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                email.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MCdatabaseReference.child(userid).child("password").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String password=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        try {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = getIntent().getStringExtra("userid");
                    String name1 = name.getText().toString();
                    String age1 = age.getText().toString();
                    String number1 = number.getText().toString();
                    String location1 = location.getText().toString();
                    String address1 = address.getText().toString();
                    MCdatabaseReference.child(userid).child("name").setValue(name1);
                    MCdatabaseReference.child(userid).child("age").setValue(age1);
                    MCdatabaseReference.child(userid).child("number").setValue(number1);
                    MCdatabaseReference.child(userid).child("location").setValue(location1);
                    MCdatabaseReference.child(userid).child("address").setValue(address1);
                }
            });

        } catch (Exception exception) {

        }
    }

}