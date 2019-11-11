package com.example.myapplicationtestapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplicationtestapp.R;
import com.example.myapplicationtestapp.model.BloodSugar;
import java.util.ArrayList;

public class BloodSugarAdapter extends RecyclerView.Adapter<BloodSugarAdapter.ViewHolder>  {
    private ArrayList<String> mData;
    private ArrayList<BloodSugar> Data;
    private Context ct1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView time;
        public TextView concentration;
        public View layout;
        public CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time11);
            concentration = (TextView) itemView.findViewById(R.id.reading1);
            card = (CardView) itemView.findViewById(R.id.lineChart);
        }
    }
    public BloodSugarAdapter(ArrayList<String> Data1){

        this.mData=Data1;
    }

    public BloodSugarAdapter(ArrayList<BloodSugar> Data, Context ct)
    {
        this.Data=Data;
        this.ct1 =ct;
    }

    @Override
    public BloodSugarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bloodcard,parent,false);
        // set the view size, margin,padding and layout parameters
        BloodSugarAdapter.ViewHolder vh = new BloodSugarAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final BloodSugarAdapter.ViewHolder holder, final int position) {
        holder.date.setText(Data.get(position).getDate()+"");
        holder.time.setText(Data.get(position).getTime()+"");
        holder.concentration.setText(Data.get(position).getConcentrationSugar()+"");
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

}
