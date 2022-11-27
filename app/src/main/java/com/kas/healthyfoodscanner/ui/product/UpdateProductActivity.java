package com.kas.healthyfoodscanner.ui.product;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.database.DatabaseHelper;
import com.kas.healthyfoodscanner.ui.home.scanner.BarcodeScannerActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class UpdateProductActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_CODE = 100;

    private Button buttonCapture;

    private Button buttonUpdate;

    private ImageView imageViewResult;

    private TextInputLayout textInputLayoutBarcode;

    private TextInputLayout textInputLayoutCompanyName;

    private TextInputLayout textInputLayoutProductName;

    private TextInputLayout textInputLayoutDescription;

    private TextInputEditText textInputEditTextBarcode;

    private TextInputEditText textInputEditTextCompanyName;

    private TextInputEditText textInputEditTextProductName;

    private TextInputEditText textInputEditTextDescription;

    private TextInputEditText textInputEditTextKcal;

    private TextInputEditText textInputEditTextSugar;

    private TextInputEditText textInputEditTextSalt;

    private TextInputEditText textInputEditTextWhites;

    private TextInputEditText textInputEditTextSatFat;

    private TextInputEditText textInputEditTextCarbohydrates;

    private Bitmap bitmap;

    private DatabaseHelper databaseHelper;

    private String barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        imageViewResult = findViewById(R.id.image_view_update);

        textInputLayoutBarcode = findViewById(R.id.til_barcode_update);
        textInputLayoutBarcode.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                UpdateProductActivity.this.startActivity(intent);
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        textInputEditTextBarcode = findViewById(R.id.tiet_barcode_update);
        textInputEditTextBarcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 12){
                    textInputLayoutBarcode.setError("No more!");
                } else {
                    textInputLayoutBarcode.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textInputLayoutCompanyName = findViewById(R.id.til_company_name_update);
        textInputLayoutCompanyName.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                UpdateProductActivity.this.startActivity(intent);
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        textInputEditTextCompanyName = findViewById(R.id.tiet_company_name_update);
        textInputEditTextCompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                if (charSequence.length() > 12){
//                    textInputLayoutCompanyName.setError("No more!");
//                } else {
//                    textInputLayoutCompanyName.setError(null);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textInputLayoutProductName = findViewById(R.id.til_product_name_update);
        textInputLayoutProductName.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                UpdateProductActivity.this.startActivity(intent);
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        textInputEditTextProductName = findViewById(R.id.tiet_product_name_update);
        textInputEditTextProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                if (charSequence.length() > 12){
//                    textInputLayoutProductName.setError("No more!");
//                } else {
//                    textInputLayoutProductName.setError(null);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textInputEditTextDescription = findViewById(R.id.tiet_description_update);

        textInputEditTextKcal = findViewById(R.id.tiet_kcal_update);

        textInputEditTextSugar = findViewById(R.id.tiet_sugar_update);

        textInputEditTextSalt = findViewById(R.id.tiet_salt_update);

        textInputEditTextWhites = findViewById(R.id.tiet_whites_update);

        textInputEditTextSatFat = findViewById(R.id.tiet_fat_update);

        textInputEditTextCarbohydrates = findViewById(R.id.tiet_carbohydrates_update);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            barcode = extras.getString("barcode");

            databaseHelper = new DatabaseHelper(UpdateProductActivity.this);
            Cursor cursor = databaseHelper.getProductByBarcode(Long.valueOf(barcode));

            cursor.moveToFirst();

            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
                System.out.println("Company name = No data");
            } else {

                textInputEditTextBarcode.setText(cursor.getString(0));
                textInputEditTextCompanyName.setText(cursor.getString(1));
                textInputEditTextProductName.setText(cursor.getString(2));
                textInputEditTextDescription.setText(cursor.getString(3));
                textInputEditTextKcal.setText(cursor.getString(4));
                textInputEditTextSugar.setText(cursor.getString(5));
                textInputEditTextSalt.setText(cursor.getString(6));
                textInputEditTextWhites.setText(cursor.getString(7));
                textInputEditTextSatFat.setText(cursor.getString(8));
                textInputEditTextCarbohydrates.setText(cursor.getString(9));

            }
        }

        if (ContextCompat.checkSelfPermission(UpdateProductActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(UpdateProductActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, REQUEST_CAMERA_CODE);
        } else {

            buttonCapture = findViewById(R.id.button_capture_update);
            buttonCapture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                    buttonCapture.setText("Retake");
//                    buttonSave.setVisibility(View.VISIBLE);
                    CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(UpdateProductActivity.this);
                }
            });

            buttonUpdate = findViewById(R.id.button_update);
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(UpdateProductActivity.this);
                    databaseHelper.updateProduct( barcode,
                            textInputEditTextBarcode.getText().toString(),
                            textInputEditTextCompanyName.getText().toString(),
                            textInputEditTextProductName.getText().toString(),
                            textInputEditTextDescription.getText().toString(),
                            Integer.valueOf(textInputEditTextKcal.getText().toString()),
                            Integer.valueOf(textInputEditTextSugar.getText().toString()),
                            Integer.valueOf(textInputEditTextSalt.getText().toString()),
                            Integer.valueOf(textInputEditTextWhites.getText().toString()),
                            Integer.valueOf(textInputEditTextSatFat.getText().toString()),
                            Integer.valueOf(textInputEditTextCarbohydrates.getText().toString())
                    );
                    startActivity(new Intent(UpdateProductActivity.this, ProductActivity.class));
                }
            });

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    getTextFromImage(bitmap);
                    imageViewResult.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            if (resultCode == RESULT_OK) {
//                Uri resultUri = result.getUri();
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//            }
        }
    }

    private void getTextFromImage(Bitmap bitmap){
        TextRecognizer textRecognizer = new TextRecognizer.Builder(this).build();
        if (!textRecognizer.isOperational()){
            Toast.makeText(UpdateProductActivity.this, "Error Occurred!!!", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = textRecognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < textBlockSparseArray.size(); i++){
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
            }
            textInputEditTextDescription.setText(stringBuilder.toString());
            buttonCapture.setText("Retake");
            buttonUpdate.setVisibility(View.VISIBLE);
        }
    }
}