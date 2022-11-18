//package com.kas.healthyfoodscanner.ui.home.scanner;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.util.SparseArray;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.vision.Frame;
//import com.google.android.gms.vision.text.TextBlock;
//import com.google.android.gms.vision.text.TextRecognizer;
//import com.google.firebase.ml.vision.FirebaseVision;
//import com.google.firebase.ml.vision.common.FirebaseVisionImage;
//import com.google.firebase.ml.vision.text.FirebaseVisionText;
//import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
//import com.kas.healthyfoodscanner.R;
//import com.kasarab.healthfoodscanner.R;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;
//
//import java.io.IOException;
//import java.util.List;
//
//public class Lol_kek_Activity extends AppCompatActivity {
//
//    private Button buttonCapture;
//
//    private Button buttonSave;
//
//    private ImageView imageView;
//
//    private TextView textViewData;
//
//    private Bitmap bitmap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lol_kek);
//
//        imageView = findViewById(R.id.image_view);
//        textViewData = findViewById(R.id.text_view_display);
//        buttonCapture = findViewById(R.id.button_capture);
//        buttonSave = findViewById(R.id.button_save);
//
//        buttonCapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(Lol_kek_Activity.this);
//            }
//        });
//
//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK){
//                Uri resultUri = result.getUri();
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
////                    getTextFromImage(bitmap);
//                    detectTextFromImage(bitmap);
//                    imageView.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
////            if (resultCode == RESULT_OK) {
////                Uri resultUri = result.getUri();
////            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
////                Exception error = result.getError();
////            }
//        }
//    }
//
//    private void getTextFromImage(Bitmap bitmap){
//        TextRecognizer textRecognizer = new TextRecognizer.Builder(this).build();
//        if (!textRecognizer.isOperational()){
//            Toast.makeText(Lol_kek_Activity.this, "Error Occurred!!!", Toast.LENGTH_SHORT).show();
//        } else {
//            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//            SparseArray<TextBlock> textBlockSparseArray = textRecognizer.detect(frame);
//            StringBuilder stringBuilder = new StringBuilder();
//
//            for (int i = 0; i < textBlockSparseArray.size(); i++){
//                TextBlock textBlock = textBlockSparseArray.valueAt(i);
//                stringBuilder.append(textBlock.getValue());
//                stringBuilder.append("\n");
//            }
//            textViewData.setText(stringBuilder.toString());
//            buttonCapture.setText("Retake");
//            buttonSave.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private void detectTextFromImage(Bitmap bitmap){
//        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);
//        FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer(); // can change to cloud // .getOnDeviceTextRecognizer()
//        firebaseVisionTextRecognizer.processImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
//            @Override
//            public void onSuccess(FirebaseVisionText firebaseVisionText) {
////                Toast.makeText(Lol_kek_Activity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
//                displayTextFromImage(firebaseVisionText);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Lol_kek_Activity.this, "Error Occurred!!!" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                Log.d("Error: ", e.getMessage());
//            }
//        });
//    }
//
//    private void displayTextFromImage(FirebaseVisionText firebaseVisionText){
//        List<FirebaseVisionText.TextBlock> blockList = firebaseVisionText.getTextBlocks();
//
//        if (blockList.size() == 0) {
//            Toast.makeText(Lol_kek_Activity.this, "No Text Found in Image", Toast.LENGTH_SHORT).show();
//        } else {
//            for (FirebaseVisionText.TextBlock textBlock : firebaseVisionText.getTextBlocks()){
//                String text = textBlock.getText();
//                textViewData.setText(text);
//            }
//            buttonCapture.setText("Retake");
//            buttonSave.setVisibility(View.VISIBLE);
//        }
//
//    }
//}