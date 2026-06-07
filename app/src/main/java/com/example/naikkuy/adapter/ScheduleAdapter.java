package com.example.naikkuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naikkuy.R;
import com.example.naikkuy.model.ScheduleModel;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private final ArrayList<ScheduleModel> scheduleList;

    public ScheduleAdapter(ArrayList<ScheduleModel> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleModel schedule = scheduleList.get(position);
        holder.txtTime.setText(schedule.getTime());
        holder.txtVehicleDestination.setText(schedule.getVehicle() + " - " + schedule.getDestination());
        holder.txtStatus.setText(schedule.getStatus());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView txtTime, txtVehicleDestination, txtStatus;

        ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtVehicleDestination = itemView.findViewById(R.id.txtVehicleDestination);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }
    }
}
