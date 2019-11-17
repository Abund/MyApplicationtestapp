package com.example.myapplicationtestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationtestapp.model.Goal;
import com.example.myapplicationtestapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");
        final DatabaseReference myRef1 = database.getReference("goal");

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

                            User user = new User();
                            user.setEmail(email.getText().toString().trim());
                            user.setFirstName(firstName.getText().toString().trim());
                            user.setLastName(lastName.getText().toString().trim());

                            myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(registrationpage.this,"Registration successful",Toast.LENGTH_SHORT).show();
                                    Intent at = new Intent(registrationpage.this, homescreen.class);
                                    startActivity(at);
                                }
                            });

                            Goal goal = new Goal();
                            goal.setGoal("2700");
                            myRef1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(goal).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            });

                        }else{
                            Toast.makeText(registrationpage.this,"Error in registration",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override public void onDataChange(DataSnapshot dataSnapshot) {
//                //String value = dataSnapshot.getValue(String.class);
//                // viewText.setText(value);
//            }
//            @Override public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Ch3", "Failed to read value.", error.toException());
//            }
//        });
    }
}
