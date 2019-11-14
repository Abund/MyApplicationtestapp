package com.example.myapplicationtestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registrationpage extends AppCompatActivity {

    Button signUp;
    FirebaseAuth firebaseAuth;
    EditText firstName,lastName,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);

        signUp = (Button) findViewById(R.id.signup);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            Intent at = new Intent(registrationpage.this, homescreen.class);
            startActivity(at);
        }

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString().trim();

                if(TextUtils.isEmpty(email1)){
                    email.setError("Email is required");
                }
                if(TextUtils.isEmpty(password1)){
                    password.setError("Password is required");
                }

                if(password1.length()<6){
                    password.setError("Password must be greater than 6 characters long");
                }

                // register the user in firebase
                firebaseAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(registrationpage.this,"Registration successful",Toast.LENGTH_SHORT).show();
                            Intent at = new Intent(registrationpage.this, homescreen.class);
                            startActivity(at);
                        }else{
                            Toast.makeText(registrationpage.this,"Error in registration",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
