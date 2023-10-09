package com.kas.healthyfoodscanner.ui.product;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.googlecode.tesseract.android.TessBaseAPI;
import com.kas.healthyfoodscanner.MainActivity;
import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.database.DatabaseHelper;
import com.kas.healthyfoodscanner.ui.home.scanner.BarcodeScannerActivity;
import com.kas.healthyfoodscanner.ui.ocr.OcrManager;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AddProductActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_CODE = 100;

    private Button buttonIngredientsCapture;

    private Button buttonSave;

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

    public static AddProductActivity instance = null;

    public static final String TESS_DATA = "/tessdata/";
    private static final String TAG = AddProductActivity.class.getSimpleName();
    private static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/Tess/";
    private TextView textView;
    private TessBaseAPI tessBaseAPI;
    private Uri outputFileDir;
    private String mCurrentPhotoPath;

    OcrManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        instance = this;
        copyTessDataForTextRecognizer();
        manager = new OcrManager();

//        prepareTessData();

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

        textInputEditTextDescription = findViewById(R.id.tiet_description);

        textInputEditTextKcal = findViewById(R.id.tiet_kcal);

        textInputEditTextSugar = findViewById(R.id.tiet_sugar);

        textInputEditTextSalt = findViewById(R.id.tiet_salt);

        textInputEditTextWhites = findViewById(R.id.tiet_whites);

        textInputEditTextSatFat = findViewById(R.id.tiet_fat);

        textInputEditTextCarbohydrates = findViewById(R.id.tiet_carbohydrates);

        if (ContextCompat.checkSelfPermission(AddProductActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddProductActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, REQUEST_CAMERA_CODE);
        } else {

            buttonIngredientsCapture = findViewById(R.id.button_ingredients_capture);
            buttonIngredientsCapture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    buttonIngredientsCapture.setText("Retake");
//                    buttonSave.setVisibility(View.VISIBLE);
                    CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(AddProductActivity.this);
                }
            });

            buttonSave = findViewById(R.id.button_save);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(AddProductActivity.this);
                    databaseHelper.addProduct(textInputEditTextBarcode.getText().toString(),
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
                    startActivity(new Intent(AddProductActivity.this, MainActivity.class));
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
//                    prepareTessData();
//                    startOCR(bitmap);

                    manager.initAPI();

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
            String str = manager.startRecognize(bitmap);

//            String str = getText(bitmap);
//            String str = startOCR(bitmap);

//            stringBuilder.append(str);
//            for (int i = 0; i < textBlockSparseArray.size(); i++){
//                TextBlock textBlock = textBlockSparseArray.valueAt(i);
//                stringBuilder.append(textBlock.getValue());
//                stringBuilder.append("\n");
//            }
            textInputEditTextDescription.setText(stringBuilder.toString());
            textInputEditTextDescription.setText(str);
            buttonIngredientsCapture.setText("Retake");
            buttonSave.setVisibility(View.VISIBLE);
        }
    }

    private void copyTessDataForTextRecognizer() {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                AssetManager assetManager = AddProductActivity.instance.getAssets();
                OutputStream outputStream = null;
                try {
                    InputStream inputStream = assetManager.open("ukr.traineddata");
                    String tesspath = instance.tessDataPath();
                    File tessFolder = new File(tesspath);
                    if(!tessFolder.exists())
                        tessFolder.mkdir();
                    String tessData = tesspath+"/"+"ukr.traineddata";
                    File tessFile = new File(tessData);
                    if(!tessFile.exists())
                    {
                        outputStream = new FileOutputStream(tessData);
                        byte[] buffer = new byte[1024];
                        int read = inputStream.read(buffer);
                        while (read != -1) {
                            outputStream.write(buffer, 0, read);
                            read = inputStream.read(buffer);
                        }
                        Log.d("AddProductActivity", " Did finish copy tess file  ");
                    }
                    else
                        Log.d("AddProductActivity", " tess file exist  ");

                } catch (Exception e)
                {
                    Log.d("AddProductActivity", "couldn't copy with the following error : "+e.toString());
                }finally {
                    try {
                        if(outputStream!=null)
                            outputStream.close();
                    }catch (Exception exx)
                    {

                    }
                }
            }
        };
        new Thread(run).start();

    }

    private String tessDataPath() {
        return AddProductActivity.instance.getExternalFilesDir(null)+"/tessdata/";
    }

    public String getTessDataParentDirectory() {
        return AddProductActivity.instance.getExternalFilesDir(null).getAbsolutePath();
    }

    //doesn't work properly

//    private String getText(Bitmap bitmap){
//        try{
//            tessBaseAPI = new TessBaseAPI();
//        }catch (Exception e){
//            Log.e(TAG, e.getMessage());
//        }
//        tessBaseAPI.init(DATA_PATH,"ukr");
//        tessBaseAPI.setImage(bitmap);
//        String retStr = "No result";
//        try{
//            retStr = tessBaseAPI.getUTF8Text();
//        }catch (Exception e){
//            Log.e(TAG, e.getMessage());
//        }
//        tessBaseAPI.end();
//        return retStr;
//    }

//    private void prepareTessData()
//    {
//        try{
//            File dir = getExternalFilesDir(TESS_DATA);
//            if(!dir.exists()){
//                if (!dir.mkdir()) {
//                    Toast.makeText(getApplicationContext(), "The folder " + dir.getPath() + "was not created", Toast.LENGTH_SHORT).show();
//                }
//            }
//            String fileList[] = getAssets().list("");
//            for(String fileName : fileList){
//                String pathToDataFile = dir + "/" + fileName;
//                int ino=fileName.lastIndexOf(".");
//                String extension = (ino>=0)?fileName.substring(ino):"";
//                if((new File(pathToDataFile)).exists() && extension.equals(".traineddata")){
//                    InputStream in = getAssets().open(fileName);
//                    OutputStream out = new FileOutputStream(pathToDataFile);
//                    byte [] buff = new byte[1024];
//                    int len ;
//                    while(( len = in.read(buff)) > 0){
//                        out.write(buff,0,len);
//                    }
//                    in.close();
//                    out.close();
//                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private String startOCR(Bitmap bitmap){
//        String result = "";
//        try{
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = false;
//            options.inSampleSize = 6;
////            Bitmap bitmap2 = BitmapFactory.decodeFile(bitmap, options);
//            result = getText(bitmap);
//            return result;
////            textInputEditTextDescription.setText(result);
//        }catch (Exception e){
//            Log.e(TAG, e.getMessage());
//        }
//        return result;
//    }

}