package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class registrationpage extends AppCompatActivity {

    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);

        signUp = (Button) findViewById(R.id.signup);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent at = new Intent(registrationpage.this, homescreen.class);
                startActivity(at);
            }
        });
    }
}
