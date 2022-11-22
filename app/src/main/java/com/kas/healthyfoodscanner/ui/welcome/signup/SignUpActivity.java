package com.kas.healthyfoodscanner.ui.welcome.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.welcome.login.LogInActivity;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText username;

    private TextInputEditText password;

    private TextInputEditText confirmPassword;

    private Button signUpBtn;

    private TextView loginTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginTv = findViewById(R.id.log_in_tv);
        loginTv.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        });

    }
}