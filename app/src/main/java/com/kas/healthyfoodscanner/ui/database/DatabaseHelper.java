package com.kas.healthyfoodscanner.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ProductLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "product";
    private static final String COLUMN_ID = "_barcode";
    private static final String COLUMN_COMPANY_NAME = "company_name";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_INGREDIENTS = "_ingredients";
    private static final String COLUMN_KCAL = "_kcal";
    private static final String COLUMN_SUGAR = "_sugar";
    private static final String COLUMN_SALT = "_salt";
    private static final String COLUMN_WHITES = "_whites";
    private static final String COLUMN_FAT = "_fat";
    private static final String COLUMN_CARBOHYDRATES = "_carbohydrates";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_COMPANY_NAME + " TEXT, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_INGREDIENTS + " TEXT, " +
                COLUMN_KCAL + " INTEGER, " +
                COLUMN_SUGAR + " INTEGER, " +
                COLUMN_SALT + " INTEGER, " +
                COLUMN_WHITES + " INTEGER, " +
                COLUMN_FAT + " INTEGER, " +
                COLUMN_CARBOHYDRATES + " INTEGER);";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(String barcode, String company_name, String product_name, String ingredients, int kcal, int sugar, int salt, int whites, int fat, int carbohydrates) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID, barcode);
        contentValues.put(COLUMN_COMPANY_NAME, company_name);
        contentValues.put(COLUMN_PRODUCT_NAME, product_name);
        contentValues.put(COLUMN_INGREDIENTS, ingredients);
        contentValues.put(COLUMN_KCAL, kcal);
        contentValues.put(COLUMN_SUGAR, sugar);
        contentValues.put(COLUMN_SALT, salt);
        contentValues.put(COLUMN_WHITES, whites);
        contentValues.put(COLUMN_FAT, fat);
        contentValues.put(COLUMN_CARBOHYDRATES, carbohydrates);

        long result = database.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Failed to insert Data!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor getAllProducts(){
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getProductByBarcode(long barcode){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + barcode;

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

}
