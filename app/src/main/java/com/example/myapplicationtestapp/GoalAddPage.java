package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;

public class GoalAddPage extends AppCompatActivity {

    private EditText goal,time,date;
    private Button save;
    private DatabaseReference myRef;
    Date start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_add_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        time= (EditText) findViewById(R.id.calGoalTime);
        date= (EditText) findViewById(R.id.calGoalDate);
        goal= (EditText) findViewById(R.id.calgoals);
        save = (Button) findViewById(R.id.calGoalSave);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("goal");

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Query query = myRef.orderByChild("goal").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override public void onDataChange(DataSnapshot dataSnapshot) {
                        DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                        String key = nodeShot.getKey();
                        HashMap<String, Object> update = new HashMap<>();
                        update.put("goal", goal.getText().toString());
                        myRef.child(key).updateChildren(update);
                    }
                    @Override public void onCancelled(DatabaseError databaseError) {

                    }});
                finish();
            }
        });
    }
}
