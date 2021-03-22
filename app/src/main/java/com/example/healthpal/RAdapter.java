package com.example.healthpal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DateStepsModel;

import java.util.ArrayList;

public class RAdapter extends RecyclerView.Adapter<RAdapter.RViewholder> {
      ArrayList<DateStepsModel> stepCountList;

    public RAdapter(ArrayList<DateStepsModel> dataholder) {
        this.stepCountList = dataholder;
    }

    @NonNull
    @Override
    public RViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_layout,parent,false);
        return new RViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RViewholder holder, int position) {
        holder.dateStepCountText.setText(stepCountList.get(position).date+ " - TotalSteps: "+String.valueOf(stepCountList.get(position).stepCount));
    }

    @Override
    public int getItemCount() {
        return stepCountList.size();
    }

    class RViewholder extends RecyclerView.ViewHolder {
        TextView dateStepCountText;
        public RViewholder(@NonNull View itemView) {
            super(itemView);
            dateStepCountText=(TextView)itemView.findViewById(R.id.rv_dateSteps);

        }
    }


}
