package com.kas.healthyfoodscanner.ui.home.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.database.DatabaseHelper;
import com.kas.healthyfoodscanner.ui.home.PopUpAddProductActivity;
import com.kas.healthyfoodscanner.ui.product.AddProductActivity;

public class ResultScanActivity extends AppCompatActivity {

    private TextView barcodeTv;

    private TextView companyNameTv;

    private TextView productNameTv;

    private TextView ingredientsTv;

    private TextView kcalTv;

    private TextView sugarTv;

    private TextView saltTv;

    private TextView whitesTv;

    private TextView satFatTv;

    private TextView carbohydratesTv;

    private ImageView kcalIv;

    private ImageView sugarIv;

    private ImageView saltIv;

    private ImageView whitesIv;

    private ImageView satFatIv;

    private ImageView carbohydratesIv;

    private Button checkProductButton;

    private DatabaseHelper databaseHelper;

    private int danger = 0;

    private int warning = 0;

    private int allowed = 0;

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
        whitesTv = findViewById(R.id.whites_tv);
        satFatTv = findViewById(R.id.fat_tv);
        carbohydratesTv = findViewById(R.id.carbohydrates_tv);
        kcalIv = findViewById(R.id.kcal_iv);
        sugarIv = findViewById(R.id.sugar_iv);
        saltIv = findViewById(R.id.salt_iv);
        whitesIv = findViewById(R.id.whites_iv);
        satFatIv = findViewById(R.id.fat_iv);
        carbohydratesIv = findViewById(R.id.carbohydrates_iv);
        checkProductButton = findViewById(R.id.check_product_button);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String barcode = extras.getString("barcode");
//            String barcode = "987654321098";
//            String barcode = "4823000913691";

            databaseHelper = new DatabaseHelper(ResultScanActivity.this);
            Cursor cursor = databaseHelper.getProductByBarcode(Long.valueOf(barcode));

            cursor.moveToFirst();

            if (cursor.getCount() == 0){
                Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ResultScanActivity.this, PopUpAddProductActivity.class));
            } else {
                barcodeTv.setText(barcode);
//                companyNameTv.setText(cursor.getString(1));
                productNameTv.setText(cursor.getString(2));
                productNameTv.append("(" + cursor.getString(1) + ")");
                ingredientsTv.setText(cursor.getString(3));
                kcalTv.setText(cursor.getString(4));
                sugarTv.setText(cursor.getString(5));
                saltTv.setText(cursor.getString(6));
                whitesTv.setText(cursor.getString(7));
                satFatTv.setText(cursor.getString(8));
                carbohydratesTv.setText(cursor.getString(9));

                if (Integer.valueOf(cursor.getString(4)) > 100 && Integer.valueOf(cursor.getString(4)) <= 500) {
                    kcalIv.setImageResource(R.drawable.yellow_result);
                    warning++;
                } else if (Integer.valueOf(cursor.getString(4)) > 500){
                    kcalIv.setImageResource(R.drawable.red_result);
                    danger++;
                } else {
                    kcalIv.setImageResource(R.drawable.green_result);
                    allowed++;
                }

                if (Integer.valueOf(cursor.getString(5)) > 100 && Integer.valueOf(cursor.getString(5)) <= 500) {
                    sugarIv.setImageResource(R.drawable.yellow_result);
                    warning++;
                } else if (Integer.valueOf(cursor.getString(5)) > 500) {
                    sugarIv.setImageResource(R.drawable.red_result);
                    danger++;
                } else {
                    sugarIv.setImageResource(R.drawable.green_result);
                    allowed++;
                }

                if (Integer.valueOf(cursor.getString(6)) > 100 && Integer.valueOf(cursor.getString(6)) <= 500) {
                    saltIv.setImageResource(R.drawable.yellow_result);
                    warning++;
                } else if (Integer.valueOf(cursor.getString(6)) > 500) {
                    saltIv.setImageResource(R.drawable.red_result);
                    danger++;
                } else {
                    saltIv.setImageResource(R.drawable.green_result);
                    allowed++;
                }

                if (Integer.valueOf(cursor.getString(7)) > 100 && Integer.valueOf(cursor.getString(7)) <= 500) {
                    whitesIv.setImageResource(R.drawable.yellow_result);
                    warning++;
                } else if (Integer.valueOf(cursor.getString(7)) > 500){
                    whitesIv.setImageResource(R.drawable.red_result);
                    danger++;
                } else {
                    whitesIv.setImageResource(R.drawable.green_result);
                    allowed++;
                }

                if (Integer.valueOf(cursor.getString(8)) > 100 && Integer.valueOf(cursor.getString(8)) <= 500) {
                    satFatIv.setImageResource(R.drawable.yellow_result);
                    warning++;
                } else if (Integer.valueOf(cursor.getString(8)) > 500) {
                    satFatIv.setImageResource(R.drawable.red_result);
                    danger++;
                } else {
                    satFatIv.setImageResource(R.drawable.green_result);
                    allowed++;
                }

                if (Integer.valueOf(cursor.getString(9)) > 100 && Integer.valueOf(cursor.getString(9)) <= 500) {
                    carbohydratesIv.setImageResource(R.drawable.yellow_result);
                    warning++;
                } else if (Integer.valueOf(cursor.getString(9)) > 500) {
                    carbohydratesIv.setImageResource(R.drawable.red_result);
                    danger++;
                } else {
                    carbohydratesIv.setImageResource(R.drawable.green_result);
                    allowed++;
                }

                checkProductButton.setOnClickListener(view -> {
                    if (danger >= warning && danger >= allowed) {
                    Toast.makeText(this, "This product is Danger for your health!\nWe don't recommend you eat it!", Toast.LENGTH_LONG).show();
                    } else if (warning > danger && warning >= allowed) {
                        Toast.makeText(this, "This product isn't Good for your health!\nНou can eat it at your own risk!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "This product is Good for your health!", Toast.LENGTH_LONG).show();
                    }
                });

            }

        } else {
            Toast.makeText(ResultScanActivity.this, "Barcode not found", Toast.LENGTH_SHORT).show();
        }
    }

}