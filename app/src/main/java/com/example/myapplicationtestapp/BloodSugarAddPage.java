package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationtestapp.model.BloodSugar;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BloodSugarAddPage extends AppCompatActivity {

    private EditText sugarConcentration,time,date,notes,tags;
    private AutoCompleteTextView measured;
    private DatabaseReference myRef;
    Date dob_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar_add_page);

        sugarConcentration= (EditText) findViewById(R.id.sugarConcentration);
        time= (EditText) findViewById(R.id.time);
        date= (EditText) findViewById(R.id.dateBS);
        notes= (EditText) findViewById(R.id.notesBS);
        tags= (EditText) findViewById(R.id.tags);
        measured= (AutoCompleteTextView) findViewById(R.id.measured);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("BloodSugar");


        FloatingActionButton fab = findViewById(R.id.floatingActionButtonSA);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    dob_var = sdf.parse(date.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                BloodSugar bloodSugar = new BloodSugar();
                bloodSugar.setConcentrationSugar(Integer.parseInt(sugarConcentration.getText().toString()));
                bloodSugar.setDate(dob_var);
                bloodSugar.setMeasured(Integer.parseInt(measured.getText().toString()));
                //bloodSugar.setTime();
                bloodSugar.setNotes(notes.getText().toString().trim());
                bloodSugar.setTag(tags.getText().toString().trim());
                myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(bloodSugar).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(BloodSugarAddPage.this,"Successful",Toast.LENGTH_SHORT).show();
                        Intent at = new Intent(BloodSugarAddPage.this, homescreen.class);
                        startActivity(at);
                    }
                });
                Intent at = new Intent(BloodSugarAddPage.this, BloodSugarActivity.class);
                startActivity(at);
            }
        });
    }
}
