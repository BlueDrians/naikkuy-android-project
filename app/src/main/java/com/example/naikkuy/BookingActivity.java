package com.example.naikkuy;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naikkuy.helper.DatabaseHelper;
import com.example.naikkuy.helper.SessionManager;
import com.example.naikkuy.util.FormatHelper;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {
    private EditText edtPassenger, edtDate, edtTicketCount;
    private Spinner spnTransport, spnDestination;
    private android.widget.TextView txtTotal;
    private DatabaseHelper databaseHelper;
    private SessionManager sessionManager;

    private final String[] transports = {"Bus Kota", "Angkot", "Shuttle"};
    private final String[] destinations = {"Serpong", "BSD", "Cipondoh", "Gading Serpong"};
    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        android.widget.TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        edtPassenger = findViewById(R.id.edtPassenger);
        edtDate = findViewById(R.id.edtDate);
        edtTicketCount = findViewById(R.id.edtTicketCount);
        spnTransport = findViewById(R.id.spnTransport);
        spnDestination = findViewById(R.id.spnDestination);
        txtTotal = findViewById(R.id.txtTotal);
        Button btnBook = findViewById(R.id.btnBook);

        spnTransport.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, transports));
        spnDestination.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, destinations));

        edtDate.setOnClickListener(v -> showDatePicker());
        btnBook.setOnClickListener(v -> bookTicket());

        spnTransport.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                calculateTotal();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });

        edtTicketCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateTotal();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    edtDate.setText(date);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private int getTransportPrice(String transport) {
        switch (transport) {
            case "Angkot":
                return 5000;
            case "Shuttle":
                return 25000;
            case "Bus Kota":
            default:
                return 10000;
        }
    }

    private void calculateTotal() {
        String countText = edtTicketCount.getText().toString().trim();
        int count = 0;

        if (!countText.isEmpty()) {
            try {
                count = Integer.parseInt(countText);
            } catch (NumberFormatException ignored) {
                count = 0;
            }
        }

        String transport = spnTransport.getSelectedItem() == null ? "Bus Kota" : spnTransport.getSelectedItem().toString();
        totalPrice = getTransportPrice(transport) * count;
        txtTotal.setText("Total: " + FormatHelper.rupiah(totalPrice));
    }

    private void bookTicket() {
        String passenger = edtPassenger.getText().toString().trim();
        String date = edtDate.getText().toString().trim();
        String countText = edtTicketCount.getText().toString().trim();

        if (passenger.isEmpty() || date.isEmpty() || countText.isEmpty()) {
            Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        int ticketCount;
        try {
            ticketCount = Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Jumlah tiket harus angka", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ticketCount <= 0) {
            Toast.makeText(this, "Jumlah tiket minimal 1", Toast.LENGTH_SHORT).show();
            return;
        }

        String transport = spnTransport.getSelectedItem().toString();
        String destination = spnDestination.getSelectedItem().toString();
        calculateTotal();

        boolean saved = databaseHelper.addBooking(
                sessionManager.getUsername(),
                passenger,
                transport,
                destination,
                date,
                ticketCount,
                totalPrice
        );

        if (saved) {
            showBookingSuccess(passenger, transport, destination, date, ticketCount, totalPrice);
        } else {
            Toast.makeText(this, "Gagal menyimpan pesanan", Toast.LENGTH_SHORT).show();
        }
    }

    private void showBookingSuccess(String passenger, String transport, String destination, String date, int ticketCount, int totalPrice) {
        String message = "Nama: " + passenger +
                "\nTransportasi: " + transport +
                "\nTujuan: " + destination +
                "\nTanggal: " + date +
                "\nJumlah tiket: " + ticketCount +
                "\nTotal: " + FormatHelper.rupiah(totalPrice);

        new AlertDialog.Builder(this)
                .setTitle("Pesanan Berhasil")
                .setMessage(message)
                .setPositiveButton("Lihat Riwayat", (dialog, which) -> {
                    startActivity(new android.content.Intent(this, HistoryActivity.class));
                    finish();
                })
                .setNegativeButton("Tutup", null)
                .show();
    }
}
