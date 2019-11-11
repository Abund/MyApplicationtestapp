package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import android.view.View;

public class BloodpressureActivity extends AppCompatActivity {

    private LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodpressure);
        lineChart =(LineChart) findViewById(R.id.lineChart);
        //lineChart.setOnChartGestureListener(BloodpressureActivity.this);
        //lineChart.setOnChartValueSelectedListener(BloodpressureActivity.this);
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

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent at = new Intent(BloodpressureActivity.this, bloodpressureaddpage.class);
                startActivity(at);
            }
        });
    }
}
