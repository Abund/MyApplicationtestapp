package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationtestapp.model.BloodPressure;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bloodpressureaddpage extends AppCompatActivity {

    private EditText systolicPressure,diastolicPressure,notes,tags,time,date;
    private AutoCompleteTextView measured;
    private DatabaseReference myRef;
    Date dob_var;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodpressureaddpage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        systolicPressure= (EditText) findViewById(R.id.sugarConcentration);
        diastolicPressure= (EditText) findViewById(R.id.time);
        date= (EditText) findViewById(R.id.pressuredate);
        notes= (EditText) findViewById(R.id.notesBP);
        tags= (EditText) findViewById(R.id.tags);
        measured= (AutoCompleteTextView) findViewById(R.id.measured);
        time= (AutoCompleteTextView) findViewById(R.id.pressureTime);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("BloodSugar");

        FloatingActionButton fab = findViewById(R.id.floatingActionButtonA);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    dob_var = sdf.parse(date.getText().toString());
//                    str = time.getText().toString();
//                    DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                BloodPressure bloodPressure = new BloodPressure();
                bloodPressure.setDate(dob_var);
                bloodPressure.setDiastolicPressure(Integer.parseInt(diastolicPressure.getText().toString()));
                bloodPressure.setMeasuredArm(Integer.parseInt(measured.getText().toString()));
                //bloodPressure.setTime();
                bloodPressure.setTag(tags.getText().toString().trim());
                bloodPressure.setNotes(notes.getText().toString().trim());
                bloodPressure.setSystolicPressure(Integer.parseInt(systolicPressure.getText().toString()));

                myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(bloodPressure).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(bloodpressureaddpage.this,"Successful",Toast.LENGTH_SHORT).show();
                        Intent at = new Intent(bloodpressureaddpage.this, homescreen.class);
                        startActivity(at);
                    }
                });
                Intent at = new Intent(bloodpressureaddpage.this, BloodpressureActivity.class);
                startActivity(at);
            }
        });
    }
}
