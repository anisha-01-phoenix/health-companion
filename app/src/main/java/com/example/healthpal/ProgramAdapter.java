package com.example.healthpal;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>{

    private String[] data;
    public ProgramAdapter(String[]data){
        this.data=data;
    }
    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.steps_layout,parent,false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
            String title=data[position];
            holder.date.setText(title);
            holder.stepCount.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView stepCount;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            date= (TextView) itemView.findViewById(R.id.rv_date);
            stepCount=(TextView) itemView.findViewById(R.id.rv_stepCount);



        }
    }
}
