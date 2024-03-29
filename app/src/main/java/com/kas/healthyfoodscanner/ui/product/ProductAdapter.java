package com.kas.healthyfoodscanner.ui.product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.home.scanner.ResultScanActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
    private List<String> barcode, companyName, productName;

    public ProductAdapter(Context context, List<String> barcode, List<String> companyName, List<String> productName) {
        this.context = context;
        this.barcode = barcode;
        this.companyName = companyName;
        this.productName = productName;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.barcode_search_tv.setText(barcode.get(position));
        holder.product_name_search_tv.setText(productName.get(position));
        holder.company_name_search_tv.setText(companyName.get(position));
        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateProductActivity.class);
            intent.putExtra("barcode", barcode.get(position));
            context.startActivity(intent);
        });
        holder.detailsIv.setOnClickListener(view -> {
            Intent intent = new Intent(context, ResultScanActivity.class);
            intent.putExtra("barcode", barcode.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return barcode.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView barcode_search_tv;
        TextView product_name_search_tv;
        TextView company_name_search_tv;
        LinearLayout linearLayout;
        ImageView imageView;
        ImageView detailsIv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.barcode_search_tv = itemView.findViewById(R.id.barcode_search_tv);
            this.product_name_search_tv = itemView.findViewById(R.id.product_name_search_tv);
            this.company_name_search_tv = itemView.findViewById(R.id.company_name_search_tv);
            this.linearLayout = itemView.findViewById(R.id.product_layout);
            this.imageView = itemView.findViewById(R.id.update_iv);
            this.detailsIv = itemView.findViewById(R.id.details_iv);
        }
    }
}
