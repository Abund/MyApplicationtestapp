package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationtestapp.model.Medication;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicationAddPage extends AppCompatActivity {

    private EditText medicationName,EndDate,notes;
    private AutoCompleteTextView instructionMedi,repeat,pills,unitsMd;
    private DatabaseReference myRef;
    private TextView startDate;
    Date start;
    Date end;
    private static final String[] meas=new String[]{"Before food","After food","With food","No matter"};
    private static final String[] meas1=new String[]{"pill","gm","mg","ml","pieces","tablet","units","strips","tablespoon","drops","mcg","cream","carton","spray"};
    private static final String[] meas2=new String[]{"Number of days","Continuous",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_add_page2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medicationName= (EditText) findViewById(R.id.medicationName);
        startDate= (TextView) findViewById(R.id.dateMedi);
        EndDate= (AutoCompleteTextView) findViewById(R.id.endDateMedi);
        notes= (EditText) findViewById(R.id.notesMD);
        instructionMedi= (AutoCompleteTextView) findViewById(R.id.instructionMedi);
        repeat= (AutoCompleteTextView) findViewById(R.id.repeat);
        pills= (AutoCompleteTextView) findViewById(R.id.pills);
        unitsMd= (AutoCompleteTextView) findViewById(R.id.unitsMd);
        ArrayAdapter<String> com=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,meas);
        ArrayAdapter<String> com1=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,meas1);
        ArrayAdapter<String> com2=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,meas2);
        instructionMedi.setAdapter(com);
        instructionMedi.setThreshold(0);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Medication");

        FloatingActionButton fab = findViewById(R.id.floatingActionButtonMAD);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    start = sdf.parse(startDate.getText().toString());
                    end = sdf.parse(EndDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Medication medication = new Medication();
                medication.setEndDate(end);
                medication.setInstructions(instructionMedi.getText().toString());
                medication.setMedicationName(medicationName.getText().toString());
                medication.setNotes(notes.getText().toString());
                medication.setNumberOfDays(unitsMd.getText().toString());
                medication.setRepeats(repeat.getText().toString());
                medication.setUnits(pills.getText().toString());
                medication.setStarteDate(start);

                myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(medication).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                    }
                });
                Intent at = new Intent(MedicationAddPage.this, MedicationDashBoard.class);
                startActivity(at);
            }
        });
    }
}
