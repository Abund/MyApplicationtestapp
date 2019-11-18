package com.example.myapplicationtestapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationtestapp.R;
import com.example.myapplicationtestapp.model.Calorie;

import java.util.ArrayList;

public class CalorieAdapter extends RecyclerView.Adapter<CalorieAdapter.ViewHolder>  {

    private ArrayList<String> mData;
    private ArrayList<Calorie> Data;
    private Context ct1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView time;
        public TextView arm;
        public TextView firstReading;
        public TextView secondReading;
        public View layout;
        public CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            date = (TextView) itemView.findViewById(R.id.date);
            //time = (TextView) itemView.findViewById(R.id.time11);
            arm = (TextView) itemView.findViewById(R.id.arm);
            firstReading = (TextView) itemView.findViewById(R.id.reading1);
            secondReading = (TextView) itemView.findViewById(R.id.reading2);
            card = (CardView) itemView.findViewById(R.id.lineChart);
        }
    }
    public CalorieAdapter(ArrayList<String> Data1){

        this.mData=Data1;
    }

    public CalorieAdapter(ArrayList<Calorie> Data, Context ct)
    {
        this.Data=Data;
        this.ct1 =ct;
    }

    @Override
    public CalorieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bloodcard,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CalorieAdapter.ViewHolder holder, final int position) {
//        holder.date.setText(Data.get(position).getDate()+"");
//        holder.time.setText(Data.get(position).getTime()+"");
//        holder.arm.setText(Data.get(position).getMeasuredArm()+"");
//        holder.firstReading.setText(Data.get(position).getSystolicPressure()+"");
//        holder.secondReading.setText(Data.get(position).getDiastolicPressure()+"");

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

}
