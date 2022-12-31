package com.improve10x.doitquotes;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

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
        setupQuotationAdapter();
        setupQuotationRv();
    }

    private void setupQuotationRv() {
        binding.quotationsRv.setLayoutManager(new LinearLayoutManager(this));
        binding.quotationsRv.setAdapter(quotationsAdapter);
    }

    private void setupQuotationAdapter() {
        quotationsAdapter = new QuotationsAdapter();
        quotationsAdapter.setData(quotes);
    }
}