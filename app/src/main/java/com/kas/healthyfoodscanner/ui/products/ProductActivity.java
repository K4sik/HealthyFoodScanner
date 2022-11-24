package com.kas.healthyfoodscanner.ui.products;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView search_rv;
    private DatabaseHelper databaseHelper;
    private List<String> barcode, companyName, productName;
    private ProductAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_rv = findViewById(R.id.search_rv);

        databaseHelper = new DatabaseHelper(ProductActivity.this);
        barcode = new ArrayList<>();
        companyName = new ArrayList<>();
        productName = new ArrayList<>();

        parseAllProducts();

        searchAdapter = new ProductAdapter(ProductActivity.this, barcode, companyName, productName);
        search_rv.setAdapter(searchAdapter);
        search_rv.setLayoutManager(new LinearLayoutManager(ProductActivity.this));
    }

    private void parseAllProducts(){
        Cursor cursor = databaseHelper.getAllProducts();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                barcode.add(cursor.getString(0));
                companyName.add(cursor.getString(1));
                productName.add(cursor.getString(2));
            }
        }
    }
}