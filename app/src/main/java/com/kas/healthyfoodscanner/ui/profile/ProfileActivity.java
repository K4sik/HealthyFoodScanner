package com.kas.healthyfoodscanner.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kas.healthyfoodscanner.R;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout general_info_ll;
    private TextView general_info_tv;
    private LinearLayout diseases_info_ll;
    private TextView diseases_info_tv;
    private LinearLayout allergies_info_ll;
    private TextView allergies_info_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        general_info_ll = findViewById(R.id.general_info_ll);
        general_info_tv = findViewById(R.id.general_info_tv);
        diseases_info_ll = findViewById(R.id.diseases_info_ll);
        diseases_info_tv = findViewById(R.id.diseases_info_tv);
        allergies_info_ll = findViewById(R.id.allergies_info_ll);
        allergies_info_tv = findViewById(R.id.allergies_info_tv);

        general_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general_info_ll.setVisibility(View.VISIBLE);
                diseases_info_ll.setVisibility(View.GONE);
                allergies_info_ll.setVisibility(View.GONE);
            }
        });

        diseases_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general_info_ll.setVisibility(View.GONE);
                diseases_info_ll.setVisibility(View.VISIBLE);
                allergies_info_ll.setVisibility(View.GONE);
            }
        });

        allergies_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general_info_ll.setVisibility(View.GONE);
                diseases_info_ll.setVisibility(View.GONE);
                allergies_info_ll.setVisibility(View.VISIBLE);
            }
        });

    }
}