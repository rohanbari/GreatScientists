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

        Scientist[] scientistList = gson.fromJson(readAsset("scientists.json"),
                Scientist[].class);
        adapter.addScientists(new ArrayList<>(Arrays.asList(scientistList)));

        binding.listView.setAdapter(adapter);
    }

    private String readAsset(final String fileName) {
        String json = "";

        try {
            InputStream input = getAssets().open(fileName);
            int size = input.available();
            byte[] bytes = new byte[size];

            if (-1 == input.read(bytes))
                System.err.println("Error while reading the JSON asset file.");

            input.close();

            json = new String(bytes, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}