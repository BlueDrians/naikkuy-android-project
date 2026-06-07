package com.example.naikkuy;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naikkuy.adapter.ScheduleAdapter;
import com.example.naikkuy.model.ScheduleModel;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {
    private ArrayList<ScheduleModel> allSchedules;
    private ArrayList<ScheduleModel> filteredSchedules;
    private ScheduleAdapter scheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        android.widget.TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        Spinner spnDestination = findViewById(R.id.spnDestination);
        RecyclerView recyclerSchedule = findViewById(R.id.recyclerSchedule);

        String[] destinations = {"Semua Tujuan", "Serpong", "BSD", "Cipondoh", "Gading Serpong"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, destinations);
        spnDestination.setAdapter(spinnerAdapter);

        allSchedules = getDummySchedules();
        filteredSchedules = new ArrayList<>(allSchedules);

        scheduleAdapter = new ScheduleAdapter(filteredSchedules);
        recyclerSchedule.setLayoutManager(new LinearLayoutManager(this));
        recyclerSchedule.setNestedScrollingEnabled(false);
        recyclerSchedule.setAdapter(scheduleAdapter);

        spnDestination.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                filterSchedule(destinations[position]);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });
    }

    private ArrayList<ScheduleModel> getDummySchedules() {
        ArrayList<ScheduleModel> list = new ArrayList<>();
        list.add(new ScheduleModel("06:30", "Bus A", "Serpong", "Tersedia"));
        list.add(new ScheduleModel("07:00", "Bus B", "BSD", "Hampir Berangkat"));
        list.add(new ScheduleModel("08:15", "Angkot C", "Cipondoh", "Tersedia"));
        list.add(new ScheduleModel("09:30", "Shuttle D", "Gading Serpong", "Penuh"));
        list.add(new ScheduleModel("11:00", "Bus A", "Serpong", "Tersedia"));
        list.add(new ScheduleModel("13:00", "Bus B", "BSD", "Tersedia"));
        return list;
    }

    private void filterSchedule(String destination) {
        filteredSchedules.clear();
        for (ScheduleModel schedule : allSchedules) {
            if (destination.equals("Semua Tujuan") || schedule.getDestination().equalsIgnoreCase(destination)) {
                filteredSchedules.add(schedule);
            }
        }
        scheduleAdapter.notifyDataSetChanged();
    }
}
