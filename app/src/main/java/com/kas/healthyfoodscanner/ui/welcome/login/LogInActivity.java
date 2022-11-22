package com.kas.healthyfoodscanner.ui.welcome.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.kas.healthyfoodscanner.MainActivity;
import com.kas.healthyfoodscanner.R;

public class LogInActivity extends AppCompatActivity {

    private TextInputEditText username;

    private TextInputEditText password;

    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = findViewById(R.id.tiet_username);
        password = findViewById(R.id.tiet_password);

        loginBtn = findViewById(R.id.log_in_btn);
        loginBtn.setOnClickListener(view -> {

            if (username.getText().toString().equals("Admin") && password.getText().toString().equals("admin")){
                Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "Error enter valid Username and Password!", Toast.LENGTH_SHORT).show();
            }


        });

    }
}