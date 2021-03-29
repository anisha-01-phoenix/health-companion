package com.example.healthpal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DateStepsModel;

import java.util.ArrayList;

public class RAdapter extends RecyclerView.Adapter<RAdapter.RViewholder> {
    private Context context;
    private ArrayList<DateStepsModel> stepCountList;

    public RAdapter(Context context, ArrayList<DateStepsModel> stepCountList) {
        this.context=context;
        this.stepCountList = stepCountList;
    }

    @NonNull
    @Override
    public RAdapter.RViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_layout, parent,false);
        return new RViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RViewholder holder, int position) {
        DateStepsModel stepsModel=stepCountList.get(position);
        holder.dateStepCountText.setText(stepsModel.date+ " - TotalSteps: "+String.valueOf(stepsModel.stepCount));
    }

    @Override
    public int getItemCount() {
        return stepCountList.size();
    }

    class RViewholder extends RecyclerView.ViewHolder {
        TextView dateStepCountText;
        public RViewholder(@NonNull View itemView) {
            super(itemView);
            dateStepCountText=itemView.findViewById(R.id.rv_dateSteps);

        }
    }


}
