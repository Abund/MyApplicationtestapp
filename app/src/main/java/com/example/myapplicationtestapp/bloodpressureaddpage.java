package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplicationtestapp.fragments.DatePickerFragment;
import com.example.myapplicationtestapp.fragments.TimePickerFragment;
import com.example.myapplicationtestapp.model.BloodPressure;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class bloodpressureaddpage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText systolicPressure,diastolicPressure,notes,tags;
    private AutoCompleteTextView measured;
    private DatabaseReference myRef;
    private ImageView pointer;
    TextView date,time;
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
        date= (TextView) findViewById(R.id.pressuredate);
        notes= (EditText) findViewById(R.id.notesBP);
        tags= (EditText) findViewById(R.id.tags);
        measured= (AutoCompleteTextView) findViewById(R.id.measuredArm);
        time= (TextView) findViewById(R.id.pressureTime);
        ArrayAdapter<String> com=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,meas);
        measured.setAdapter(com);
        measured.setThreshold(0);

        pointer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                measured.showDropDown();
            }
        });

        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("BloodPressure").child(FirebaseAuth.getInstance().getUid()).push();

        FloatingActionButton fab = findViewById(R.id.floatingActionButtonA);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String syst = systolicPressure.getText().toString().trim();
                String diast = diastolicPressure.getText().toString().trim();
                String arm = measured.getText().toString().trim();
                String date1 = date.getText().toString().trim();
                String time1 = time.getText().toString().trim();

                if(TextUtils.isEmpty(syst)){
                    systolicPressure.setError("Systolic pressure is required");
                }
                if(TextUtils.isEmpty(diast)){
                    diastolicPressure.setError("diastolic is required");
                }
                if(TextUtils.isEmpty(arm)){
                    measured.setError("Please enter measured arm");
                }
                if(TextUtils.isEmpty(date1)){
                    date.setError("Please enter date");
                }
                if(TextUtils.isEmpty(time1)){
                    time.setError("Please enter time");
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
                bloodPressure.setDate(date.getText().toString());
                bloodPressure.setDiastolicPressure(Integer.parseInt(diastolicPressure.getText().toString()));
                bloodPressure.setMeasuredArm(measured.getText().toString());
                bloodPressure.setTime(time.getText().toString());
                bloodPressure.setTag(tags.getText().toString().trim());
                bloodPressure.setNotes(notes.getText().toString().trim());
                bloodPressure.setSystolicPressure(Integer.parseInt(systolicPressure.getText().toString()));

                myRef.setValue(bloodPressure).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(bloodpressureaddpage.this,"Successful",Toast.LENGTH_SHORT).show();
                        finish();
//                        FragmentManager fragmentManager =getSupportFragmentManager();
//                        fragmentManager.beginTransaction().replace(R.id.pAddPage, new BloodpressureActivity()).commit();
//                        Intent at = new Intent(bloodpressureaddpage.this, homescreen.class);
//                        startActivity(at);
                    }
                });
//                Intent at = new Intent(bloodpressureaddpage.this, BloodpressureActivity.class);
//                startActivity(at);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c =Calendar.getInstance();
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        //c.set(Calendar.DAY_OF_MONTH,i2);
        String currentDatePicker =DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        date.setText(currentDatePicker);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        //String currentDatePicker =DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        time.setText(""+i+":"+i1);
    }
}
