package com.kas.healthyfoodscanner.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.databinding.FragmentHomeBinding;
import com.kas.healthyfoodscanner.ui.product.ProductActivity;
import com.kas.healthyfoodscanner.ui.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private TextInputEditText tiet_search;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tiet_search = root.findViewById(R.id.tiet_search);

        List<Page> pageList = new ArrayList<>();
        pageList.add(new Page(R.drawable.camera, "Scan Barcode", R.drawable.ic_arrow_right));
        pageList.add(new Page(R.drawable.map, "Add new Product", R.drawable.ic_arrow_right));
        pageList.add(new Page(R.drawable.person, "Profile", R.drawable.ic_arrow_right));
        pageList.add(new Page(R.drawable.ic_shopping_cart, "Product", R.drawable.ic_arrow_right));

        RecyclerView recyclerView = root.findViewById(R.id.home_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new PageAdapter(pageList));

        tiet_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(root.getContext(), SearchActivity.class));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}