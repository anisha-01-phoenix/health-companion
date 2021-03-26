package com.example.healthpal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RWaterAdapter extends RecyclerView.Adapter<RWaterAdapter.RWaterViewholder> {
    ArrayList<DateWaterModel> waterCountList;
    public RWaterAdapter(ArrayList<DateWaterModel> dataholder) {
        this.waterCountList = dataholder;
    }



    @NonNull
    @Override
    public RWaterAdapter.RWaterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.water_count,parent,false);
        return new RWaterAdapter.RWaterViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RWaterViewholder holder, int position) {
        holder.waterCountText.setText(waterCountList.get(position).date+ " - TotalNoOfGlasses: "+String.valueOf(waterCountList.get(position).waterCount));
    }

    @Override
    public int getItemCount() {
        return waterCountList.size();
    }

    class RWaterViewholder extends RecyclerView.ViewHolder{
        TextView waterCountText;
        public RWaterViewholder(@NonNull View itemView) {
            super(itemView);
            waterCountText=(TextView)itemView.findViewById(R.id.rv_water);
        }
    }
}
