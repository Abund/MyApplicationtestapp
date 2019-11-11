package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class bloodpressureaddpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodpressureaddpage);

        FloatingActionButton fab = findViewById(R.id.floatingActionButtonA);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(bloodpressureaddpage.this, BloodpressureActivity.class);
                startActivity(at);
            }
        });
    }
}
