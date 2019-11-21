package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView pointer;
    Date dob_var;
    String str;
    private static final String[] meas=new String[]{"Left","Right"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodpressureaddpage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        systolicPressure= (EditText) findViewById(R.id.systolicPressure);
        pointer=(ImageView) findViewById(R.id.measuredPointerBP);
        diastolicPressure= (EditText) findViewById(R.id.diastolicPressure);
        date= (EditText) findViewById(R.id.pressuredate);
        notes= (EditText) findViewById(R.id.notesBP);
        tags= (EditText) findViewById(R.id.tags);
        measured= (AutoCompleteTextView) findViewById(R.id.measuredArm);
        time= (AutoCompleteTextView) findViewById(R.id.pressureTime);
        ArrayAdapter<String> com=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,meas);
        measured.setAdapter(com);
        measured.setThreshold(0);

        pointer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                measured.showDropDown();
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("BloodSugar");

        FloatingActionButton fab = findViewById(R.id.floatingActionButtonA);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String syst = systolicPressure.getText().toString().trim();
                String diast = diastolicPressure.getText().toString().trim();
                String arm = measured.getText().toString().trim();

                if(TextUtils.isEmpty(syst)){
                    systolicPressure.setError("Systolic pressure is required");
                }
                if(TextUtils.isEmpty(diast)){
                    diastolicPressure.setError("diastolic is required");
                }
                if(TextUtils.isEmpty(arm)){
                    measured.setError("Please enter measured arm");
                }

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
