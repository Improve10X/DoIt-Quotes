package com.improve10x.doitquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10x.doitquotes.databinding.ActivityQuotationsBinding;

import java.util.ArrayList;

public class QuotationsActivity extends BaseActivity {

    public ArrayList<Quote> quotes = new ArrayList<>();
    public ActivityQuotationsBinding binding;
    public QuotationsAdapter quotationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Quotations");
    }
}