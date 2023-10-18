package com.kas.healthyfoodscanner.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.home.scanner.ResultScanActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private ArrayList<SearchItem> searchItems;
    private Context context;

    public SearchAdapter(ArrayList<SearchItem> searchItems, Context context) {
        this.searchItems = searchItems;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        SearchItem searchItem = searchItems.get(position);
        holder.barcode_tv.setText("Штрих-код: " + searchItem.getBarcode());
        holder.product_name_tv.setText(searchItem.getProductName());
        holder.table_row_whites_id.setText(searchItem.getWhites());
        holder.table_row_fats_id.setText(searchItem.getFats());
        holder.table_row_carbohydrates_id.setText(searchItem.getCarbohydrates());
        holder.table_row_caloric_id.setText(searchItem.getCalories());
        if (!searchItem.getAdditives().isEmpty()){
            holder.additives_tv.setVisibility(View.VISIBLE);
            holder.additives_tv.setText("Харчові добавки: " + searchItem.getAdditives());
        } else {
            holder.additives_tv.setVisibility(View.INVISIBLE);
        }
        Picasso.get().load(searchItem.getImgUrl()).into(holder.product_item_img_iv);
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView barcode_tv;
        ImageView product_item_img_iv;
        TextView product_name_tv;
        TextView table_row_whites_id;
        TextView table_row_fats_id;
        TextView table_row_carbohydrates_id;
        TextView table_row_caloric_id;
        TextView additives_tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barcode_tv = itemView.findViewById(R.id.barcode_tv);
            product_item_img_iv = itemView.findViewById(R.id.product_item_img_iv);
            product_name_tv = itemView.findViewById(R.id.product_name_tv);
            table_row_whites_id = itemView.findViewById(R.id.table_row_whites_id);
            table_row_fats_id = itemView.findViewById(R.id.table_row_fats_id);
            table_row_carbohydrates_id = itemView.findViewById(R.id.table_row_carbohydrates_id);
            table_row_caloric_id = itemView.findViewById(R.id.table_row_caloric_id);
            additives_tv = itemView.findViewById(R.id.additives_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            SearchItem searchItem = searchItems.get(position);

//            Intent intent = new Intent(context, 9kasbActivity.class);
//            intent.putExtra();
//            context.startActivity(intent);
        }
    }
}
