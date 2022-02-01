package com.rohanbari.scientists;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.rohanbari.scientists.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ScientistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        adapter = new ScientistAdapter(this);
        binding.listView.setAdapter(adapter);

        Gson gson = new Gson();
        String json = null;
        try {
            InputStream input = getAssets().open("scientists.json");
            int size = input.available();
            byte[] bytes = new byte[size];
            input.read(bytes);
            input.close();
            json = new String(bytes, StandardCharsets.UTF_8);
            Scientist[] scientistList = gson.fromJson(json, Scientist[].class);
            adapter.addScientists(new ArrayList<>(Arrays.asList(scientistList)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        binding.listView.setAdapter(adapter);
    }
}