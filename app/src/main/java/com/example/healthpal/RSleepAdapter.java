package com.example.healthpal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RSleepAdapter extends RecyclerView.Adapter<RSleepAdapter.RSleepViewHolder> {
    private Context context;
    private ArrayList<DateSleepModel> sleepList;
    public RSleepAdapter(Context context,ArrayList<DateSleepModel> sleepList) {
        this.context=context;
        this.sleepList = sleepList;
    }

    @NonNull
    @Override
    public RSleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sleep, parent,false);
        return new RSleepAdapter.RSleepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RSleepViewHolder holder, int position) {
        holder.sleepText.setText(sleepList.get(position).date+ " -  "+String.valueOf(sleepList.get(position).sleep));
    }

    @Override
    public int getItemCount() {
        return sleepList.size();
    }

    public class RSleepViewHolder extends RecyclerView.ViewHolder{
        TextView sleepText;
        public RSleepViewHolder(@NonNull View itemView) {
            super(itemView);
            sleepText=itemView.findViewById(R.id.rv_sleep);
        }
    }
}
