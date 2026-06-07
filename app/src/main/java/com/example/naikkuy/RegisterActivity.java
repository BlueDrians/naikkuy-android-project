package com.example.naikkuy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naikkuy.helper.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName, edtUsername, edtPassword, edtPhone;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        android.widget.TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        databaseHelper = new DatabaseHelper(this);

        edtName = findViewById(R.id.edtName);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> register());
    }

    private void register() {
        String name = edtName.getText().toString().trim();
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean saved = databaseHelper.registerUser(name, username, password, phone);
        if (saved) {
            Toast.makeText(this, "Akun berhasil dibuat, silakan login", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Username sudah digunakan", Toast.LENGTH_SHORT).show();
        }
    }
}
