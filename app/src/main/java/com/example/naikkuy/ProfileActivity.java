package com.example.naikkuy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naikkuy.helper.DatabaseHelper;
import com.example.naikkuy.helper.SessionManager;
import com.example.naikkuy.model.UserModel;

public class ProfileActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        android.widget.TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        sessionManager = new SessionManager(this);
        databaseHelper = new DatabaseHelper(this);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtUsername = findViewById(R.id.txtUsername);
        TextView txtPhone = findViewById(R.id.txtPhone);
        Button btnLogout = findViewById(R.id.btnLogout);

        UserModel user = databaseHelper.getUser(sessionManager.getUsername());
        if (user != null) {
            txtName.setText("Nama: " + user.getName());
            txtUsername.setText("Username: " + user.getUsername());
            txtPhone.setText("No HP: " + user.getPhone());
        }

        btnLogout.setOnClickListener(v -> {
            sessionManager.logout();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
