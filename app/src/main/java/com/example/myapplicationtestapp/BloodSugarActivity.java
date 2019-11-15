package com.example.myapplicationtestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationtestapp.adapters.BloodSugarAdapter;
import com.example.myapplicationtestapp.model.BloodSugar;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BloodSugarActivity extends AppCompatActivity {

    private LineChart lineChart;
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BloodSugar> data;
    private ArrayList<String> mData;
    private DatabaseReference myRef;
    private BloodSugarAdapter bloodSugarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar);

        lineChart =(LineChart) findViewById(R.id.lineChart2);
        //lineChart.setOnChartGestureListener(BloodpressureActivity.this);
        //lineChart.setOnChartValueSelectedListener(BloodpressureActivity.this);


        data = new ArrayList<BloodSugar>();
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRef = FirebaseDatabase.getInstance().getReference().child("BloodPressure");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    BloodSugar bloodSugar = dataSnapshot1.getValue(BloodSugar.class);
                    data.add(bloodSugar);
                }
                bloodSugarAdapter = new BloodSugarAdapter(data,BloodSugarActivity.this);
                mRecycler.setAdapter(bloodSugarAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BloodSugarActivity.this,"Oppss... something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0,50));
        yValues.add(new Entry(1,60));
        yValues.add(new Entry(2,70));
        yValues.add(new Entry(3,80));
        yValues.add(new Entry(4,90));
        yValues.add(new Entry(5,100));

        LineDataSet set1 = new LineDataSet(yValues,"Data set 1");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(BloodSugarActivity.this, BloodSugarAddPage.class);
                startActivity(at);
            }
        });
    }
}
