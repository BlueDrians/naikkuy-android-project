package com.example.naikkuy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naikkuy.adapter.RouteAdapter;
import com.example.naikkuy.model.RouteModel;
import com.example.naikkuy.util.FormatHelper;

import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity {
    private ArrayList<RouteModel> routeList;
    private ArrayList<RouteModel> filteredList;
    private RouteAdapter routeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        android.widget.TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        EditText edtSearch = findViewById(R.id.edtSearch);
        RecyclerView recyclerRoute = findViewById(R.id.recyclerRoute);

        routeList = getDummyRoutes();
        filteredList = new ArrayList<>(routeList);

        routeAdapter = new RouteAdapter(filteredList, this::showRouteDetail);
        recyclerRoute.setLayoutManager(new LinearLayoutManager(this));
        recyclerRoute.setAdapter(routeAdapter);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterRoutes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private ArrayList<RouteModel> getDummyRoutes() {
        ArrayList<RouteModel> list = new ArrayList<>();
        list.add(new RouteModel("Bus A", "Terminal Poris - Cikokol - TangCity - Serpong", "35 menit", 10000));
        list.add(new RouteModel("Bus B", "Terminal Cimone - Karawaci - BSD", "45 menit", 12000));
        list.add(new RouteModel("Angkot C", "Pasar Anyar - Stasiun Tangerang - Cipondoh", "25 menit", 5000));
        list.add(new RouteModel("Shuttle D", "TangCity - Alam Sutera - Gading Serpong", "30 menit", 25000));
        return list;
    }

    private void filterRoutes(String keyword) {
        filteredList.clear();
        for (RouteModel route : routeList) {
            if (route.getVehicle().toLowerCase().contains(keyword.toLowerCase()) ||
                    route.getRoute().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(route);
            }
        }
        routeAdapter.notifyDataSetChanged();
    }

    private void showRouteDetail(RouteModel route) {
        String message = "Kendaraan: " + route.getVehicle() +
                "\nRute: " + route.getRoute() +
                "\nEstimasi: " + route.getDuration() +
                "\nTarif: " + FormatHelper.rupiah(route.getFare());

        new AlertDialog.Builder(this)
                .setTitle("Detail Rute")
                .setMessage(message)
                .setPositiveButton("Tutup", null)
                .show();
    }
}
