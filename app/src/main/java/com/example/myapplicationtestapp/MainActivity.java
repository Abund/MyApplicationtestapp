package com.example.myapplicationtestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button signIn,googleSignIn;
    TextView register;
    EditText email,password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = (Button) findViewById(R.id.signIn);
        googleSignIn = (Button) findViewById(R.id.googleSignInButton);
        register = (TextView) findViewById(R.id.register);
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener(){
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
                //authenticathe the user
                firebaseAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                            Intent at = new Intent(MainActivity.this, homescreen.class);
                            startActivity(at);
                        }else{
                            Toast.makeText(MainActivity.this,"Error in login",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent at = new Intent(MainActivity.this, registrationpage.class);
                startActivity(at);
            }
        });
    }
}
