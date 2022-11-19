package com.kas.healthyfoodscanner.ui.home.scanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kas.healthyfoodscanner.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class AddProductActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_CODE = 100;

    private Button buttonCapture;

    private Button buttonSave;

    private ImageView imageViewResult;

    private TextView textViewData;

    private TextInputLayout textInputLayoutBarcode;

    private TextInputLayout textInputLayoutCompanyName;

    private TextInputLayout textInputLayoutProductName;

    private TextInputEditText textInputEditTextBarcode;

    private TextInputEditText textInputEditTextCompanyName;

    private TextInputEditText textInputEditTextProductName;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        imageViewResult = findViewById(R.id.image_view);

        textInputLayoutBarcode = findViewById(R.id.til_barcode);
        textInputLayoutBarcode.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                AddProductActivity.this.startActivity(intent);
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        textInputEditTextBarcode = findViewById(R.id.tiet_barcode);
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

        textInputLayoutCompanyName = findViewById(R.id.til_company_name);
        textInputLayoutCompanyName.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                AddProductActivity.this.startActivity(intent);
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        textInputEditTextCompanyName = findViewById(R.id.tiet_company_name);
        textInputEditTextCompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 12){
                    textInputLayoutCompanyName.setError("No more!");
                } else {
                    textInputLayoutCompanyName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textInputLayoutProductName = findViewById(R.id.til_product_name);
        textInputLayoutProductName.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                AddProductActivity.this.startActivity(intent);
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        textInputEditTextProductName = findViewById(R.id.tiet_product_name);
        textInputEditTextProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 12){
                    textInputLayoutProductName.setError("No more!");
                } else {
                    textInputLayoutProductName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textViewData = findViewById(R.id.tiet_description);

        if (ContextCompat.checkSelfPermission(AddProductActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddProductActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, REQUEST_CAMERA_CODE);
        } else {

            buttonCapture = findViewById(R.id.button_capture);
            buttonCapture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                    buttonCapture.setText("Retake");
//                    buttonSave.setVisibility(View.VISIBLE);
                    CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(AddProductActivity.this);
                }
            });

            buttonSave = findViewById(R.id.button_save);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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
            Toast.makeText(AddProductActivity.this, "Error Occurred!!!", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = textRecognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < textBlockSparseArray.size(); i++){
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
            }
            textViewData.setText(stringBuilder.toString());
            buttonCapture.setText("Retake");
            buttonSave.setVisibility(View.VISIBLE);
        }
    }
}