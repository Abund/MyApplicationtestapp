package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GoalAddPage extends AppCompatActivity {

    private EditText goal,time,date;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_add_page);

        time= (EditText) findViewById(R.id.calGoalTime);
        date= (EditText) findViewById(R.id.calGoalDate);
        goal= (EditText) findViewById(R.id.calgoals);
        save = (Button) findViewById(R.id.calGoalSave);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent at = new Intent(GoalAddPage.this, GoalActivity.class);
                startActivity(at);
            }
        });
    }
}
