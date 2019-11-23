package com.example.myapplicationtestapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplicationtestapp.adapters.Bloodpressureadapter;
import com.example.myapplicationtestapp.model.BloodPressure;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.view.View;

//public class BloodpressureActivity extends AppCompatActivity {
public class BloodpressureActivity extends Fragment {

    private LineChart lineChart;
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BloodPressure> data;
    private ArrayList<String> mData;
    private DatabaseReference myRef;
    private Bloodpressureadapter bloodpressureadapter;
    View view;
    private DatabaseReference myRefOnline;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_bloodpressure,container,false);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bloodpressure);
        lineChart = (LineChart) view.findViewById(R.id.lineChart);
        mRecycler = (RecyclerView) view.findViewById(R.id.recyclerViewBP);
        //lineChart.setOnChartGestureListener(BloodpressureActivity.this);
        //lineChart.setOnChartValueSelectedListener(BloodpressureActivity.this);
        data = new ArrayList<BloodPressure>();

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
      //  myRef = FirebaseDatabase.getInstance().getReference().child("BloodPressure").child(FirebaseAuth.getInstance().getUid()).push();
        myRefOnline = FirebaseDatabase.getInstance().getReference().child("BloodPressure").child(FirebaseAuth.getInstance().getUid());

        myRefOnline.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    if (dataSnapshot.getChildrenCount() == 0){
                        return;
                    }
                    BloodPressure bloodPressure = dataSnapshot1.getValue(BloodPressure.class);
                    data.add(bloodPressure);
                }
                bloodpressureadapter = new Bloodpressureadapter(data,getActivity().getBaseContext());
                mRecycler.setAdapter(bloodpressureadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getBaseContext(),"Oppss... something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 50));
        yValues.add(new Entry(1, 60));
        yValues.add(new Entry(2, 70));
        yValues.add(new Entry(3, 100));
        yValues.add(new Entry(4, 90));
        yValues.add(new Entry(5, 100));

        for(int i =0;i<data.size();i++){
            yValues.add(new Entry(data.get(i).getDiastolicPressure(), 120));
        }

        LineDataSet set1 = new LineDataSet(yValues, "Data set 1");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent at = new Intent(getActivity().getBaseContext(), bloodpressureaddpage.class);
                startActivity(at);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("HealthCare");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onRefresh() {
        Toast.makeText(getActivity(), "Fragment : Refresh called.",
                Toast.LENGTH_SHORT).show();
    }

}