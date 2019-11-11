package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button signIn;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = (Button) findViewById(R.id.signIn);
        register = (TextView) findViewById(R.id.register);

        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent at = new Intent(MainActivity.this, homescreen.class);
                startActivity(at);
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
