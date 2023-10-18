package com.kas.healthyfoodscanner.ui.home.scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.database.DatabaseHelper;
import com.kas.healthyfoodscanner.ui.home.PopUpAddProductActivity;
import com.kas.healthyfoodscanner.ui.search.SearchActivity;
import com.kas.healthyfoodscanner.ui.search.SearchAdapter;
import com.kas.healthyfoodscanner.ui.search.SearchItem;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResScanActivity extends AppCompatActivity {
    private ProgressBar progress_bar;
    private ImageView product_iv;
    private String imgUrl;
    private TextView product_name_tv;
    private TextView barcode_tv;
    private TextView country_code_tv;
    private TextView address_tv;
    private TextView ingredients_tv;
    private String barcode;
    private String barcodes;
    private String productName;
    private String countryCode;
    private String address;
    private List<String> ingredientList = new ArrayList<>();
    private GridLayout buttonContainer;
    private ImageView product_energy;
    private ImageView product_nutriscore_iv;
    private ImageView product_nutriscore_exp_iv;
    private List<Map<String, String>> dataList = new ArrayList<>();
    private Map<String, String> dataMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_scan);

        Content content = new Content();

        product_iv = findViewById(R.id.product_iv);
        barcode_tv = findViewById(R.id.barcode_tv);
        product_name_tv = findViewById(R.id.product_name_tv);
        country_code_tv = findViewById(R.id.country_code_tv);
        address_tv = findViewById(R.id.address_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
        buttonContainer = findViewById(R.id.button_container);
        product_energy = findViewById(R.id.product_energy);
        product_nutriscore_iv = findViewById(R.id.product_nutriscore_iv);
        product_nutriscore_exp_iv = findViewById(R.id.product_nutriscore_exp_iv);

        progress_bar = findViewById(R.id.progress_bar);




        Bundle extras = getIntent().getExtras();

//        if (extras != null) {
//            barcode = extras.getString("barcode");
//        barcode = "4823036503002";
        barcode = "4823000916524";

        content.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


//        } else {
//        Toast.makeText(ResScanActivity.this, "Barcode was not found", Toast.LENGTH_SHORT).show();
//        }


    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_bar.setVisibility(View.VISIBLE);
            progress_bar.startAnimation(AnimationUtils.loadAnimation(ResScanActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress_bar.setVisibility(View.GONE);
            progress_bar.startAnimation(AnimationUtils.loadAnimation(ResScanActivity.this, android.R.anim.fade_out));
            Picasso.get().load(imgUrl).into(product_iv);
            product_name_tv.setText(productName);
            barcode_tv.setText(barcodes);
            country_code_tv.setText(countryCode);
            address_tv.setText(address);

            for ( String item : ingredientList ) {
                if (!item.contains("help")){        // переробити
                    ingredients_tv.append( " * " + item + "\n");
                }
            }

            for (Map.Entry<String, String> entry : dataMap.entrySet()) {

                Button button = new Button(ResScanActivity.this);
                button.setText(entry.getKey());
                button.setBackgroundColor(Color.parseColor(entry.getValue().trim()));
                button.setWidth(20);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setMargins(8, 8, 8, 8);
                button.setLayoutParams(params);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String buttonLabel = ((Button) view).getText().toString();

                        Toast.makeText(ResScanActivity.this, "" + buttonLabel, Toast.LENGTH_SHORT).show();
                    }
                });

                buttonContainer.addView(button);
            }

            String uri_1 = "@drawable/energy_1";
            String uri_2 = "@drawable/artek_1";
            String uri_3 = "@drawable/artek_2";

            String uri_1_2 = "@drawable/energy_2";
            String uri_2_2 = "@drawable/artek_2_2";
            String uri_3_2 = "@drawable/artek_2_2";

            int imageResource_1 = getResources().getIdentifier(uri_1, null, getPackageName());
//            Drawable res = getResources().getDrawable(imageResource_1);
            product_energy.setImageDrawable(getResources().getDrawable(imageResource_1));
            int imageResource_2 = getResources().getIdentifier(uri_2, null, getPackageName());
//            Drawable res = getResources().getDrawable(imageResource_1);
            product_nutriscore_iv.setImageDrawable(getResources().getDrawable(imageResource_2));
            int imageResource_3 = getResources().getIdentifier(uri_3, null, getPackageName());
//            Drawable res = getResources().getDrawable(imageResource_1);
            product_nutriscore_exp_iv.setImageDrawable(getResources().getDrawable(imageResource_3));

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = "https://bscanner.com.ua/" + barcode;

                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("section.product");
                Log.d("doc", "doc: " + doc);
                Log.d("data", "data: " + data);

                // get imageUrl
                imgUrl = data.select("div.product__image")
                        .select("img")
//                            .eq(i)
                        .attr("src");

                Elements product_description = doc.select("div.product__description");

                // get productName
                productName = product_description.select("h4.product__description--title").text();

                // get barcodes
                barcodes = product_description.select("p.product__description--subtitle").text();

                // get country
                countryCode = product_description.select("p.product__description--text").first().text();

                // get nutritionalProperties
                String nutritionalProperties = product_description.select("p.product__description--text").last().text();

                // get all address
                String allAddress = product_description.select("p.product__description--text").text();
                // get actual address
                address = allAddress.substring(countryCode.length() + 1, allAddress.length() - nutritionalProperties.length());

//                Elements nutriscore = doc.select("div.nutriscore-selected-B");
////                    ok
//                String nutriscore_B = nutriscore.select("span.nutriscore-B").first().text();


                Log.d("items", "img: " + imgUrl
                        + ", \ntitle: " + productName
                        + ", \nbarcodes: " + barcodes
                        + ", \ncountryCode: " + countryCode
                        + ", \naddress: " + address);

                // get ingredients
                Elements ulElements = doc.select("ul.info_content");
                Elements liElements = ulElements.select("li");

                for (Element liElement : liElements) {
                    ingredientList.add(liElement.text());
                }
                ingredientList.forEach(i -> Log.d("ingredients", "= " + i));

//                Elements divElement = doc.select("div.info__content");
//                Elements divElements = divElement.select("div.dropdown-on-hover");
//
//                String nutriName = divElements.select()
//
//                for (Element divEl: divElements) {
////                    ingredientList.add(divElement.text());
//                    Log.d("nutri", "= " + divEl.text());
//                }

                // get a nutrition blocks
                // Select all <div> elements with class "supplements_item"
                Elements supplementItems = doc.select("div.supplements_item");

                // Iterate through each <div> element and extract text and background-color
                for (Element supplementItem : supplementItems) {
                    String text = supplementItem.text();
                    String style = supplementItem.attr("style");

                    // Extract background-color from the style attribute
                    String backgroundColor = "";
                    String[] styleAttributes = style.split(";");
                    for (String attr : styleAttributes) {
                        if (attr.trim().startsWith("background-color:")) {
                            backgroundColor = attr.trim().substring("background-color:".length());
                            break;
                        }
                    }

                    // Put the text and background-color into the map
                    dataMap.put(text, backgroundColor);
                }

                // You now have a Map where keys are the text values, and values are the background-color values
                for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                    String text = entry.getKey();
                    String backgroundColor = entry.getValue();
                    Log.d("Text", text);
                    Log.d("Background-Color", backgroundColor);
                }

                // Nutritional value
                // Select the outer container div with class "energy-value__tabl energy-value-position"
                Element energyValueContainer = doc.select("div.energy-value__tabl.energy-value-position").first();

                if (energyValueContainer != null) {
                    // Create a list or map to store the extracted data


                    // Select all rows within the container
                    Elements rows = energyValueContainer.select("div.energy-value__tabl--row");

//                    for (Element row : rows) {
//                        Map<String, String> rowData = new HashMap<>();
//
//                        // Extract data within each row
//                        Elements items = row.select("p.energy-value__tabl--item");
//                        Elements scores = row.select("p.item-score");
//                        Elements crossbars = row.select("div.energy-value__tabl--crossbar");
//
//                        String item = items.get(0).text();
//                        String score = scores.get(0).text().trim();
//                        String crossbarWidth = crossbars.get(0)
//                                .select("div.crossbar__runner")
//                                .attr("style");
//
//                        rowData.put("Item", item);
//                        rowData.put("Score", score);
//                        rowData.put("CrossbarWidth", crossbarWidth);
//
//                        // Add the rowData to the list
//                        dataList.add(rowData);
//
//                    }

                    // Print or use the dataList to build a table or perform further processing
                    for (Map<String, String> rowData : dataList) {
                        Log.d("Item: ", "= " + rowData.get("Item"));
                        Log.d("Score: ", "= " + rowData.get("Score"));
                        Log.d("CrossbarWidth: ", "= " + rowData.get("CrossbarWidth"));
                        System.out.println("Item: " + rowData.get("Item"));
                        System.out.println("Score: " + rowData.get("Score"));
                        System.out.println("CrossbarWidth: " + rowData.get("CrossbarWidth"));
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

    }
}