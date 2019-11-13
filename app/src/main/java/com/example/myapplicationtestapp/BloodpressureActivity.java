package com.example.myapplicationtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplicationtestapp.adapters.Bloodpressureadapter;
import com.example.myapplicationtestapp.model.BloodPressure;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import android.view.View;

import android.app.ProgressDialog;
import android.util.Log;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BloodpressureActivity extends AppCompatActivity {

    private LineChart lineChart;
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BloodPressure> data;
    private ArrayList<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodpressure);
        lineChart =(LineChart) findViewById(R.id.lineChart);
        //lineChart.setOnChartGestureListener(BloodpressureActivity.this);
        //lineChart.setOnChartValueSelectedListener(BloodpressureActivity.this);
        data = new ArrayList<BloodPressure>();
        mData = new ArrayList<>();
        for (int i = 0;i<30;i++){
            mData.add("new title"+i);
        }
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(BloodpressureActivity.this,LinearLayoutManager.HORIZONTAL,false);
        mRecycler.setLayoutManager(mLayoutManager);

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


//    private class SyncData extends AsyncTask<String ,String,String>{
//        String msg="Internet/DB_Credentials/Windows_FireWall_TurnOn Error, see android monitor in the buttom for details";
//        ProgressDialog progress;
//        boolean success = false;
//
//        @Override
//        protected void onPreExecute(){
//            progress = ProgressDialog.show(BloodpressureActivity.this,"Synchronising","RecyclerView loading! please wait...",true);
//        }
//
//        @Override
//        protected String doInBackground(String...strings){
//            try {
//                Connection con = db.getConnection();
//                ResultSet resultSet = null;
//
//                if (con == null) {
//
//                    msg="No Data found";
//                    success = false;
//                }else{
//                    String query = "SELECT * FROM Product_Details where Category_ID = 2 ";
//                    PreparedStatement stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                    resultSet = stmt.executeQuery();
//
//                    while (resultSet.next() ) {
//                        try {
//                            data.add(new BloodPressure(resultSet.getBinaryStream("Image"),resultSet.getInt("Product_ID"), resultSet.getString("Product_Name"), resultSet.getInt("Price"),resultSet.getInt("Stock"), resultSet.getString("Description"), resultSet.getInt("Discount")));
//                        }catch(Exception ex){
//                            ex.printStackTrace();
//                            Log.e( "error here: ",ex.getLocalizedMessage());
//                            Log.e( "error here: ",ex.getMessage());
//                        }
//
//                    }
//                    msg ="found";
//                    success=true;
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//                //Write write = new StringWriter();
//                //e.printStackTrace(new PrintWriter(write));
//                //msg=writer.toString();
//                Log.e( "error here: ",e.getLocalizedMessage());
//                Log.e( "error here: ",e.getMessage());
//                success=false;
//            }
//            return msg;
//        }
//
//        @Override
//        protected void onPostExecute(String msg){
//            progress.dismiss();
//            Toast.makeText(BloodpressureActivity.this,msg+"",Toast.LENGTH_LONG).show();
//            if(success == true){
//                mAdapter = new Bloodpressureadapter(data,BloodpressureActivity.this);
//                mAdapter.notifyDataSetChanged();
//                mRecycler.setAdapter(mAdapter);
//            }else{
//                try{
//
//                }catch (Exception e){
//
//                }
//
//            }
//        }
//    }
}
