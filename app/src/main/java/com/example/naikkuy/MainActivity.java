package com.example.naikkuy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.naikkuy.helper.DatabaseHelper;
import com.example.naikkuy.helper.SessionManager;
import com.example.naikkuy.model.UserModel;

public class MainActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        databaseHelper = new DatabaseHelper(this);

        if (!sessionManager.isLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        TextView txtGreeting = findViewById(R.id.txtGreeting);
        UserModel user = databaseHelper.getUser(sessionManager.getUsername());
        if (user != null) {
            txtGreeting.setText("Halo, " + user.getName());
        }

        CardView cardRoute = findViewById(R.id.cardRoute);
        CardView cardSchedule = findViewById(R.id.cardSchedule);
        CardView cardBooking = findViewById(R.id.cardBooking);
        CardView cardHistory = findViewById(R.id.cardHistory);
        CardView cardProfile = findViewById(R.id.cardProfile);

        cardRoute.setOnClickListener(v -> startActivity(new Intent(this, RouteActivity.class)));
        cardSchedule.setOnClickListener(v -> startActivity(new Intent(this, ScheduleActivity.class)));
        cardBooking.setOnClickListener(v -> startActivity(new Intent(this, BookingActivity.class)));
        cardHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        cardProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
