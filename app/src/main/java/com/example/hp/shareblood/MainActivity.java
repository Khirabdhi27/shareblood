package com.example.hp.shareblood;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText inputUserEmail,inputPassword;
    private TextView textDetails;
    private Button btnsave,btnlogin;
    private FirebaseAuth mAuth;
    //    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputUserEmail=(EditText) findViewById(R.id.useremail);
        inputPassword=(EditText) findViewById(R.id.password);
        btnlogin=(Button) findViewById(R.id.buttonLogin);
        mAuth = FirebaseAuth.getInstance();     //firebase method as we written in registration activity
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String useremail=inputUserEmail.getText().toString();
                String password=inputPassword.getText().toString();
                if(useremail.equals(""))
                {
                    inputUserEmail.setError("username is required");
                    inputUserEmail.requestFocus();
                    //Toast.makeText(getApplicationContext(), "Please enter a valid UserName", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {  //check email is correctly entered or not
                    inputUserEmail.setError("Please Enter A Valid Email");
                    inputUserEmail.requestFocus();
                    return;
                }
                else if (password.equals(""))
                {
                    inputPassword.setError("password is required");
                    inputPassword.requestFocus();
                    //Toast.makeText(getApplicationContext(), "Please enter a valid Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(useremail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent userpage = new Intent(getApplicationContext(), UserPage.class);
                                userpage.putExtra("useremail",useremail);
                                startActivity(userpage);
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        btnsave=(Button)findViewById(R.id.button);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegistrationPage.class);
                startActivity(i);
            }
        });
    }
}
