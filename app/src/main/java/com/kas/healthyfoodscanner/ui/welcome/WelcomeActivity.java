package com.kas.healthyfoodscanner.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.welcome.login.LogInActivity;
import com.kas.healthyfoodscanner.ui.welcome.signup.SignUpActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button logInButton;

    private Button singUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        logInButton = findViewById(R.id.log_in_btn);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            }
        });

        singUpButton = findViewById(R.id.sign_in_btn);
        singUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

    }
}