package com.example.naikkuy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naikkuy.adapter.BookingAdapter;
import com.example.naikkuy.helper.DatabaseHelper;
import com.example.naikkuy.helper.SessionManager;
import com.example.naikkuy.model.BookingModel;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private SessionManager sessionManager;
    private ArrayList<BookingModel> bookingList;
    private BookingAdapter bookingAdapter;
    private RecyclerView recyclerHistory;
    private TextView txtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        android.widget.TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        recyclerHistory = findViewById(R.id.recyclerHistory);
        txtEmpty = findViewById(R.id.txtEmpty);
        Button btnClearAll = findViewById(R.id.btnClearAll);

        recyclerHistory.setLayoutManager(new LinearLayoutManager(this));
        loadBookings();

        btnClearAll.setOnClickListener(v -> confirmDeleteAll());
    }

    private void loadBookings() {
        bookingList = databaseHelper.getBookings(sessionManager.getUsername());
        bookingAdapter = new BookingAdapter(bookingList, this::confirmDeleteOne);
        recyclerHistory.setAdapter(bookingAdapter);

        if (bookingList.isEmpty()) {
            txtEmpty.setVisibility(View.VISIBLE);
            recyclerHistory.setVisibility(View.GONE);
        } else {
            txtEmpty.setVisibility(View.GONE);
            recyclerHistory.setVisibility(View.VISIBLE);
        }
    }

    private void confirmDeleteOne(BookingModel booking) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Pesanan")
                .setMessage("Yakin ingin menghapus pesanan ini?")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    databaseHelper.deleteBooking(booking.getId());
                    Toast.makeText(this, "Pesanan dihapus", Toast.LENGTH_SHORT).show();
                    loadBookings();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void confirmDeleteAll() {
        if (bookingList.isEmpty()) {
            Toast.makeText(this, "Riwayat masih kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Hapus Semua Riwayat")
                .setMessage("Semua riwayat pesanan akan dihapus. Lanjutkan?")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    databaseHelper.deleteAllBookings(sessionManager.getUsername());
                    Toast.makeText(this, "Semua riwayat dihapus", Toast.LENGTH_SHORT).show();
                    loadBookings();
                })
                .setNegativeButton("Batal", null)
                .show();
    }
}
