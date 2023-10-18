package com.kas.healthyfoodscanner.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.database.DatabaseHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView search_rv;
    private SearchAdapter searchAdapter;
    private ArrayList<SearchItem> searchItems = new ArrayList<>();
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Content content = new Content();

        search_rv = findViewById(R.id.search_rv);
        progress_bar = findViewById(R.id.progress_bar);

        search_rv.setHasFixedSize(true);
        search_rv.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(searchItems, this);
        search_rv.setAdapter(searchAdapter);

//        content.execute();
        content.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        searchItems = new ArrayList<>();

    }


    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_bar.setVisibility(View.VISIBLE);
            progress_bar.startAnimation(AnimationUtils.loadAnimation(SearchActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress_bar.setVisibility(View.GONE);
            progress_bar.startAnimation(AnimationUtils.loadAnimation(SearchActivity.this, android.R.anim.fade_out));
            searchAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = "https://uk.dobavkam.net/food";

                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div.views-row");
                int size = data.size();
                Log.d("doc", "doc: "+doc);
                Log.d("data", "data: "+data);
                Log.d("size", ""+size);
                for (int i = 0; i < size; i++) {
//                    ok
                    String imgUrl = url.substring(0, url.length() - 4) + data.select("div.product-big-card__image")
                            .select("img")
                            .eq(i)
                            .attr("data-src");

//                    ok
                    String productName = data.select("h2.product-big-card__title")
                            .select("span")
                            .eq(i)
                            .text();

//                    ok
                    String whites = data.select("tr.protein")
                            .select("td")
                            .eq(i)
                            .text();

                    String fats = data.select("tr.fat")
                            .select("td")
                            .eq(i)
                            .text();

                    String carbohydrates = data.select("tr.carb")
                            .select("td")
                            .eq(i)
                            .text();

                    String calories = data.select("tr.calories")
                            .select("td")
                            .eq(i)
                            .text();

//                    ok
                    String barcode = data.select("div.field--barcode")
                            .eq(i)
                            .text();

//                    ok
                    String additives = data.select("div.field--additives")
                            .select("div")
                            .eq(i)
                            .text();

                    String detailUrl = data.select("div.product-big-card")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    String detailedUrl = "https://bscanner.com.ua/" + barcode.substring(10);

                    String deleteFromString = "Харчові добавки Е ";
                    String cleanAdditives;
                    if (additives.contains(deleteFromString)) {
                        cleanAdditives = additives.substring(18);
                    } else {
                        cleanAdditives = additives;
                    }

                    searchItems.add(new SearchItem(
                            barcode.substring(10),
                            imgUrl,
                            productName,
                            whites,//.substring(8, whites.length() - 2),
                            fats,//.substring(7, fats.length() - 2),
                            carbohydrates,//.substring(12, carbohydrates.length() - 2),
                            calories,//.substring(15, calories.length() - 5),
                            cleanAdditives,
                            "4823036503002"));
                    Log.d("items", "img: " + imgUrl + " . title: " + productName
                            + ", Whites: " + whites.substring(8, whites.length() - 2)
                            + ", Fats: " + fats.substring(7, fats.length() - 2)
                            + ", Carbohydrates: " + carbohydrates.substring(12, carbohydrates.length() - 2)
                            + ", Calories: " + calories.substring(15, calories.length() - 5)
                            + ", barcode: " + barcode.substring(10)
                            + ", additives: " + cleanAdditives
                            + ", url: " + detailedUrl);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

}