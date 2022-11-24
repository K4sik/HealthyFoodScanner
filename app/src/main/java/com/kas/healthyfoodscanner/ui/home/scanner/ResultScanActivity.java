package com.kas.healthyfoodscanner.ui.home.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kas.healthyfoodscanner.R;

public class ResultScanActivity extends AppCompatActivity {

    TextView barcodeTv;

    TextView companyNameTv;

    TextView productNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan);

        barcodeTv = findViewById(R.id.barcode_tv);
        companyNameTv = findViewById(R.id.company_name_tv);
        productNameTv = findViewById(R.id.product_name_tv);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String barcode = extras.getString("barcode");
            barcodeTv.setText(barcode);
        } else {
            Toast.makeText(ResultScanActivity.this, "Barcode not found", Toast.LENGTH_SHORT).show();
        }
    }
}