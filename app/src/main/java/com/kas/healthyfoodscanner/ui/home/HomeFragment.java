package com.kas.healthyfoodscanner.ui.home;

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

import com.kas.healthyfoodscanner.R;
import com.kas.healthyfoodscanner.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<Page> pageList = new ArrayList<>();
        pageList.add(new Page(R.drawable.camera, "Scan Barcode", R.drawable.ic_arrow_right));
        pageList.add(new Page(R.drawable.map, "Add new Product", R.drawable.ic_arrow_right));
        pageList.add(new Page(R.drawable.person, "Profile", R.drawable.ic_arrow_right));
        pageList.add(new Page(R.drawable.ic_shopping_cart, "Product", R.drawable.ic_arrow_right));

        RecyclerView recyclerView = root.findViewById(R.id.home_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new PageAdapter(pageList));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}