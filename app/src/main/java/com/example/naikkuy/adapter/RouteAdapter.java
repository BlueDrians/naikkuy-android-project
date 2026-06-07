package com.example.naikkuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naikkuy.R;
import com.example.naikkuy.model.RouteModel;
import com.example.naikkuy.util.FormatHelper;

import java.util.ArrayList;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {
    public interface OnRouteClickListener {
        void onRouteClick(RouteModel route);
    }

    private final ArrayList<RouteModel> routeList;
    private final OnRouteClickListener listener;

    public RouteAdapter(ArrayList<RouteModel> routeList, OnRouteClickListener listener) {
        this.routeList = routeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        RouteModel route = routeList.get(position);
        holder.txtVehicle.setText(route.getVehicle());
        holder.txtRoute.setText(route.getRoute());
        holder.txtInfo.setText(route.getDuration() + " • " + FormatHelper.rupiah(route.getFare()));
        holder.itemView.setOnClickListener(v -> listener.onRouteClick(route));
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    static class RouteViewHolder extends RecyclerView.ViewHolder {
        TextView txtVehicle, txtRoute, txtInfo;

        RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVehicle = itemView.findViewById(R.id.txtVehicle);
            txtRoute = itemView.findViewById(R.id.txtRoute);
            txtInfo = itemView.findViewById(R.id.txtInfo);
        }
    }
}
