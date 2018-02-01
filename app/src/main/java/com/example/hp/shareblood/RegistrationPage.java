package com.example.hp.shareblood;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationPage extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName,inputAge,inputMobile,inputlocation,inputUsername,inputEmail,inputPassword,inputCpassword,inputAddress;
    private Button btnSave;
    private Spinner bloodhaving;
    private RadioGroup rg;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    public String userId;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        inputName=(EditText) findViewById(R.id.name);
        inputAge=(EditText) findViewById(R.id.age);
        bloodhaving=(Spinner)findViewById(R.id.typeblood);
        rg=(RadioGroup)findViewById(R.id.radiogroup);
        inputMobile=(EditText) findViewById(R.id.number);
        inputlocation=(EditText) findViewById(R.id.location);
        inputUsername=(EditText) findViewById(R.id.userName);
        inputEmail=(EditText) findViewById(R.id.email);
        inputPassword=(EditText) findViewById(R.id.password);
        inputCpassword=(EditText) findViewById(R.id.cpassword);
        inputAddress=(EditText) findViewById(R.id.address);
        btnSave=(Button) findViewById(R.id.dataregistration);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("share blood").child("contacts").child("hello");
        mFirebaseInstance.getReference("app_title").setValue("Share Blood");
        mAuth=FirebaseAuth.getInstance();
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=inputName.getText().toString();
                String age=inputAge.getText().toString();
                String blood=bloodhaving.getSelectedItem().toString();
                String gender=((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                String number=inputMobile.getText().toString();
                String location=inputlocation.getText().toString();
                String username=inputUsername.getText().toString();
                String email=inputEmail.getText().toString();
                String password=inputPassword.getText().toString();
                String cpassword=inputCpassword.getText().toString();
                String address=inputAddress.getText().toString();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);



                if(name.equals("")) {         //check whether field is empty or not
                    inputName.setError("Name is required");
                    inputName.requestFocus();
                    return;

                }


                if(age.equals("")){
                    inputAge.setError("please enter your age");
                    inputAge.requestFocus();
                    return;


                }


                if(number.equals("")){

                    inputMobile.setError("please enter mobile number");
                    inputMobile.requestFocus();
                    return;

                }
                if((!Patterns.PHONE.matcher(number).matches())&&(inputMobile.length()!=10))
                {    //this is for checking whether password is gt then 6 or not
                    inputPassword.setError("length of mobile number should be 10");
                    inputPassword.requestFocus();
                    return;
                }

                if(location.equals("")) {

                    inputlocation.setError("please enter location");
                    inputlocation.requestFocus();
                    return;


                }
                if(username.equals("")){

                    inputUsername.setError("enter username");
                    inputUsername.requestFocus();
                    return;
                }

                if(email.equals("")){       //check whether field is empty or not
                    inputEmail.setError("Email is required");
                    inputEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){  //check email is correctly entered or not
                    inputEmail.setError("Please Enter A Valid Email");
                    inputEmail.requestFocus();
                    return;
                }
                if(password.equals("")){      //check whether field is empty or not
                    inputPassword.setError("Password is required");
                    inputPassword.requestFocus();
                    return;
                }
                if(password.length()<6){    //this is for checking whether password is gt then 6 or not
                    inputPassword.setError("Minimum length of password should be 6");
                    inputPassword.requestFocus();
                }

                if(cpassword.equals("")){        //check whether field is empty or not
                    inputCpassword.setError("Please Confirm Password");
                    inputCpassword.requestFocus();
                    return;
                }
                if(!password.equals(cpassword)){

                    Toast.makeText(getApplicationContext(),"Password And confirm-Password not match",Toast.LENGTH_SHORT).show();
                    inputCpassword.requestFocus();

                    return;

                }




                if(address.equals("")){
                    inputAddress.setError("enter your address");
                    inputAddress.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"User Registered Successful",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(task.getException()instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),"Email is already registered",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                if (TextUtils.isEmpty(userId)) {
                    createUsers(name, age,blood,gender, number, location,username ,email, password,cpassword, address);
                }
                startActivity(i);
            }
        });
    }
    public void createUsers(String name, String age,String blood, String gender, String number, String location, String username,String email, String password, String cpassword, String address) {
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }
        String ad=email;
        String[] words=ad.split("@");
//        Toast.makeText(getApplicationContext(),words[0],Toast.LENGTH_SHORT).show();
        Users user = new Users(name, age,blood,gender, number, location, username,email, password,cpassword, address);
        mFirebaseDatabase.child(words[0]).setValue(user);
        addUserChangeListener();
    }
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }
}
