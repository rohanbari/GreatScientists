package com.rohanbari.scientists;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.rohanbari.scientists.databinding.ItemScientistBinding;

import java.util.ArrayList;

public class ScientistAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Scientist> scientists = new ArrayList<>();

    public ScientistAdapter(Context context) {
        this.context = context;
    }

    public void addScientists(ArrayList<Scientist> scientists) {
        this.scientists.clear();
        this.scientists.addAll(scientists);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return scientists.size();
    }

    @Override
    public Object getItem(int i) {
        return scientists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemScientistBinding binding = ItemScientistBinding
                .inflate(LayoutInflater.from(context), viewGroup, false);
        Scientist scientist = scientists.get(i);

        Glide.with(context).load(scientist.getImage()).into(binding.scientistImage);
        binding.scientistName.setText(scientist.getName());
        binding.scientistDescription.setText(scientist.getDescription());

        binding.scientistTab.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse(scientist.getWikiUrl()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        });

        // TODO: 02/02/22 Add the zoom-to-fullscreen algorithm in the next release.
//        binding.scientistImage.setOnClickListener(v -> {
//
//        });

        return binding.getRoot();
    }
}
