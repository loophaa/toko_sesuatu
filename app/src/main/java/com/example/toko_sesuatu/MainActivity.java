package com.example.toko_sesuatu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; // Firebase Authentication

    private EditText emailEditText, passwordEditText; // Input untuk email dan password
    private Button loginButton; // Tombol login

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Menghubungkan elemen layout
        EditText emailEditText = findViewById(R.id.editTextEmail);
        EditText passwordEditText = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);

        // Event handler untuk tombol login
        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Validasi input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Email dan password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Proses login menggunakan Firebase Authentication
            loginUser(email, password);
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login berhasil
                        Toast.makeText(MainActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                        Log.d("LoginStatus", "Login berhasil dengan email: " + email);
                    } else {
                        // Login gagal
                        Toast.makeText(MainActivity.this, "Login gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("LoginStatus", "Error: ", task.getException());
                    }
                });
    }
}
