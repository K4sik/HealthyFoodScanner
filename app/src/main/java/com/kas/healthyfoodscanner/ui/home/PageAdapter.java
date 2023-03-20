package com.kas.healthyfoodscanner.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.ui.home.scanner.BarcodeScannerActivity;
import com.kas.healthyfoodscanner.ui.product.AddProductActivity;
import com.kas.healthyfoodscanner.ui.home.scanner.ResultScanActivity;
import com.kas.healthyfoodscanner.ui.product.ProductActivity;

import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder>{

    private List<Page> dataholder;

    public PageAdapter(List<Page> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public PageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(dataholder.get(position).getImgId());
        holder.header.setText(dataholder.get(position).getName());
        holder.imgStat.setImageResource(dataholder.get(position).getImgId2());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context context;
        ImageView img;
        TextView header;
        ImageView imgStat;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            context = itemView.getContext();
            img = itemView.findViewById(R.id.listItemImgMain);
            header = itemView.findViewById(R.id.pageName);
            imgStat = itemView.findViewById(R.id.listItemImgSecond);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent = null;

            switch (getAdapterPosition()){

                case 0:
                    intent = new Intent(context, BarcodeScannerActivity.class);
                    break;

                case 1:
                    intent = new Intent(context, AddProductActivity.class);
                    break;

                case 2:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=QTB1YiWxxKU&ab_channel=BenLionelScott"));
                    break;

                case 3:
                    intent = new Intent(context, ProductActivity.class);
                    break;
            }

            context.startActivity(intent);
        }
    }
}
