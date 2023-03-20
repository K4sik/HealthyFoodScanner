package com.kas.healthyfoodscanner.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.kas.healthyfoodscanner.MainActivity;
import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.product.AddProductActivity;

public class PopUpAddProductActivity extends AppCompatActivity {

    private TextView yesTv;

    private TextView noTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_add_product);

        yesTv = findViewById(R.id.yes_tv);
        noTv = findViewById(R.id.no_tv);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width*.9), (int) (height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        noTv.setOnClickListener(view -> {
            startActivity(new Intent(PopUpAddProductActivity.this, MainActivity.class));
        });

        yesTv.setOnClickListener(view -> {
            startActivity(new Intent(PopUpAddProductActivity.this, AddProductActivity.class));
        });
    }
}