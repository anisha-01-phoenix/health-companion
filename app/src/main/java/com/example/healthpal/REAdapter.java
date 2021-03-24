package com.example.healthpal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DateStepsModel;

import java.util.ArrayList;

public class REAdapter extends RecyclerView.Adapter<REAdapter.REViewholder> {
    ArrayList<DateEHoursModel> ETimeList;

    public REAdapter(ArrayList<DateEHoursModel> dataholder) {
        this.ETimeList = dataholder;
    }

    @NonNull
    @Override
    public REAdapter.REViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_exercise, parent,false);
        return new REAdapter.REViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull REAdapter.REViewholder holder, int position) {
        holder.ehoursCountText.setText(ETimeList.get(position).date+ " - TotalNoOfHours: "+String.valueOf(ETimeList.get(position).ehoursCount));
    }

    @Override
    public int getItemCount() {
        return ETimeList.size();
    }

    class REViewholder extends RecyclerView.ViewHolder {
        TextView ehoursCountText;
        public REViewholder(@NonNull View itemView) {
            super(itemView);
            ehoursCountText=(TextView)itemView.findViewById(R.id.rv_dateTime);

        }
    }


}

