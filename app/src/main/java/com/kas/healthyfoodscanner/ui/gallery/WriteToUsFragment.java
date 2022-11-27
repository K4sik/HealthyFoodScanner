package com.kas.healthyfoodscanner.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kas.healthyfoodscanner.MainActivity;
import com.kas.healthyfoodscanner.databinding.FragmentGalleryBinding;

public class WriteToUsFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        startActivity(new Intent(root.getContext(), MainActivity.class));

        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("text/plain");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "roman.kasarab.pz.2018@lpnu.ua" });
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Application");
        mailIntent.putExtra(Intent.EXTRA_TEXT, "Best app :)");

        startActivity(Intent.createChooser(mailIntent, "Send Email"));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}