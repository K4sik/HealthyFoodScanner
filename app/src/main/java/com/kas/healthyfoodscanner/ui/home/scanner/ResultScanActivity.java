package com.kas.healthyfoodscanner.ui.home.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.database.DatabaseHelper;

public class ResultScanActivity extends AppCompatActivity {

    private TextView barcodeTv;

    private TextView companyNameTv;

    private TextView productNameTv;

    private TextView ingredientsTv;

    private TextView kcalTv;

    private TextView sugarTv;

    private TextView saltTv;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan);

        barcodeTv = findViewById(R.id.barcode_tv);
        companyNameTv = findViewById(R.id.company_name_tv);
        productNameTv = findViewById(R.id.product_name_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        kcalTv = findViewById(R.id.kcal_tv);
        sugarTv = findViewById(R.id.sugar_tv);
        saltTv = findViewById(R.id.salt_tv);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String barcode = extras.getString("barcode");

            databaseHelper = new DatabaseHelper(ResultScanActivity.this);
            Cursor cursor = databaseHelper.getProductByBarcode(Long.valueOf(barcode));

            cursor.moveToFirst();

            if (cursor.getCount() == 0){
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
                System.out.println("Company name = No data");
            } else {
                barcodeTv.setText(barcode);
                companyNameTv.setText(cursor.getString(1));
                productNameTv.setText(cursor.getString(2));
                ingredientsTv.setText(cursor.getString(3));
                kcalTv.setText(cursor.getString(4));
                sugarTv.setText(cursor.getString(5));
                saltTv.setText(cursor.getString(6));
            }

        } else {
            Toast.makeText(ResultScanActivity.this, "Barcode not found", Toast.LENGTH_SHORT).show();
        }
    }

}